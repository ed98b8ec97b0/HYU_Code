package term_project;

import java.awt.*;
import javax.swing.*;

public class ObjeMenu extends JButton {
    private String name, id;
    private int price;

    public ObjeMenu(String name, String id, int price) {
        this.name = name;
        this.id = id;
        this.price = price;
        setPreferredSize(new Dimension(120, 25));
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }
}
