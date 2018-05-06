import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelMenu extends JPanel {
    JLabel title = new JLabel("메뉴");
    JPanel panel = new JPanel();
    ArrayList<JButton> bl = new ArrayList<JButton>();

    public PanelMenu(Account user, PanelOrderList pol) {
        setPreferredSize(new Dimension(280, 365));
        setLayout(new FlowLayout());

        title.setPreferredSize(new Dimension(270, 20));
        add(title);
        for (int i = 0; i < 20; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(120, 25));
            button.addActionListener(e -> {
                if (!button.getText().equals("")) {
                    SQLExecutor sqle = new SQLExecutor("insert into ordering values (" + pol.getTid() + ", \'" + pol.getCname() + "\', \'" + button.getText() + "\', " + button.getName() + ", \'ing\')", "insert");
                    pol.output(user, pol.getArea());
                }
            });
            bl.add(button);
        }
        for (int i = 0; i < 20; i++) {
            panel.add(bl.get(i));
        }
        reloadButton();
        panel.setPreferredSize(new Dimension(270, 305));
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.setLayout(new FlowLayout());
        add(panel);
    }

    public void reloadButton() {
        SQLExecutor sqle = new SQLExecutor("select mname, mprice from menu", "select");
        ArrayList<String> result = sqle.getResult();
        for (int i = 0; i < result.size(); i++) {
            StringTokenizer st = new StringTokenizer(result.get(i));
            bl.get(i).setText(st.nextToken(" "));
            bl.get(i).setName(st.nextToken(" "));
        }
    }
}
