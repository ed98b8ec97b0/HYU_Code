package term_project;

import java.util.*;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FuncPayment {
    public static Connection connect;
    private String name, id, customerName;
    private int sum, sale;
    private boolean existing = false;
    private Calendar cal = Calendar.getInstance();

    public FuncPayment(String customerName, int sum, ObjeStaff usingStaff) {
        this.sum = sum;
        this.name = usingStaff.getName();
        this.id = usingStaff.getId();
        this.sale = usingStaff.getSale();

        connectDB();
        try {
            findCustomer();
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }

        if (existing == true) {
            try {
                payment();
                connect.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connect = DriverManager.getConnection("jdbc:oracle:thin: " + "@localhost:1521:XE", name, id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "로그인을 해주세요.", "로그인 오류", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "예외발생", "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findCustomer() throws SQLException {
        String query = "select name from Customer where name = " + customerName + ";";
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            if (rs.getString("name").equals(customerName)) {
                existing = true;
            }
        }
        if (existing == false) {
            JOptionPane.showMessageDialog(null, "등록되지 않은 고객입니다.", "고객 조회 오류", JOptionPane.ERROR_MESSAGE);
        }

        rs.close();
        stmt.close();
    }

    public void payment() throws SQLException {
        String level = "";
        int customerSale = 0;

        String query = "select sale, level from Customer where name = " + customerName + ";";
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            customerSale = rs.getInt("sale");
            level = rs.getString("level");
        }
        switch (level) {
            case "Bronze":
                sum *= 0.9;
                break;
            case "Silver":
                sum *= 0.8;
                break;
            case "Gold":
                sum *= 0.7;
                break;
        }
        customerSale += sum;
        sale += sum;

        query = "update Customer set sale = " + customerName + " where name = " + customerName + ";";
        stmt = connect.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next());

        query = "update Staff set sale = " + sale + " where name = " + name + ";";
        stmt = connect.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next());

        rs.close();
        stmt.close();
    }
}
