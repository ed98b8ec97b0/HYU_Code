import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TabPanel extends JPanel {
    Calendar cal = new Calendar.getInstance();
    String today = String.valueOf(cal.get(Calendar.YEAR)) + "-" + String.valueOf(cal.get(Calendar.MONTH)+1) + "-" + String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    JLabel nameLabel = new JLabel();
    JTextField nameField = new JTextField();
    JButton newer = new JButton();
    JButton search = new JButton();
    JTextArea area = new JTextArea();
    Dimension oneline = new Dimension(220, 20);
    Dimension comboline = new Dimension(220, 30);
    Dimension longword = new Dimension(90, 30);
    Dimension shortword = new Dimension(60, 30);
    Dimension halfsize = new Dimension(220, 175);

    public TabPanel(Account user, int opt) {
        setLayout(new FlowLayout());

        nameLabel.setText("고객명");
        nameLabel.setPreferredSize(oneline);
        add(nameLabel);

        nameField.setPreferredSize(longword);
        add(nameField);

        newer.setText("가입");
        newer.setPreferredSize(longword);
        newer.addActionListener(e -> {
            new FrameSub(user, 1.0);
        });
        add(newer);

        search.setText("조회");
        search.setPreferredSize(shortword);
        search.addActionListener(e -> {
            if (!nameField.getText().equals("")) {
                String query = "select * from customer where cname = " + nameField.getText();
                SQLExecutor sqle = new SQLExecutor(query, "select");
                ArrayList<String> result = sqle.getResult();
                StringTokenizer st = new StringTokenizer(result.get(0));
                area.append("고객명:\t" + st.nextToken(" ") + "\n");
                area.append("고객ID:\t" + st.nextToken(" ") + "\n");
                area.append("생일:\t" + st.nextToken(" ") + "\n");
                area.append("전화번호:\t" + st.nextToken(" ") + "\n");
                area.append("고객등급:\t" + st.nextToken(" ") + "\n");
                area.append("총 구매금액:\t" + st.nextToken(" ") + "원\n");
            }
        });
        add(search);

        area.setEditable(false);
        area.setPreferredSize(halfsize);
        add(area);
    }

    public TabPanel(Account user, double opt) {
        setLayout(new FlowLayout());
        dateInput(today);

        nameLabel.setText("기간");
        nameLabel.setPreferredSize(oneline);
        add(nameLabel);

        ArrayList<String> dateList = dataInput(today);
        JComboBox<String> box = new JComboBox<String>(dateList);
        box.setPreferredSize(comboline);
        box.addActionListener(e -> {
            if (!box.getSelectedItem().toString().equals("")) {
                String query = "select sale, top, bot from sales where due = " + box.getSelectedItem().toString();
                SQLExecutor sqle = new SQLExecutor(query, "select");
                ArrayList<String> result = sqle.getResult();
                StringTokenizer st = new StringTokenizer(result.get(0));
                area.append("일 매출:\t" + st.nextToken(" ") + "\n");
                area.append("--------------------------------\n");
                area.append("가장 많이 팔린 메뉴\n");
                area.append(st.nextToken(" ") + "\n\n");
                area.append("가장 적게 팔린 메뉴\n");
                area.append(st.nextToken(" ") + "\n");
                area.append("--------------------------------\n");
                query = "select sum(sale) from sales";
                sqle = new SQLExecutor(query, "select");
                result = sqle.getResult();
                st = new StringTokenizer(result.get(0));
                area.append("누적 매출:\t" + st.nextToken(" "));
            }
        });
        add(box);

        area.setEditable(false);
        area.setPreferredSize(halfsize);
        add(area);
    }

    public TabPanel(Account user, String opt) {
        setLayout(new FlowLayout());

        nameLabel.setText("직원명");
        nameLabel.setPreferredSize(oneline);
        add(nameLabel);

        nameField.setPreferredSize(longword);
        add(nameField);

        newer.setText("직원등록");
        newer.setPreferredSize(longword);
        newer.addActionListener(e -> {
            new FrameSub(user, "1");
        });
        add(newer);

        search.setText("조회");
        search.setPreferredSize(shortword);
        search.addActionListener(e -> {
            if (!nameField.getText().equals("")) {
                String query = "select sname, sclass, stotal from staff where sname = " + nameField.getText();
                SQLExecutor sqle = new SQLExecutor(query, "select");
                ArrayList<String> result = sqle.getResult();
                StringTokenizer st = new StringTokenizer(result.get(0));
                area.append("직원명:\t" + st.nextToken(" ") + "\n");
                area.append("고객등급:\t" + st.nextToken(" ") + "\n");
                area.append("총실적:\t" + st.nextToken(" ") + "\n");
            }
        });
        add(search);

        area.setEditable(false);
        area.setPreferredSize(halfsize);
        add(area);
    }

    public TabPanel(Account user, boolean opt) {
        setLayout(new FlowLayout());

        nameLabel.setText("메뉴명");
        nameLabel.setPreferredSize(oneline);
        add(nameLabel);

        nameField.setPreferredSize(longword);
        add(nameField);

        newer.setText("메뉴등록");
        newer.setPreferredSize(longword);
        newer.addActionListener(e -> {
            new FrameSub(user, true);
        });
        add(newer);

        search.setText("조회");
        search.setPreferredSize(shortword);
        search.addActionListener(e -> {
            if (!nameField.getText().equals("")) {
                String query = "select mname, mprice from menu where mname = " + nameField.getText();
                SQLExecutor sqle = new SQLExecutor(query, "select");
                ArrayList<String> result = sqle.getResult();
                StringTokenizer st = new StringTokenizer(result.get(0));
                area.append("메뉴명:\t" + st.nextToken(" ") + "\n");
                area.append("가격:\t" + st.nextToken(" ") + "\n");
            }
        });
        add(search);

        area.setEditable(false);
        area.setPreferredSize(halfsize);
        add(area);
    }

    public ArrayList<String> dataInput(String today) {
        String query = "select due from sales where due = " + today;
        SQLExecutor sqle = new SQLExecutor(query, "select");
        ArrayList<String> result = sqle.getResult();
        if (result.size() == 0) {
            query = "insert into sales values (" + today + ", "", "", 0)";
            sqle = new SQLExecutor(query, "insert");
        }
        query = "select due from sales";
        sqle.SQLExecutor(query, "select");
        result = sqle.getResult();
        return result;
    }
}
