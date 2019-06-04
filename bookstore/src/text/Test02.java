package text;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import cn.yao.bookStore.domain.User;
import cn.yao.bookStore.utils.DataSourceUtils;

public class Test02 {

	@Test
	public void fun1() throws SQLException {
		String sql = "select * from user where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		User user= runner.query(sql, new BeanHandler<User>(User.class),"5036");
		System.out.println(user);
	}
}
