package term_project;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelBill extends JPanel {
    ArrayList<ObjeTable> tableList;
    JTextArea console = new JTextArea();
    String[] index = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    JComboBox list = new JComboBox(index);

    public PanelBill(ArrayList<ObjeTable> tableList, ArrayList<ObjeMenu> orderList, ObjeStaff usingStaff) {
        this.tableList = tableList;
        setPreferredSize(new Dimension(280, 245));
        setLayout(new FlowLayout());

        JLabel title = new JLabel("주문내역");
        title.setPreferredSize(new Dimension(270, 20));
        add(title);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(270, 210));
        panel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        console.setPreferredSize(new Dimension(135, 190));
        console.setOpaque(true);
        console.setBackground(Color.WHITE);
        panel.add(console);

        JPanel handle = new JPanel();
        handle.setPreferredSize(new Dimension(105, 190));
        handle.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 3));

        JLabel name = new JLabel("고객명");
        name.setPreferredSize(new Dimension(100, 20));
        handle.add(name);

        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(100, 20));
        handle.add(input);

        JLabel table = new JLabel("테이블명");
        table.setPreferredSize(new Dimension(100, 20));
        handle.add(table);

        list.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = list.getSelectedIndex();
                viewConsole(tableList.get(id));
            }
        });
        handle.add(list);

        JButton order = new JButton("주문");
        order.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = input.getText();
                int id = list.getSelectedIndex();
                tableList.get(id).setName(name);
                tableList.get(id).addOrder(orderList);
                orderList.clear();
            }
        });
        handle.add(order);

        JButton cancel = new JButton("취소");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = list.getSelectedIndex();
                orderList.clear();
            }
        });
        handle.add(cancel);

        JButton pay = new JButton("결제");
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sum = 0;
                int id = list.getSelectedIndex();
                String customerName = input.getText();

                ArrayList<ObjeMenu> orderNow = tableList.get(id).getOrderList();
                for (int i = 0; i < orderNow.size(); i++) {
                    sum += orderNow.get(i).getPrice();
                }
                new FuncPayment(customerName, sum, usingStaff);
                tableList.get(id).offTable();
            }
        });
        handle.add(pay);

        panel.add(handle);
        add(panel);
    }

    public JTextArea getConsole() {
        return console;
    }

    public void viewConsole(ObjeTable table) {
        int sum = 0;
        ArrayList<ObjeMenu> orderNow = table.getOrderList();
        String output = "";

        for (int i = 0; i < orderNow.size(); i++) {
            output += orderNow.get(i).getName() + "\t" + orderNow.get(i).getPrice() + "\n";
            sum += orderNow.get(i).getPrice();
        }
        output += "\n\n\n----------------------------------------\n";
        output = output + "총 합계: " + String.valueOf(sum);
        console.setText(output);
    }

    public ObjeTable getTable() {
        int id = list.getSelectedIndex();
        return tableList.get(id);
    }
}
