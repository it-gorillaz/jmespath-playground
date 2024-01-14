package com.itgorillaz.jmespathplayground.view.core;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;

@Component
public class DefaultWindowManager implements WindowManager, AWTEventListener {

    private Window window;
    private JFrame root;

    @Override
    public void show(JFrame frame, BoundsPolicy policy) {
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.WINDOW_EVENT_MASK);
        this.root = frame;
        initBounds(frame, policy);
        frame.setVisible(true);
    }

    @Override
    public Window getActiveWindow() {
        return window;
    }

    @Override
    public JFrame getRootFrame() {
        return root;
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        if (event instanceof WindowEvent windowEvent) {
            switch (windowEvent.getID()) {
                case WindowEvent.WINDOW_ACTIVATED:
                    window = windowEvent.getWindow();
                    break;
                case WindowEvent.WINDOW_DEACTIVATED:
                    window = null;
                    break;
                default:
                    break;
            }
        }
    }

    private void initBounds(JFrame frame, BoundsPolicy policy) {
        switch (policy) {
            case CENTER_ONLY:
                frame.setLocationRelativeTo(null);
                break;
            case MAXIMIZE_BOTH:
                frame.setState(Frame.MAXIMIZED_BOTH);
                break;
            case PACK_ONLY:
                frame.pack();
                break;
            case PACK_AND_CENTER:
                frame.pack();
                frame.setLocationRelativeTo(null);
                break;
            case MAXIMIZE:
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension dimension = toolkit.getScreenSize();
                Insets insets = toolkit.getScreenInsets(frame.getGraphicsConfiguration());

                int width = dimension.width - (insets.left + insets.top);
                int height = dimension.height - (insets.top + insets.bottom);
                int x = insets.left;
                int y = insets.right;

                frame.pack();
                frame.setSize(width, height);
                frame.setLocation(x, y);

                break;
            default:
                break;
        }
    }
}
