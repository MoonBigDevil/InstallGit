package cn.yao.bookStore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.yao.bookStore.domain.Order;
import cn.yao.bookStore.utils.DataSourceUtils;

public class OrderDao {
	
	//添加订单
	public void addOrder(Order order ) throws SQLException {
		String sql = "insert into orders(id,money,receiverAddress,receiverName,receiverPhone,"
				+ "paystate,user_id) values(?,?,?,?,?,0,?);";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,order.getId(),order.getMoney(),order.getReceiverAddress(),
				order.getReceiverName(),order.getReceiverPhone(),order.getUser().getId());
		if(row == 0) {
			System.out.println("添加失败");
			throw new RuntimeException();
			
		}
	}
	// 根据订单号修改订单状态
		public void updateOrderState(String id) throws SQLException {
			String sql = "update orders set paystate=1 where id=?";

			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

			runner.update(sql, id);
		}
}
