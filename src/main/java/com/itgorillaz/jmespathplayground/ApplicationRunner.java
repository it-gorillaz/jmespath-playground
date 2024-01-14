package com.itgorillaz.jmespathplayground;

import com.itgorillaz.jmespathplayground.view.RootFrame;
import com.itgorillaz.jmespathplayground.view.core.BoundsPolicy;
import com.itgorillaz.jmespathplayground.view.core.WindowManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final RootFrame root;
    private final WindowManager windowManager;

    public ApplicationRunner(final RootFrame root, WindowManager windowManager) {
        this.root = root;
        this.windowManager = windowManager;
    }

    @Override
    public void run(String... args) throws Exception {
        SwingUtilities.invokeLater(() -> windowManager.show(root, BoundsPolicy.MAXIMIZE));
    }
}
