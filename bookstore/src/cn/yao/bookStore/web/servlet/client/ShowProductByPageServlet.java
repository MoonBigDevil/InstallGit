package cn.yao.bookStore.web.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yao.bookStore.domain.PageBean;
import cn.yao.bookStore.domain.Product;
import cn.yao.bookStore.exception.ListProductException;
import cn.yao.bookStore.service.ProductService;

@WebServlet("/showProductByPage")
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowProductByPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
//		ProductService service = new ProductService();
//		List<Product> list = null;
//		try {
//			
//			list = service.listAll();
//			
//		} catch (ListProductException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		out.println("所有图书：<br>");
//		for(Product p : list) {
//			String url = "/bookstore/AddCartServlet?id="+p.getId();
//			out.print(p.getName()+"<a href='"+url+"'>加入购物车</a><br>");
//		}
		
		//定义当前页码
		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if(_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		//定义每页显示条数
		int currentCount = 4;
		String _currentCount = request.getParameter("currentCount");
		if(_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}
		//获取查找的分类
		String category = "all";
		String _category = request.getParameter("category");
		if(_category != null) {
			category = _category;
		}
		
		//调用service完成获取当前分页的数据
		ProductService service = new ProductService();
		PageBean bean = service.findProductByPage(currentPage, currentCount, category);
		
		System.out.println(bean.getPsList());
		
		//将数据存储到request范围内，跳转到product_list分页
		request.setAttribute("bean",bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
