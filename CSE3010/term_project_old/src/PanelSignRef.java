package term_project;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelSignRef extends JPanel {
    public PanelSignRef(ObjeStaff usingStaff) {
        setPreferredSize(new Dimension(280, 365));
        setLayout(new FlowLayout());

        JLabel title = new JLabel("등록/조회");
        title.setPreferredSize(new Dimension(270, 20));
        add(title);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(270, 305));
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.setLayout(new FlowLayout());

        JTabbedPane tab = new JTabbedPane();
        tab.setPreferredSize(new Dimension(260, 295));
        tab.addTab("고객", new TabCustomer(usingStaff));
        tab.addTab("매출", new TabSale(usingStaff));
        tab.addTab("직원", new TabStaff(usingStaff));
        tab.addTab("메뉴", new TabMenu(usingStaff));
        panel.add(tab);

        add(panel);
    }
}
