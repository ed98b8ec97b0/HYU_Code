package term_project;

import java.util.*;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FuncRegist {
    public static Connection connect;
    private String id;
    private String staffName;
    private String staffId;

    public FuncRegist(ObjeCustomer obje, ObjeStaff usingStaff) {
        staffName = usingStaff.getName();
        staffId = usingStaff.getId();

        connectDB();
        try {
            idMaker("Customer");
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }
        try {
            regist(obje);
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }
    }

    public FuncRegist(ObjeStaff obje, ObjeStaff usingStaff) {
        connectDB();
        try {
            idMaker("Staff");
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }
        try {
            regist(obje);
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }
    }

    public FuncRegist(ObjeMenu obje, ObjeStaff usingStaff) {
        connectDB();
        try {
            idMaker("Menu");
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }
        try {
            regist(obje);
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connect = DriverManager.getConnection("jdbc:oracle:thin: " + "@localhost:1521:XE", staffName, staffId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "로그인을 해주세요.", "로그인 오류", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "예외발생", "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void regist(ObjeCustomer obje) throws SQLException {
        String query = "insert into Customer values (\"" + obje.getName() + "\", \"" + id + "\", \"" + obje.getBirth() + "\", \"" + obje.getPhone() + "\", \"" + obje.getLevel() + "\", " + obje.getSale() + ");";
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next());

        rs.close();
        stmt.close();
    }

    public void regist(ObjeStaff obje) throws SQLException {
        String query = "insert into Customer values (\"" + obje.getName() + "\", \"" + id + "\", \"" + obje.getPosition() + "\", " + obje.getSale() + ");";
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next());

        rs.close();
        stmt.close();
    }

    public void regist(ObjeMenu obje) throws SQLException {
        String query = "insert into Customer values (\"" + obje.getName() + "\", \"" + id + "\", " + obje.getPrice() + ");";
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next());

        rs.close();
        stmt.close();
    }

    public void idMaker(String table) throws SQLException {
        Random r = new Random();
        boolean overlap;

        do {
            id = "";
            overlap = false;
            for (int i = 0; i < 4; i++) {
                id += String.valueOf(r.nextInt(10));
            }

            String query = "select id from " + table + " where id = " + id + ";";
            PreparedStatement stmt = connect.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                if (rs.getString("id").equals(id)) {
                    overlap = true;
                }
            }
        } while (overlap == false);
    }
}
