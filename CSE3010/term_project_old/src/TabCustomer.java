package term_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class TabCustomer extends JPanel {
    public TabCustomer(ObjeStaff usingStaff) {
        setLayout(new FlowLayout());

        JPanel left = new JPanel();
        left.setLayout(new FlowLayout(0));
        left.setPreferredSize(new Dimension(100, 60));
        JLabel name = new JLabel("고객명");
        name.setPreferredSize(new Dimension(90, 20));
        left.add(name);
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(70, 20));
        left.add(input);
        add(left);

        JPanel right = new JPanel();
        right.setLayout(new FlowLayout(2));
        right.setPreferredSize(new Dimension(100, 60));
        JLabel blank = new JLabel();
        blank.setPreferredSize(new Dimension(90,20));
        right.add(blank);
        JButton signIn = new JButton("가입");
        signIn.setPreferredSize(new Dimension(30, 20));
        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowCustomer(usingStaff);
            }
        });
        // 이부분 떼서 하나의 내부 클래스 리스너로 만들 것.
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
