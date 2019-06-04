package cn.yao.bookStore.service;

import java.sql.SQLException;

import javax.security.auth.login.LoginException;

import cn.yao.bookStore.dao.UserDao;
import cn.yao.bookStore.domain.User;
import cn.yao.bookStore.exception.RegisterException;
import cn.yao.bookStore.utils.MailUtils;

public class UserService {
	private UserDao dao = new UserDao();

	// 注册
	public void register(User user) throws RegisterException {
		try {
			// 调用dao层方法完成注册操作
			dao.addUser(user);
			// 发送激活邮件
			String emailMsg = "感谢您注册网上商城，点击" + "<a href='http://localhost:8080/bookstore/activeUser?activeCode="
					+ user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用。" + "<br>为了保障您的账户安全，请在24小时内完成激活操作";
			MailUtils.sendMail(user.getEmail(), emailMsg);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注册失败");
		}
	}

	// 登录
	public User login(String username, String password) throws LoginException {

		try {
			User user = dao.findUserByUsernameAndPassword(username, password);
			
			if(user != null) {
				//只有激活过得用户才可以登录成功，多以要判断用户的激活状态
				if(user.getState() == 1) {
					return user;
				}
				else throw new LoginException("用户未激活,请注意查收您邮箱的激活邮件");
			}
			throw new LoginException("用户名或密码错误");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}

	}
}
