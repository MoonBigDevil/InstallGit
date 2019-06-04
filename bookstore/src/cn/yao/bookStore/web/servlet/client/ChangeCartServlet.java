package cn.yao.bookStore.web.servlet.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.yao.bookStore.domain.Product;

@WebServlet("/changeCart")
public class ChangeCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到商品
		String id = request.getParameter("id");
		// 得到要改的数量
		int count = Integer.parseInt(request.getParameter("count"));
		// 从session中获取购物车
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		Set<Product> map = cart.keySet();
		// 获取Product
		Iterator<Product> it = map.iterator();
		Product p =null;
		while (it.hasNext()) {
			p = it.next();
			if (id.equals(p.getId())) {
				break;
			}
		}
		System.out.println("Change里获取的Product"+p.toString());
		if (count != 0) {
			cart.put(p, count);
		} else {
			cart.remove(p);
		}
		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
