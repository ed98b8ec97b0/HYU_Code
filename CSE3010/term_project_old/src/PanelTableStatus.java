package term_project;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelTableStatus extends JPanel {
    public PanelTableStatus(ArrayList<ObjeTable> tableList) {
        setPreferredSize(new Dimension(280, 245));
        setLayout(new FlowLayout());

        JLabel title = new JLabel("테이블 현황");
        title.setPreferredSize(new Dimension(270, 20));
        add(title);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.setPreferredSize(new Dimension(270, 210));
        for (int i = 1; i <= 20; i++) {
            panel.add(tableList.get(i));
        }
        add(panel);
    }


}
