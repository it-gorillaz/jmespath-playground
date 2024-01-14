package com.itgorillaz.jmespathplayground.view;

import net.miginfocom.swing.MigLayout;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class RootFrame extends JFrame {

    private static final String FRAME_TITLE = "JMESPath Playground";

    private static final String LAYOUT_CONSTRAINTS = "";
    private static final String LAYOUT_ROW_CONSTRAINTS = "[][grow]";
    private static final String LAYOUT_COLUMN_CONSTRAINTS = "[grow][grow]";

    private static final MigLayout LAYOUT = new MigLayout(LAYOUT_CONSTRAINTS,
        LAYOUT_COLUMN_CONSTRAINTS, LAYOUT_ROW_CONSTRAINTS);

    private final QueryTextArea queryTextArea;
    private final DataTextArea dataTextArea;
    private final ResultTextArea resultTextArea;

    public RootFrame(final QueryTextArea queryTextArea,
                     final DataTextArea dataTextArea,
                     final ResultTextArea resultTextArea) {
        this.queryTextArea = queryTextArea;
        this.dataTextArea = dataTextArea;
        this.resultTextArea = resultTextArea;
        initComponents();
    }

    private void initComponents() {
        setTitle(FRAME_TITLE);
        setName(FRAME_TITLE);
        setLayout(LAYOUT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(queryTextArea, "growx,span");
        add(new RTextScrollPane(dataTextArea), "grow");
        add(new RTextScrollPane(resultTextArea), "grow");
    }

}
