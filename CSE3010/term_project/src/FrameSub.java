import java.util.*;
import java.util.Random;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameSub extends JFrame {
    String[] sclass = new String[] {"Staff", "Supervisor"};
    JLabel l1 = new JLabel();
    JLabel l2 = new JLabel();
    JLabel l3 = new JLabel();
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JComboBox<String> c = new JComboBox<String>(sclass);
    JButton b1 = new JButton();
    JButton b2 = new JButton();
    Dimension d = new Dimension(80, 30);
    Dimension ld = new Dimension(120, 30);

    public FrameSub(Account user, int opt) {
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        setSize(300, 115);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("사원 로그인");
        setResizable(false);
        setLayout(new FlowLayout());

        p1.setLayout(new FlowLayout());
        p1.setPreferredSize(new Dimension(175, 75));
        p2.setLayout(new BorderLayout());
        p2.setPreferredSize(new Dimension(90, 75));

        l1.setText("이름");
        l1.setPreferredSize(d);
        p1.add(l1);

        t1.setPreferredSize(d);
        p1.add(t1);

        l2.setText("사원번호");
        l2.setPreferredSize(d);
        p1.add(l2);

        t2.setPreferredSize(d);
        p1.add(t2);

        b1.setText("로그인");
        b1.setPreferredSize(d);
        b1.addActionListener(e -> {
            String sname = t1.getText();
            String sid = t2.getText();
            String sclass = "";
            String query = "select sclass from staff where sname = \'" + sname + "\' and sid = " + sid;
            SQLExecutor sqle = new SQLExecutor(query, "select");
            ArrayList<String> result = sqle.getResult();
            StringTokenizer st = new StringTokenizer(result.get(0));

            sclass = st.nextToken(" ");
            user.setAccount(sname, sid, sclass);
            dispose();
        });
        p2.add(b1, "Center");

        add(p1);
        add(p2);

        setVisible(true);
    }

    public FrameSub(Account user, double opt) {
        setSize(185, 175);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("회원등록");
        setResizable(false);
        setLayout(new FlowLayout());

        l1.setText("고객명");
        l1.setPreferredSize(d);
        add(l1);

        t1.setPreferredSize(d);
        add(t1);

        l2.setText("생일(4자리)");
        l2.setPreferredSize(d);
        add(l2);

        t2.setPreferredSize(d);
        add(t2);

        l3.setText("연락처");
        l3.setPreferredSize(d);
        add(l3);

        t3.setPreferredSize(d);
        add(t3);

        b1.setText("가입신청");
        b1.setPreferredSize(new Dimension(90, 30));
        b1.addActionListener(e -> {
            if (user.getSclass().equals("Supervisor")) {
                String name = t1.getText();
                String id = "";
                String birth = t2.getText();
                String phone = t3.getText();
                boolean ok;
                String query;
                SQLExecutor sqle;

                do {
                    Random r = new Random();
                    ok = true;
                    for (int i = 0; i < 4; i++) {
                        id += String.valueOf(r.nextInt(10));
                    }
                    query = "select cid from customer where cid = \'" + id + "\'";
                    sqle = new SQLExecutor(query, "select");
                    ArrayList<String> result = sqle.getResult();
                    if (result.size() != 0) {
                        ok = false;
                    }
                } while (ok == false);
                query = "insert into customer values (\'" + name + "\', \'" + id + "\', \'" + birth + "\', \'" + phone + "\', \'Normal\', 0)";
                sqle = new SQLExecutor(query, "insert");

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "권한이 없는 사용자입니다.", "권한 오류", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(b1);

        b2.setText("취소");
        b2.setPreferredSize(new Dimension(60, 30));
        b2.addActionListener(e -> {
            dispose();
        });
        add(b2);

        setVisible(true);
    }

    public FrameSub(Account user, String opt) {
        setSize(220, 140);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("직원등록");
        setResizable(false);
        setLayout(new FlowLayout());

        l1.setText("직원명");
        l1.setPreferredSize(d);
        add(l1);

        t1.setPreferredSize(ld);
        add(t1);

        l2.setText("직급");
        l2.setPreferredSize(d);
        add(l2);

        c.setPreferredSize(ld);
        add(c);

        b1.setText("등록");
        b1.setPreferredSize(d);
        b1.addActionListener(e -> {
            if (user.getSclass().equals("Supervisor")) {
                String name = t1.getText();
                String id = "";
                String cl = c.getSelectedItem().toString();
                boolean ok;
                String query;
                SQLExecutor sqle;

                do {
                    Random r = new Random();
                    ok = true;
                    for (int i = 0; i < 4; i++) {
                        id += String.valueOf(r.nextInt(10));
                    }
                    query = "select sid from staff where sid = \'" + id + "\'";
                    sqle = new SQLExecutor(query, "select");
                    ArrayList<String> result = sqle.getResult();
                    if (result.size() != 0) {
                        ok = false;
                    }
                } while (ok == false);
                query = "insert into staff values (\'" + name + "\', \'" + id + "\', \'" + cl + "\', 0)";
                sqle = new SQLExecutor(query, "insert");

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "권한이 없는 사용자입니다.", "권한 오류", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(b1);

        b2.setText("취소");
        b2.setPreferredSize(d);
        b2.addActionListener(e -> {
            dispose();
        });
        add(b2);

        setVisible(true);
    }

    public FrameSub(Account user, boolean opt) {
        setSize(185, 140);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("메뉴등록");
        setResizable(false);
        setLayout(new FlowLayout());

        l1.setText("메뉴명");
        l1.setPreferredSize(d);
        add(l1);

        t1.setPreferredSize(d);
        add(t1);

        l2.setText("가격");
        l2.setPreferredSize(d);
        add(l2);

        t2.setPreferredSize(d);
        add(t2);

        b1.setText("등록");
        b1.setPreferredSize(d);
        b1.addActionListener(e -> {
            if (user.getSclass().equals("Supervisor")) {
                String name = t1.getText();
                String id = "";
                String price = t2.getText();
                boolean ok;
                String query;
                SQLExecutor sqle;

                do {
                    Random r = new Random();
                    ok = true;
                    for (int i = 0; i < 4; i++) {
                        id += String.valueOf(r.nextInt(10));
                    }
                    query = "select mid from menu where mid = \'" + id + "\'";
                    sqle = new SQLExecutor(query, "select");
                    ArrayList<String> result = sqle.getResult();
                    if (result.size() != 0) {
                        ok = false;
                    }
                } while (ok == false);
                query = "insert into menu values (\'" + name + "\', \'" + id + "\', " + price + ")";
                sqle = new SQLExecutor(query, "insert");

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "권한이 없는 사용자입니다.", "권한 오류", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(b1);

        b2.setText("취소");
        b2.setPreferredSize(d);
        b2.addActionListener(e -> {
            dispose();
        });
        add(b2);

        setVisible(true);
    }
}
