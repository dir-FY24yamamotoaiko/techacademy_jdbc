package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TryWithResourceSample01 {

	public static void main(String[] args) {
		try {
			//ドライバのクラスをJava上で読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			//SERECT文の実行と結果の格納・代入
			String sql = "SELECT * FROM country LIMIT 50";
			
			//try-with-resource構文
			try(//DBと接続する
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true", "root",
							"B-ken13guitarrinrin");
					
				//DBとやり取りする窓口(Statementオブジェクト)の作成
				Statement stmt = con.createStatement();
					
				//SQLを発行
				ResultSet rs = stmt.executeQuery(sql);){
					
				//結果を表示する
				while (rs.next()) {
					//Name列の値を取得
					String name = rs.getString("Name");
					//取得した値を表示
					System.out.println(name);
				}
				}
		}catch (ClassNotFoundException e) {
			System.err.println("JDBCドライバーのロードに失敗しました。");
			e.printStackTrace();
		}catch (SQLException e) {
			System.err.println("データベースに異常が発生しました。");
			e.printStackTrace();
		}
	}

}
