package com.itgorillaz.jmespathplayground.view.core;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;

import java.io.IOException;

public final class ThemeManager {

    private static final String ECLIPSE_THEME = "/org/fife/ui/rsyntaxtextarea/themes/eclipse.xml";

    private static final Theme THEME;
    static {
        try {
            var resource = ThemeManager.class.getResourceAsStream(ECLIPSE_THEME);
            THEME = Theme.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ThemeManager() {

    }

    public static void apply(RSyntaxTextArea textArea) {
        THEME.apply(textArea);
    }

}
