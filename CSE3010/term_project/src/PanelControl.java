import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelControl extends JPanel {
    JLabel title = new JLabel("등록/조회");
    JPanel panel = new JPanel();
    JTabbedPane tab = new JTabbedPane();

    public PanelControl(Account user) {
        setPreferredSize(new Dimension(280, 365));
        setLayout(new FlowLayout());

        title.setPreferredSize(new Dimension(270, 20));
        add(title);

        tab.setPreferredSize(new Dimension(260, 295));
        tab.addTab("고객", new TabPanel(user, 1));
        tab.addTab("매출", new TabPanel(user, 1.0));
        tab.addTab("직원", new TabPanel(user, "1"));
        tab.addTab("메뉴", new TabPanel(user, true));
        panel.add(tab);

        panel.setPreferredSize(new Dimension(270, 305));
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.setLayout(new FlowLayout());
        add(panel);
    }
}
