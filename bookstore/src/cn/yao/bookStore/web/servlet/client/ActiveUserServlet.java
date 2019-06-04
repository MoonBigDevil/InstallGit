package cn.yao.bookStore.web.servlet.client;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yao.bookStore.dao.UserDao;
import cn.yao.bookStore.domain.User;

@WebServlet("/activeUser")
public class ActiveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActiveUserServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取不到，是null
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
		String activeCode = request.getParameter("activeCode");
		System.out.println(activeCode);
		PrintWriter pWriter = response.getWriter();
		UserDao dao = new UserDao();
		User user = null;

		try {
			// 调用dao中的激活方法
			dao.activeUser(activeCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			user = dao.findUserByActiveCode(activeCode);
//			System.out.println(user.getUserName());
//			System.out.println(user.toString());
			if (user.getState() == 1) {
				/*
				 * 开始user.getUserName一直是null 刚开始排除乱码问题，然后还是不行 现在反应过来是，
				 * //将表单数据封装到user对象对应的属性中
				 * BeanUtils.populate(user, request.getParameterMap()); 
				  * 刚开始的时候 register.jsp属性名是name，然后User实体类的属性名也是user，
				  * 只有数据库的 是username。 这样的话，能把数据保存到数据库，可是为什么获取不出来呢？
				   * 那么又有问题了，他是怎么获取的呢？
				   * 在dao的findUserByActiveCode方法里
				 * User user= runner.query(sql,new BeanHandler<User>(User.class), activeCode);
				  * 肯定就是将sql语句执行后，将对应的属性数据封装到Javabean中，
				  * 只有name和username对应不上 解决办法，改名
				 */
				pWriter.println(user.getUsername() + ",您好，您已经成功激活");
				pWriter.println("激活时间为："+user.getRegistTime().toString());
				System.out.println("成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
