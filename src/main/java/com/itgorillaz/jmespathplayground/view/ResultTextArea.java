package com.itgorillaz.jmespathplayground.view;

import com.itgorillaz.jmespathplayground.event.EventType;
import com.itgorillaz.jmespathplayground.event.JMESPathEvent;
import com.itgorillaz.jmespathplayground.event.JMESPathEventConsumer;
import com.itgorillaz.jmespathplayground.event.EventManager;
import com.itgorillaz.jmespathplayground.view.core.ThemeManager;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.springframework.stereotype.Component;

@Component
public class ResultTextArea extends RSyntaxTextArea implements JMESPathEventConsumer {

    private final transient EventManager eventManager;

    public ResultTextArea(final EventManager eventManager) {
        this.eventManager = eventManager;
        initComponents();
    }

    private void initComponents() {
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setTabSize(2);
        setCodeFoldingEnabled(true);
        setAntiAliasingEnabled(true);
        setFocusable(false);
        setHighlightCurrentLine(false);
        setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);

        eventManager.registerConsumer(this);

        ThemeManager.apply(this);
    }

    @Override
    public void onEvent(JMESPathEvent event) {
        if (EventType.EXPRESSION_EVALUATED == event.type()) {
            setText(event.data());
        } else if (EventType.INVALID_QUERY_SYNTAX == event.type() || EventType.INVALID_JSON_DATA == event.type()) {
            setText(null);
        }
    }

}
