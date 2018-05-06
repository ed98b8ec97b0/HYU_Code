package term_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowMenu extends JFrame {
    public WindowMenu(ObjeStaff usingStaff) {
        Dimension size = new Dimension(100, 30);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("메뉴등록");
        setSize(230, 150);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel name = new JLabel("메뉴명");
        name.setPreferredSize(size);
        add(name);
        JTextField nameInput = new JTextField();
        nameInput.setPreferredSize(size);
        add(nameInput);
        JLabel price = new JLabel("가격");
        price.setPreferredSize(size);
        add(price);
        JTextField priceInput = new JTextField();
        priceInput.setPreferredSize(size);
        add(priceInput);
        JButton register = new JButton("등록");
        register.setPreferredSize(size);
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String menuName = nameInput.getText();
                int menuPrice = Integer.parseInt(priceInput.getText());
                ObjeMenu menu = new ObjeMenu(menuName, "", menuPrice);
                new FuncRegist(menu, usingStaff);
            }
        });
        add(register);
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
