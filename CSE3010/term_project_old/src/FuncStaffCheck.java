package term_project;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FuncStaffCheck {
    public static Connection connect;
    private String name;
    private String id;
    private String position;
    private int sale;

    public FuncStaffCheck(ObjeStaff usingStaff) {
        this.name = usingStaff.getName();
        this.id = usingStaff.getId();

        connectDB();
        try {
            getStaffInfo();
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "잘못된 SQL문 입니다.", "SQLException", JOptionPane.ERROR_MESSAGE);
        }

        usingStaff = new ObjeStaff(name, id, position, sale);
    }

    private void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connect = DriverManager.getConnection("jdbc:oracle:thin: " + "@localhost:1521:XE", name, id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "직원이 아닙니다.", "로그인 오류", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "예외발생", "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getStaffInfo() throws SQLException {
        String query = "select * from Staff where name = " + name + " and id = " + id + ";";
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            this.position = rs.getString("position");
            this.sale = rs.getInt("sale");
        }

        rs.close();
        stmt.close();
    }
}
