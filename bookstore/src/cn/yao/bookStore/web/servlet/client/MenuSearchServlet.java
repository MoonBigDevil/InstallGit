package cn.yao.bookStore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yao.bookStore.domain.PageBean;
import cn.yao.bookStore.service.ProductService;

@WebServlet("/menuSearch")
public class MenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MenuSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if(_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		
		int currentCount = 4;
		
		//获取前台的搜索框的值
		String searchfield = request.getParameter("textfield");
		System.out.println("菜单栏搜索参数："+searchfield);
		if("请输入书名进行搜索".equals(searchfield)) {
			request.getRequestDispatcher("showProductByPage").forward(request, response);
			return;
		}
		//调用service的书名模糊查询
		ProductService service = new ProductService();
		PageBean bean = service.findBookByName(currentPage, currentCount, searchfield);
		System.out.println("mss"+bean);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_search_list.jsp").forward(request, response);
		
	}

}
