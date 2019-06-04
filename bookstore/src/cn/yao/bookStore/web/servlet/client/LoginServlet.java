package cn.yao.bookStore.web.servlet.client;

import java.io.IOException;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yao.bookStore.domain.User;
import cn.yao.bookStore.service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
		
		//1\获取登录界面输入的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String ck1 = request.getParameter("checkbox1");//记住用户名
		String ck2 = request.getParameter("checkbox2");//自动登录
		System.out.println(ck1);
		
		//2\调用service，完成登录操作
		UserService service = new UserService();
		try {
			User user = service.login(username, password);
			//3\登录成功,将用户存储到Session中
			request.getSession().setAttribute("user", user);
			//登录成功后，判断记住用户名和自动登录
			if("checkbox".equals(ck1)) {
				Cookie cookie = new Cookie("remembername", username+"#"+password);
				cookie.setMaxAge(6000);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
				System.out.println("记住用户名cookie："+cookie.getValue());
			}
			if("autologin".equals(ck2)) {
				Cookie cookie = new Cookie("autologin", username+"#"+password);
				cookie.setMaxAge(600);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
				System.out.println("自动登录cookie："+cookie.getValue());
			}
			
			
			
			//获取用户的角色，普通用户或超级用户
			String role = user.getRole();
			
			//如果是超级用户，就进入到网上书城的后台管理系统，否则进入我的账户页面
			if("超级用户".equals(role)) {
				response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/client/myAccount.jsp?name="+username);
				return;
			}
			
		} catch (LoginException e) {
			//如果出现问题，将错误信息存储到request域对象里，并跳转到登录页面显示错误信息
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			
		}
	}

	private Exception LoginException(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
