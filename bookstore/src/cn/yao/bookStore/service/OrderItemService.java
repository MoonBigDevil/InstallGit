package cn.yao.bookStore.service;

import java.sql.SQLException;

import cn.yao.bookStore.dao.OrderItemDao;
import cn.yao.bookStore.domain.Order;

public class OrderItemService {
	private OrderItemDao dao = new OrderItemDao();
	
	public void addOrderItem(Order order) throws SQLException {
		dao.addOrderItem(order);
		
	}
}
