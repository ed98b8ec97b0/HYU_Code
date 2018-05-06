package term_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowStaff extends JFrame {
    Dimension size = new Dimension(100, 30);
    String[] positionList = {"Staff", "Supervisor"};
    JComboBox positionBox = new JComboBox(positionList);

    public WindowStaff(ObjeStaff usingStaff) {



        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("직원등록");
        setSize(230, 150);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel name = new JLabel("직원명");
        name.setPreferredSize(size);
        add(name);
        JTextField nameInput = new JTextField();
        nameInput.setPreferredSize(size);
        add(nameInput);
        JLabel position = new JLabel("직급");
        position.setPreferredSize(size);
        add(position);
        positionBox.setPreferredSize(size);
        add(positionBox);
        JButton register = new JButton("등록");
        register.setPreferredSize(size);
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String staffName = nameInput.getText();
                String staffPosition = positionList[positionBox.getSelectedIndex()];
                ObjeStaff staff = new ObjeStaff(staffName, "", staffPosition, 0);
                new FuncRegist(staff, usingStaff);
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
