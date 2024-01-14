package com.itgorillaz.jmespathplayground.view.core;

import javax.swing.*;
import java.awt.*;

public interface WindowManager {

    void show(JFrame frame, BoundsPolicy policy);

    Window getActiveWindow();

    JFrame getRootFrame();

}
