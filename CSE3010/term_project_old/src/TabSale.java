package term_project;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TabSale extends JPanel {
    public TabSale(ObjeStaff usingStaff) {
        setLayout(new FlowLayout());

        JPanel handle = new JPanel();
        handle.setLayout(new FlowLayout(0));
        handle.setPreferredSize(new Dimension(220, 40));
        JLabel due = new JLabel("기간");
        due.setPreferredSize(new Dimension(40, 30));
        handle.add(due);
        JComboBox date = new JComboBox();
        date.setPreferredSize(new Dimension(120, 30));
        handle.add(date);
        add(handle);

        JTextArea console = new JTextArea();
        console.setPreferredSize(new Dimension(220, 185));
        add(console);
    }
}
