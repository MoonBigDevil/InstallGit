package cn.yao.bookStore.service;

import java.sql.SQLException;

import cn.yao.bookStore.dao.OrderDao;
import cn.yao.bookStore.domain.Order;
import cn.yao.bookStore.exception.AddOrderException;

public class OrderService {
	private OrderDao dao = new OrderDao();

	// 提供添加订单服务
	public void addOrder(Order order) throws AddOrderException {
		try {
			dao.addOrder(order);
			if(order.getMoney() == 0) {
				throw new AddOrderException("您还没有购买任何东西");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddOrderException("添加订单失败");
		}

	}
	// 支付成功后修改订单状态
		public void updateState(String id) {
			try {
				dao.updateOrderState(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	//
	
}
