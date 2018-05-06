import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelTableStatus extends JPanel {
    JLabel title = new JLabel("테이블 현황");
    JPanel panel = new JPanel();

    PanelTableStatus(ArrayList<JLabel> tl) {
        setPreferredSize(new Dimension(280, 245));
        setLayout(new FlowLayout());

        title.setPreferredSize(new Dimension(270, 20));
        add(title);

        panel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(270, 210));
        for (int i = 0; i < 20; i++) {
            panel.add(tl.get(i));
        }
        add(panel);
    }
}
