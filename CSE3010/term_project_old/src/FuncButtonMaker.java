package term_project;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class FuncButtonMaker {
    public static Connection connect;

    public FuncButtonMaker(ArrayList<ObjeMenu> buttonList) {
        connectDB();
        try {
            buttonInstall(buttonList);
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connect = DriverManager.getConnection("jdbc:oracle:thin: " + "@localhost:1521:XE", "rps", "rps");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "입력이 잘못되었습니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "예외발생", "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buttonInstall(ArrayList<ObjeMenu> buttonList) throws SQLException {
        int i = 1;
        String query = "select name, id, price from Menu";
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            buttonList.add(new ObjeMenu(rs.getString("name"), rs.getString("id"), rs.getInt("price")));
        }

        rs.close();
        stmt.close();
    }
}
