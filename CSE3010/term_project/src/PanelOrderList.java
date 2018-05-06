import java.util.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelOrderList extends JPanel {
    String[] combo = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    Calendar cal = new Calendar.getInstance();
    String today = String.valueOf(cal.get(Calendar.YEAR)) + "-" + String.valueOf(cal.get(Calendar.MONTH)+1) + "-" + String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    JLabel title = new JLabel("주문내역");
    JLabel cnameLabel = new JLabel("고객명");
    JLabel tnameLabel = new JLabel("테이블명");
    JPanel panel = new JPanel();
    JPanel input = new JPanel();
    JTextArea area = new JTextArea();
    JTextField cnameField = new JTextField();
    JComboBox<String> tnameBox = new JComboBox<String>(combo);
    JButton order = new JButton();
    JButton cancel = new JButton();
    JButton payment = new JButton();

    public PanelOrderList(Account user, ArrayList<JLabel> tl) {
        setPreferredSize(new Dimension(280, 245));
        setLayout(new FlowLayout());

        title.setPreferredSize(new Dimension(270, 20));
        add(title);

        area.setPreferredSize(new Dimension(150, 200));
        area.setEditable(false);
        panel.add(area);

        cnameLabel.setPreferredSize(new Dimension(95, 20));
        input.add(cnameLabel);
        cnameField.setPreferredSize(new Dimension(95, 20));
        input.add(cnameField);
        tnameLabel.setPreferredSize(new Dimension(95, 20));
        input.add(tnameLabel);
        input.add(tnameBox);

        order.setText("주문");
        order.addActionListener(e -> {
            SQLExecutor sqle = new SQLExecutor("select tid from ordering where tid = " + getTid(), "select");
            ArrayList<String> result = sqle.getResult();
            if (result.size() != 0) {
                sqle = new SQLExecutor("update ordering set status = 'ed' where status = 'ing' and tid = " + getTid() + "\'", "update");
                tl.get(tnameBox.getSelectedIndex()).setBackground(Color.YELLOW);
            }
        });
        input.add(order);

        cancel.setText("취소");
        cancel.addActionListener(e -> {
            SQLExecutor sqle = new SQLExecutor("delete from ordering where status = 'ing' and tid = " + getTid() + "\'", "update");
        });
        input.add(cancel);

        payment.setText("결제");
        payment.addActionListener(e -> {
            int sum = 0;
            String cname = cnameField.getText();
            String cclass = "";
            SQLExecutor sqle = new SQLExecutor("select tid from ordering where tid = " + getTid(), "select");
            ArrayList<String> result = sqle.getResult();

            if (result.size() != 0) {
                sqle = new SQLExecutor("select sum(mprice) from ordering where tid = \'" + getTid() + "\'", "select");
                result = sqle.getResult();
                StringTokenizer st = new StringTokenizer(result.get(0));
                sum += Integer.valueOf(st.nextToken(" "));

                if (!cname.equals("")){
                    sqle = new SQLExecutor("select cclass from customer where cname = \'" + cname + "\'", "select");
                    result = sqle.getResult();
                    st = new StringTokenizer(result.get(0));
                    cclass = st.nextToken(" ");
                    switch (cclass) {
                        case "Gold": sum *= 0.7; break;
                        case "Silver": sum *= 0.8; break;
                        case "Bronze": sum *= 0.9; break;
                        default: break;
                    }
                    sqle = new SQLExecutor("update customer set ctotal = ctotal + " + String.valueOf(sum) + " where cname = \'" + cname + "\'", "update");
                }
                sqle = new SQLExecutor("update staff set stotal = stotal + " + String.valueOf(sum) + " where sname = \'" + user.getSname() + "\'", "update");

                sqle = new SQLExecutor("select mname from ordering where tid = " + getTid(), "select");
                ArrayList<String> ml = sqle.getResult();
                for (int i = 0; i < ml.size(); i++) {
                    String manuItem = ml.get(i);
                    sqle = new SQLExecutor("select mname from counter where mname = \'" + manuItem + "\'", "select");
                    result = sqle.getResult();
                    if (result.size() == 0) {
                        sqle = new SQLExecutor("insert into counter values (" + today + ", \'" + manuItem + "\', 1)", "insert");
                    } else {
                        sqle = new SQLExecutor("update counter set count = count + 1 where mname = \'" + manuItem + "\'", "update");
                    }
                }

                sqle = new SQLExecutor("select mname from counter where count >= all (select count from counter)", "select");
                result = sqle.getResult();
                String top = result.get(0);
                sqle = new SQLExecutor("select mname from counter where count <= all (select count from counter)", "select");
                result = sqle.getResult();
                String bot = result.get(0);
                sqle = new SQLExecutor("update sales set sale = sale + " + String.valueOf(sum) + " and top = \'" + top "\' and bot = \'" + bot + "\' where due = " + today, "update");


                sqle = new SQLExecutor("delete from ordering where tid = " + getTid(), "delete");
                tl.get(tnameBox.getSelectedIndex()).setBackground(Color.WHITE);
                output(user, area);
            }
        });
        input.add(payment);

        input.setPreferredSize(new Dimension(105, 200));
        input.setLayout(new FlowLayout(2, 5, 3));

        panel.setPreferredSize(new Dimension(270, 210));
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.setLayout(new FlowLayout());
        panel.add(input);
        add(panel);
    }

    public void output(Account user, JTextArea area) {
        boolean ok = false;

        SQLExecutor sqle = new SQLExecutor("select tid from ordering where tid = " + getTid(), "select");
        ArrayList<String> result = sqle.getResult();
        if (result.size() != 0) {
            ok = true;
        }
        if (ok) {
            sqle = new SQLExecutor("select cname from ordering where tid = " + getTid(), "select");
            result = sqle.getResult();
            StringTokenizer st = new StringTokenizer(result.get(0));
            cnameField.setText(st.nextToken(" "));

            sqle = new SQLExecutor("select mname, mprice from ordering where tid = " + getTid(), "select");
            result = sqle.getResult();
            for (int i = 0; i < result.size(); i++) {
                st = new StringTokenizer(result.get(i));
                area.append(st.nextToken(" ") + "\t" + st.nextToken(" ") + "\n");
                area.setCaretPosition(area.getDocument().getLength());
            }

            sqle = new SQLExecutor("select sum(mprice) from ordering where tid = " + getTid(), "select");
            result = sqle.getResult();
            st = new StringTokenizer(result.get(0));
            area.append("-------------------------\n 총합계:\t" + st.nextToken(" "));
        } else {
            area.setText("");
        }
    }

    public String getTid() {
        return tnameBox.getSelectedItem().toString();
    }

    public String getCname() {
        return cnameField.getText();
    }

    public JTextArea getArea() {
        return area;
    }
}
