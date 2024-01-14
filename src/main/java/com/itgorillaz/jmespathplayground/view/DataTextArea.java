package com.itgorillaz.jmespathplayground.view;

import com.itgorillaz.jmespathplayground.event.*;
import com.itgorillaz.jmespathplayground.view.core.ThemeManager;
import com.itgorillaz.jmespathplayground.view.listener.TextListener;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class DataTextArea extends RSyntaxTextArea implements JsonDataProvider, JMESPathEventConsumer {

    private final transient EventManager eventManager;
    private final transient TextListener textListener;

    public DataTextArea(final EventManager eventManager, final TextListener textListener) {
        this.eventManager = eventManager;
        this.textListener = textListener;
        initComponents();
    }

    private void initComponents() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setTabSize(2);
        setCodeFoldingEnabled(true);
        setAntiAliasingEnabled(true);
        setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);

        getDocument().addDocumentListener(textListener);
        eventManager.registerJsonDataProvider(this);

        ThemeManager.apply(this);
    }

    @Override
    public String getJsonData() {
        return getText();
    }

    @Override
    public void onEvent(JMESPathEvent event) {
        if (EventType.INVALID_JSON_DATA == event.type()) {
            setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }
}
