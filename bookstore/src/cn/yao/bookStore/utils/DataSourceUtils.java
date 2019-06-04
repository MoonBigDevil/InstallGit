package cn.yao.bookStore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DataSourceUtils {
	private static DataSource dSource = new ComboPooledDataSource("config02");
	private static ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();

	// 获取DataSource
	public static DataSource getDataSource() {
		return dSource;
	}

	// 获取连接
	/**
	 * 当DBUtils需要手动控制事务时，调用该方法获得一个连接
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection connection = t1.get();
		if (connection == null) {
			connection = (Connection) dSource.getConnection();
			t1.set(connection);
		}
		return connection;
	}

	/**
	  * 开启事务
	 * @throws SQLException 
	 * 
	 */
	public static void startTransaction() throws SQLException {
		Connection connection = getConnection();
		if(connection  != null) {
			connection.setAutoCommit(false);
		}
	}
	
	/**
	 *从ThreadLocal中释放并关闭Connection，并结束事务 
	 * @throws SQLException 
	 */
	public static void releaseAndCloseConnection() throws SQLException {
		Connection connection = getConnection();
		if(connection  != null ) {
			connection.commit();
			t1.remove();
			connection.close();
		}
	}
	
	/**
	 * 事务回滚
	 * @throws SQLException 
	 */
	public static void rollback() throws SQLException {
		Connection  connection = getConnection();
		if(connection != null) {
			connection.rollback();
		}
	}

}
