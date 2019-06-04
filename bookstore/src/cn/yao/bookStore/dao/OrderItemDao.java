package cn.yao.bookStore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.yao.bookStore.domain.Order;
import cn.yao.bookStore.domain.OrderItem;
import cn.yao.bookStore.domain.Product;
import cn.yao.bookStore.utils.DataSourceUtils;

public class OrderItemDao {
	
	//添加订单数据
	public void addOrderItem(Order order) throws SQLException {
		String sql = "insert into orderItem values(?,?,?);";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<OrderItem> items = order.getOrderItems();
		Object[][] params = new Object[items.size()][3];

//		String sql2 = "update products set pnum=? where id=?;";
//		String sql3 = "select * from products where id =?;";
		
		for (int i = 0; i < params.length; i++) {
			params[i][0] = items.get(i).getOrder().getId();
			params[i][1] = items.get(i).getP().getId();
			params[i][2] = items.get(i).getBuynum();
			
//			String pid = (String) params[i][1];
//			int bn =  (int) params[i][2];
//			
//			Product product = runner.query(sql3, new BeanHandler<Product>(Product.class),pid);
//			System.out.println("Product:"+product);
//			int pnum = product.getPnum();
//			runner.update(sql2,pnum-bn, pid);
		}

		runner.batch(sql, params);
	}
	
}
