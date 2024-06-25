package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnectionUtil {
	private Properties properties;

	public DBConnectionUtil() {
		// 加载.properties文件
		properties = new Properties();
		InputStream input = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream("db.properties");
			// 从输入流加载属性列表
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getConnectionURL() {
		return properties.getProperty("db.pro") + properties.getProperty("db.serverName") + ":"
				+ properties.getProperty("db.port") + "/" + properties.getProperty("db.databaseName");
	}

	public Connection getConnection() {
		// 加载JDBC驱动（确保这个步骤在尝试建立连接之前执行）
		// 通常，这一步在类的静态初始化代码块中完成，但在这里为了简单起见，我们直接在方法中执行
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 使用getConnectionURL()方法获取完整的JDBC URL
		String url = getConnectionURL();
		Connection connection = null;
		// 使用DriverManager建立连接
		try {
			connection = DriverManager.getConnection(url, properties.getProperty("db.loginName"), properties.getProperty("db.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}