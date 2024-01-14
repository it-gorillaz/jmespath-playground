package com.itgorillaz.jmespathplayground.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.burt.jmespath.JmesPath;
import io.burt.jmespath.jackson.JacksonRuntime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EventManager {

    private final List<JMESPathEventConsumer> consumers = new ArrayList<>();

    private final ObjectMapper mapper = new ObjectMapper();
    private final JmesPath<JsonNode> jmespath = new JacksonRuntime();

    private QueryProvider queryProvider;
    private JsonDataProvider jsonDataProvider;

    public void registerQueryProvider(final QueryProvider queryProvider) {
        this.queryProvider = queryProvider;
    }

    public void registerJsonDataProvider(final JsonDataProvider jsonDataProvider) {
        this.jsonDataProvider = jsonDataProvider;
    }

    public void registerConsumer(JMESPathEventConsumer consumer) {
        consumers.add(consumer);
    }

    public void notifyChange() {
        var json = parseJsonText();
        if (json.isEmpty()) {
            notifyConsumers(new JMESPathEvent(null, EventType.INVALID_JSON_DATA));
            return;
        }

        var result = search(json.get());
        if (result.isEmpty()) {
            notifyConsumers(new JMESPathEvent(null, EventType.INVALID_QUERY_SYNTAX));
            return;
        }

        notifyConsumers(new JMESPathEvent(result.get().toPrettyString(), EventType.EXPRESSION_EVALUATED));
    }

    private Optional<JsonNode> parseJsonText() {
        try {
            var node = mapper.readTree(jsonDataProvider.getJsonData());
            return Optional.ofNullable(node);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<JsonNode> search(JsonNode node) {
        try {
            var expression = jmespath.compile(queryProvider.getQuery());
            return Optional.ofNullable(expression.search(node));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void notifyConsumers(JMESPathEvent event) {
        for (var consumer : consumers) {
            consumer.onEvent(event);
        }
    }

}
