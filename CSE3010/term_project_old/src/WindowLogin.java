package term_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class WindowLogin extends JFrame{
    public WindowLogin(ObjeStaff usingStaff) {
        Dimension size = new Dimension(100, 30);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("직원등록");
        setSize(350, 120);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(215,100));
        left.setLayout(new FlowLayout());
        JLabel id = new JLabel("아이디");
        id.setPreferredSize(size);
        left.add(id);
        JTextField idInput = new JTextField();
        idInput.setPreferredSize(size);
        left.add(idInput);
        JLabel pwd = new JLabel("비밀번호");
        pwd.setPreferredSize(size);
        left.add(pwd);
        JPasswordField pwdInput = new JPasswordField();
        pwdInput.setPreferredSize(size);
        left.add(pwdInput);
        add(left);

        JButton login = new JButton("로그인");
        login.setPreferredSize(new Dimension(100, 60));
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                usingStaff.setName(idInput.getText());
                usingStaff.setId(pwdInput.getText());
                new FuncStaffCheck(usingStaff);
                dispose();
            }
        });
        add(login);

        setVisible(true);
    }
}
