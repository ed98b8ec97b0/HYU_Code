package term_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowCustomer extends JFrame {
    public WindowCustomer(ObjeStaff usingStaff) {
        Dimension size = new Dimension(100, 30);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("회원등록");
        setSize(230, 190);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel name = new JLabel("고객명");
        name.setPreferredSize(size);
        add(name);
        JTextField nameInput = new JTextField();
        nameInput.setPreferredSize(size);
        add(nameInput);
        JLabel birth = new JLabel("생일(4자리)");
        birth.setPreferredSize(size);
        add(birth);
        JTextField birthInput = new JTextField();
        birthInput.setPreferredSize(size);
        add(birthInput);
        JLabel phone = new JLabel("연락처");
        phone.setPreferredSize(size);
        add(phone);
        JTextField phoneInput = new JTextField();
        phoneInput.setPreferredSize(size);
        add(phoneInput);
        JButton signIn = new JButton("가입신청");
        signIn.setPreferredSize(size);
        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ObjeCustomer customer = new ObjeCustomer(nameInput.getText(), "", birthInput.getText(), phoneInput.getText(), "Normal", 0);
                new FuncRegist(customer, usingStaff);
            }
        });
        add(signIn);
        JButton cancel = new JButton("취소");
        cancel.setPreferredSize(size);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancel);

        setVisible(true);
    }
}
