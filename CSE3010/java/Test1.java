import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test1 {
	private String username="db2017";
	private String password="database";
	private static Connection dbTest;

	public Test1() {
		connectDB();
	}

	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", username, password);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
		} catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	public void execute_query() throws SQLException {
		String dashline = "-------------------------";
		// 문제 1번.
		String sqlStr = "SELECT avg(speed) FROM pc";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();

		System.out.println("문제1. pc의 평균 속력을 구하라.");
		while(rs.next()) {
			System.out.println("avg(speed): " + rs.getString("avg(speed)"));
		}
		System.out.println(dashline);

		// 문제 2번.
		sqlStr = "SELECT price FROM pc WHERE price >= 2000";
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();

		System.out.println("문제2. pc에서 price가 2000이상인 가격을 구하라.");
		while(rs.next()) {
			System.out.println("price : " + rs.getString("price"));
		}
		System.out.println(dashline);

		// 문제 3번.
		sqlStr = "SELECT model, speed, hd FROM pc WHERE (cd = '6x' or cd = '8x') and price < 2000";
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();

		System.out.println("문제3. 6배속이나 8배속의 CD를 가지고 있으며 가격이 $2000미만인 PC들의 모델 번호, 속도, 하드 디스크 용량을 구하라.");
		while(rs.next()) {
			System.out.println("model : " + rs.getString("model") + " | speed : " + rs.getString("speed") + " | hd : " + rs.getString("hd"));
		}

		rs.close();
		stmt.close();
	}

	public static void main(String[] args) {
		Test1 t1 = new Test1();
		try {
			t1.execute_query();
			dbTest.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
		}
	}
}