import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLExecutor {
    private ArrayList<String> result;
    private String username = "termp";
    private String password = "roject";
    public static Connection connect;

    public SQLExecutor(String query, String opt) {
        connectDB();
        if (opt.equals("select")) {
            try {
                runSelect(query);
                connect.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "SQLException", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                runChange(query);
                connect.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "SQLException", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", username, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL문이 잘못됐습니다.", "SQL 오류", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "예외발생", "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void runSelect(String query) throws SQLException {
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int num = rsmd.getColumnCount();

        String out = "";
        result = new ArrayList<String>();
        while(rs.next()) {
             for (int i = 1; i <= num; i++) {
                 out += rs.getString(i);
                 out += " ";
             }
             result.add(out);
        }

        rs.close();
        stmt.close();
    }

    public void runChange(String query) throws SQLException {
        PreparedStatement stmt = connect.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        
        rs.close();
        stmt.close();
    }

    public ArrayList<String> getResult() {
        return this.result;
    }

}
