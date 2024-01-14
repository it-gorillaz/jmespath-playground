package com.itgorillaz.jmespathplayground.view.listener;

import com.itgorillaz.jmespathplayground.event.EventManager;
import org.springframework.stereotype.Component;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

@Component
public class TextListener implements DocumentListener {

    private final EventManager eventManager;

    public TextListener(final EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        dispatchEvent();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        dispatchEvent();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        dispatchEvent();
    }

    private void dispatchEvent() {
        EventQueue.invokeLater(eventManager::notifyChange);
    }
}
