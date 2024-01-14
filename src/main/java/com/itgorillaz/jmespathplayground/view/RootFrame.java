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

    private final QueryTextArea queryPanel;
    private final DataTextArea dataPanel;
    private final ResultTextArea resultPanel;

    public RootFrame(final QueryTextArea queryPanel, final DataTextArea dataPanel, final ResultTextArea resultPanel) {
        this.queryPanel = queryPanel;
        this.dataPanel = dataPanel;
        this.resultPanel = resultPanel;
        initComponents();
    }

    private void initComponents() {
        setTitle(FRAME_TITLE);
        setName(FRAME_TITLE);
        setLayout(LAYOUT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(queryPanel, "growx,span");
        add(new RTextScrollPane(dataPanel), "grow");
        add(new RTextScrollPane(resultPanel), "grow");
    }

}
