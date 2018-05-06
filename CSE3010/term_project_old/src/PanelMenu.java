package term_project;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelMenu extends JPanel {
    ArrayList<ObjeMenu> buttonList = new ArrayList<ObjeMenu>();

    public PanelMenu(ArrayList<ObjeMenu> orderList, PanelBill panelBill) {

        resetButtonList();
        setPreferredSize(new Dimension(280, 365));
        setLayout(new FlowLayout());

        JLabel title = new JLabel("메뉴");
        title.setPreferredSize(new Dimension(270, 20));
        add(title);

        JPanel board = new JPanel();
        board.setPreferredSize(new Dimension(270, 305));
        board.setBorder(new LineBorder(Color.BLACK, 1));
        board.setLayout(new FlowLayout(1, 10, 5));
        new FuncButtonMaker(buttonList);
        for (int i = 0; i < 20; i++) {
            ObjeMenu button = buttonList.get(i);
            button.setText(button.getName());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    orderList.add(button);
                    panelBill.viewConsole(panelBill.getTable());
                }
            });
            board.add(buttonList.get(i));
        }
        add(board);
    }

    public void resetButtonList() {
        for (int i = 0; i < 20; i++) {
            buttonList.add(new ObjeMenu("", "", 0));
        }
    }
}
