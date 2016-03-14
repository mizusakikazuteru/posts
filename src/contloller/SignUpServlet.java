package contloller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse res) throws IOException, ServletException {
		//doGetでsignup.jspへforwardする。
		req.getRequestDispatcher("signup.jsp").forward(req, res);
	}
	//doGetでsignup.jspへforwardする。
	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse res) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = req.getSession();
		if (isValid(req, messages) == true) {

			User user = new User();
			user.setlogin_id(req.getParameter("login_id"));
			user.setPassword(req.getParameter("password"));
			user.setName(req.getParameter("name"));
			user.setbranch_id(req.getParameter("branch_id"));
			user.setdepartment_id(req.getParameter("department_id"));

			new UserService().register(user);
			//フォワード
			 RequestDispatcher dispatcher =
		              req.getRequestDispatcher("home.jsp");
		          dispatcher.forward(req, res);
			res.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			res.sendRedirect("signup");
		}
	}
	//isValid メソッド (SQLServerConnection)
    //SQLServerConnection オブジェクトが閉じられておらず、有効であるかどうか。
	private boolean isValid(HttpServletRequest req, List<String> messages) {
		String login_id = req.getParameter("login_id");
		String password = req.getParameter("password");
		String   name   = req.getParameter("name");


		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.isEmpty(name) == true){
			messages.add("氏名を入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
