package com.itgorillaz.jmespathplayground.view;

import com.itgorillaz.jmespathplayground.event.*;
import com.itgorillaz.jmespathplayground.view.listener.TextListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

@Component
public class QueryTextArea extends JTextField implements QueryProvider, JMESPathEventConsumer {

    private static final Border ERROR_BORDER = BorderFactory.createLineBorder(Color.RED);
    private static final Border DEFAULT_BORDER = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

    private final transient EventManager eventManager;
    private final transient TextListener textListener;

    public QueryTextArea(final EventManager eventManager, final TextListener textListener) {
        this.eventManager = eventManager;
        this.textListener = textListener;
        initComponents();
    }

    private void initComponents() {
        setBorder(DEFAULT_BORDER);
        getDocument().addDocumentListener(textListener);
        eventManager.registerQueryProvider(this);
        eventManager.registerConsumer(this);
    }

    @Override
    public String getQuery() {
        return getText();
    }

    @Override
    public void onEvent(JMESPathEvent event) {
        if (EventType.INVALID_QUERY_SYNTAX == event.type()) {
            setBorder(ERROR_BORDER);
        } else {
            setBorder(DEFAULT_BORDER);
        }
    }
}
