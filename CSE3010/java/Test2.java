import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test2 {
	private String username="db2017";
	private String password="database";
	private static Connection dbTest;

	public Test2() {
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
		// 문제 1번.
		System.out.println("문제1. CD가 '8x'이고 RAM이 24이상인 PC의 제조업체, 모델, 가격을 구하시오.");

		String sqlStr = "SELECT maker, model, price FROM product NATURAL JOIN pc WHERE type='pc' and cd='8x' and ram>=24";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();

		System.out.println("MAKER               MODEL       PRICE");
		System.out.println("------------------- ----------- -----------");
		while(rs.next()) {
			System.out.println(rs.getString("maker") + rs.getString("model") + "\t" + rs.getString("price"));
		}
		System.out.println();

		// 문제 2번.
		System.out.println("문제2. SCREEN이 11초과이고 제조업체가 'D'나 'G'인 LAPTOP의 총 합계를 구하시오.");

		sqlStr = "SELECT sum(price) FROM laptop NATURAL JOIN product WHERE type='laptop' and (maker='D' or maker='G') and screen>11";
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();

		System.out.println("SUM(PRICE)");
		System.out.println("-----------");
		while(rs.next()) {
			System.out.println(rs.getString("sum(price)"));
		}
		System.out.println();

		// 문제 3번.
		System.out.println("문제3. PC의 hd가 2.4 초과인 것과 laptop의 speed가 130 초과인 모델의 총 개수를 구하시오.");

		sqlStr = "select count(model) from ((SELECT model FROM pc WHERE hd>2.4) UNION (SELECT model FROM laptop WHERE speed>130))";
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();

		System.out.println("COUNT(MODEL)");
		System.out.println("-----------");
		while(rs.next()) {
			System.out.println(rs.getString("count(model)"));
		}
		System.out.println();

		// 문제 4번.
		System.out.println("문제4. CD가 '8x'이고 PC중 한 개 이상의 LAPTOP보다 SPEED가 큰 모델번호와 가격을 구하시오.");

		sqlStr = "SELECT model, price FROM pc WHERE cd='8x' and speed>some(select speed from laptop) order by model";
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();

		System.out.println("MODEL   PRICE");
		System.out.println("------- ------------");
		while(rs.next()) {
			System.out.println(rs.getString("model") + "\t" + rs.getString("price"));
		}
		System.out.println();

		// 문제 5번.
		System.out.println("문제5. 적어도 1GB 용량을 지닌 하드 디스크가 내장된 랩탑의 속도와 그 제조업체를 구하라.");

		sqlStr = "select maker, speed from laptop natural join product where type='laptop' and hd >= 1";
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();

		System.out.println("MAKER               SPEED");
		System.out.println("------------------- -----------");
		while(rs.next()) {
			System.out.println(rs.getString("maker") + rs.getString("speed"));
		}
		System.out.println();

		// 문제 6번.
		System.out.println("문제6. 지금 model 2005 제품을 가지고 있는데, 이보다 더 속도가 빠른 PC나 LAPTOP을 사려고 한다. 어떤 제품들이 있는가??");

		sqlStr = "select model from ((select model, speed from pc natural join product where type='pc') union (select model, speed from laptop natural join product where type='laptop')) where speed>some(select speed from ((select model, speed from pc natural join product where type='pc') union (select model, speed from laptop natural join product where type='laptop')) where model='2004')";
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();

		System.out.println("MODEL");
		System.out.println("-----------");
		while(rs.next()) {
			System.out.println(rs.getString("model"));
		}
		System.out.println();

		// 문제 7번.
		System.out.println("문제7. 'D' 제조업체의 PRINTER 중 컬러 출력이 가능한 제품의 모델과 타입, 가격을 보여라.");

		sqlStr = "select model, type, price from printer natural join (select model from product where type='printer' and maker='D') where color='true'";
		stmt = dbTest.prepareStatement(sqlStr);
		rs = stmt.executeQuery();

		System.out.println("MODEL   TYPE                PRICE");
		System.out.println("------- ------------------- ----------");
		while(rs.next()) {
			System.out.println(rs.getString("model") + "\t" + rs.getString("type")  + rs.getString("price"));
		}
		System.out.println();

		rs.close();
		stmt.close();
	}

	public static void main(String[] args) {
		Test2 t2 = new Test2();
		try {
			t2.execute_query();
			dbTest.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
		}
	}
}