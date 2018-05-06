import java.io.*;
import java.util.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.border.*;

public class FrameMain extends JFrame {
    Account user = new Account();
    ArrayList<JLabel> tl = new ArrayList<JLabel>();
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem open = new JMenuItem("Open");
    JMenuItem login = new JMenuItem("Log In");
    JLabel title = new JLabel("식당 주문관리");
    File file;

    public FrameMain() {
        user.setAccount("test", "0000", "Supervisor");
        for (int i = 0; i < 20; i++) {
            JLabel t = new JLabel(String.valueOf(i+1));
            t.setBorder(new LineBorder(Color.BLACK, 1));
            t.setBackground(Color.WHITE);
            t.setOpaque(true);
            t.setHorizontalAlignment(JLabel.CENTER);
            t.setPreferredSize(new Dimension(45, 45));
            tl.add(t);
        }

        PanelTableStatus pts = new PanelTableStatus(tl);
        PanelOrderList pol = new PanelOrderList(user, tl);
        PanelMenu pm = new PanelMenu(user, pol);
        PanelControl pc = new PanelControl(user);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("식당 관리 시스템");
        setSize(600, 720);
        setResizable(false);
        setLayout(new FlowLayout());

        open.addActionListener(e -> {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 파일", "txt");
            JFileChooser fc = new JFileChooser();
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String[] splitedStr = null;
                String[] dataTable = {"customer", "staff", "menu"};
                Stirng[] dataId = {"cid", "sid", "mid"};
                String line = null;
                int k = 0, end = 0;
                while ((line = br.readLine()) != null) {
                    splitedStr = 0;
                    splitedStr = line.split("\t");
                    end = Integer.valueOf(splitedStr[0]);
                    for (int i = 0; i < end; i++) {
                        line = br.readLine();
                        splitedStr = line.split("\t");
                        String id = "";
                        boolean ok;
                        do {
                            Random r = new Random();
                            ok = true;
                            for (int i = 0; i < 4; i++) {
                                id += String.valueOf(r.nextInt(10));
                            }
                            query = "select " + dataId[k] + " from " + dataTable[k] + " where " + dataId[k] + " = \'" + id + "\'";
                            sqle = new SQLExecutor(query, "select");
                            ArrayList<String> result = sqle.getResult();
                            if (result.size() != 0) {
                                ok = false;
                            }
                        } while (ok == false);
                        if (k == 0) {
                            query = "insert into customer values (\'" + splitedStr[0] + "\', \'" + id + "\', \'" + splitedStr[1] + "\', \'" + splitedStr[2] + "\', \'" + splitedStr[3] + "\', 0)";

                        } else if (k == 1) {
                            query = "insert into staff values (\'" + splitedStr[0] + "\', \'" + id + "\', \'" + splitedStr[1] + "\', 0)";
                        } else if (k == 2) {
                            query = "insert into customer values (\'" + splitedStr[0] + "\', \'" + id + "\', " + splitedStr[1] + ")";
                        }
                        sqle = new SQLExecutor(query, "insert");
                    }
                }
            }
        });
        menu.add(open);

        login.addActionListener(e -> {
                new FrameSub(user, 1);
        });
        menu.add(login);

        menubar.add(menu);
        setJMenuBar(menubar);

        title.setFont(new Font("", Font.BOLD, 40));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new LineBorder(Color.BLACK, 1));
        title.setPreferredSize(new Dimension(565, 60));
        add(title);

        add(pts);
        add(pol);
        add(pm);
        add(pc);

        setVisible(true);
    }
}
