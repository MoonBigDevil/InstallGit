package cn.yao.bookStore.web.servlet.client;

import java.io.IOException;
import java.util.HashMap;
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
import cn.yao.bookStore.exception.FindProductByIdException;
import cn.yao.bookStore.service.ProductService;

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1\获取商品id
		String id = request.getParameter("id");
		//2\调用service方法，根据id查找商品
		ProductService service = new ProductService();
		try {
			Product product = service.findProductById(id);
			//获取session，然后从session中获取cart，如果没有就创建
			HttpSession session = request.getSession();
			Map<Product,Integer> cart =  (Map<Product, Integer>) session.getAttribute("cart");
			if(cart == null) {
				//如果cart为空，证明没有购物车，所以要创建购物车
				cart =  new HashMap<Product,Integer>();
				session.setAttribute("cart", cart);
			}
			//有购物车，判断商品是否在购物车在存在
			//这个时候product是键，1是值，put返回的是与product（键）关联的旧值,没有返回null
			Integer count =  cart.put(product,1);
			//判断count是否为空
			System.out.println("AddCartServlet判断count是否为空:"+count);
			if(count != null) {
				//证明购物车存在这个商品
				cart.put(product,count+1);
				//把该商品的数量+1
			}
			session.setAttribute("cart", cart);
			//测试
			session.getAttribute("cart");
			Set<Product> map = cart.keySet();
			 
			Iterator<Product> it = map.iterator();  
			while (it.hasNext()) {  
			Product p = it.next();
			  String str = p.toString();  
			  System.out.println(str);
			  System.out.println("AddCartServlet数量："+cart.get(p));
			}
//			request.getRequestDispatcher("/client/cart.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/client/cart.jsp");
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
