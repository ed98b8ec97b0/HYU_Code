package term_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class TabMenu extends JPanel {
    public TabMenu(ObjeStaff usingStaff) {
        setLayout(new FlowLayout());

        JPanel left = new JPanel();
        left.setLayout(new FlowLayout(0));
        left.setPreferredSize(new Dimension(100, 60));
        JLabel name = new JLabel("메뉴명");
        name.setPreferredSize(new Dimension(90, 20));
        left.add(name);
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(90, 20));
        left.add(input);
        add(left);

        JPanel right = new JPanel();
        right.setLayout(new FlowLayout(2));
        right.setPreferredSize(new Dimension(100, 60));
        JLabel blank = new JLabel();
        blank.setPreferredSize(new Dimension(90,20));
        right.add(blank);
        JButton signIn = new JButton("메뉴등록");
        signIn.setPreferredSize(new Dimension(60, 20));
        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowMenu(usingStaff);
            }
        });
        right.add(signIn);
        JButton ref = new JButton("조회");
        ref.setPreferredSize(new Dimension(30, 20));
        right.add(ref);
        add(right);

        JTextArea console = new JTextArea();
        console.setPreferredSize(new Dimension(220, 165));
        add(console);
    }
}
