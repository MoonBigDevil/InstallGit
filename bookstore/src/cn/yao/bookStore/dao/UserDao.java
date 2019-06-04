package cn.yao.bookStore.dao;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.yao.bookStore.domain.User;
import cn.yao.bookStore.utils.DataSourceUtils;

public class UserDao {
	//添加用户
	public void addUser(User user) throws SQLException {
		String sql = "insert into user(username,password,gender,email,telephone,introduce,"
				+ "activeCode) values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,user.getUsername(),user.getPassword(),user.getGender()
				,user.getEmail(),user.getTelephone(),user.getIntroduce(),user.getActiveCode()
				);
		if(row == 0) {
			System.out.println("添加失败");
			throw new RuntimeException();
		}
	
	}
	//根据激活码查找用户
	public User findUserByActiveCode(String activeCode) throws SQLException {
		String sql = "select * from user where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		User user= runner.query(sql, new BeanHandler<User>(User.class), activeCode);
		return user;
	}
	//激活用户：修改用户激活状态
	public void activeUser(String activeCode) throws SQLException {
		String sql = "update user set state=?  where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//				.parse(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
//		java.sql.Timestamp time=new java.sql.Timestamp(date.getTime());
		int row = runner.update(sql, 1,activeCode);
		if(row==0) {
			System.out.println("修改状态失败");
		}
	}
	
	//按照用户名和密码查找用户
	public User findUserByUsernameAndPassword(String username,String password) throws SQLException {
		String sql = "select * from user where username=? and password=?";
		QueryRunner runner  = new QueryRunner(DataSourceUtils.getDataSource());
		User user = runner.query(sql, new BeanHandler<User>(User.class),username,password);
		return user;
	}
}
