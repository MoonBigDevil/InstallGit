package cn.yao.bookStore.web.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.yao.bookStore.domain.Order;
import cn.yao.bookStore.domain.OrderItem;
import cn.yao.bookStore.domain.Product;
import cn.yao.bookStore.domain.User;
import cn.yao.bookStore.exception.AddOrderException;
import cn.yao.bookStore.service.OrderItemService;
import cn.yao.bookStore.service.OrderService;
import cn.yao.bookStore.service.ProductService;
import cn.yao.bookStore.utils.IdUtils;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateOrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到当前用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			request.setAttribute("user_null", "登录已过期，请重新登录");
			//request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/client/login.jsp");
			return;
		}
		
		// 获取购物车
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

		try {
			if (cart == null) {
				throw new AddOrderException("购物车为空");
				
			}
		} catch (AddOrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("create_order", e.getMessage());
			request.getRequestDispatcher("/client/order.jsp").forward(request, response);
		}

		// 将获取的数据封装到Order对象中
		Order order = new Order();
		System.out.println(request.getParameterMap());
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		order.setId(IdUtils.getUUID());
		order.setUser(user);

		for (Product p : cart.keySet()) {
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setP(p);
			item.setBuynum(cart.get(p));
			order.getOrderItems().add(item);
		}

		// 调用service中的方法，添加订单
		OrderService service = new OrderService();
		try {
			service.addOrder(order);
		} catch (AddOrderException e) {
			e.printStackTrace();
			
		}
		OrderItemService service2  = new OrderItemService();
		try {
			service2.addOrderItem(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ProductService service3 = new ProductService();
		try {
			service3.changeProductNum(order);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//清空购物车
		session.setAttribute("cart", null);
		response.sendRedirect(request.getContextPath() + "/client/createOrderSuccess.jsp");
	}

}
