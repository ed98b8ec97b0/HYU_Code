package term_project;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ObjeTable extends JLabel{
    private String name = "";
    private int id;
    private boolean use = false;
    private ArrayList<ObjeMenu> orderNow = new ArrayList<ObjeMenu>();

    public ObjeTable(int id) {
        this.id = id;
        setBackground(Color.WHITE);
        setText(String.valueOf(id));
        setOpaque(true);
        setPreferredSize(new Dimension(45, 45));
        setBorder(new LineBorder(Color.BLACK, 1));
        setHorizontalAlignment(JLabel.CENTER);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void offTable() {
        this.name = "";
        this.use = false;
        orderNow = new ArrayList<ObjeMenu>();
        setBackground(Color.WHITE);
    }

    public void addOrder(ArrayList<ObjeMenu> orderList) {
        this.use = true;
        for (int i = 0; i < orderList.size(); i++) {
            orderNow.add(orderList.get(i));
        }
        setBackground(Color.YELLOW);
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public boolean getUse() {
        return this.use;
    }

    public ArrayList<ObjeMenu> getOrderList() {
        return this.orderNow;
    }
}
