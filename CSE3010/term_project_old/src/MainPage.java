package term_project;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainPage extends JFrame {
    private ArrayList<ObjeTable> tableList = new ArrayList<ObjeTable>();
    private ArrayList<ObjeMenu> orderList = new ArrayList<ObjeMenu>();
    private ObjeStaff usingStaff = new ObjeStaff("", "", "", 0);

    public MainPage() {
        tableReset(tableList);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("식당 관리 시스템");
        setSize(575,705);
        setResizable(false);
        setLayout(new FlowLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new MenuListener());
        menu.add(open);
        JMenuItem login = new JMenuItem("Log in");
        login.addActionListener(new MenuListener());
        menu.add(login);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Program title
        JLabel title = new JLabel("식당 주문관리");
        title.setFont(new Font("", Font.BOLD, 40));
        title.setBorder(new LineBorder(Color.BLACK, 1));
        title.setPreferredSize(new Dimension(565, 60));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        PanelTableStatus panelTableStatus = new PanelTableStatus(tableList);
        PanelBill panelBill = new PanelBill(tableList, orderList, usingStaff);
        PanelMenu panelMenu = new PanelMenu(orderList, panelBill);
        PanelSignRef panelSignRef = new PanelSignRef(usingStaff);
        add(panelTableStatus);
        add(panelBill);
        add(panelMenu);
        add(panelSignRef);

        setVisible(true);
    }

    public class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch(cmd) {
                case "Open":
                    break;
                case "Log in":
                    new WindowLogin(usingStaff);
                    break;
            }
        }
    }

    public void tableReset(ArrayList<ObjeTable> tableList) {
        for (int i = 0; i < 21; i++) {
            tableList.add(new ObjeTable(i+1));
        }
    }
}
