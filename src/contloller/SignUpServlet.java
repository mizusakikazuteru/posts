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

import beans.Branch;
import beans.Department;
import beans.User;
import service.BranchService;
import service.DepartmentService;
import service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// 支店情報取得→jspへ表示
		BranchService branchService = new BranchService();
		List<Branch> branches = branchService.getBranches();

		req.setAttribute("branches", branches);

		// 部署・役職情報取得→jspへ表示
		DepartmentService departmentService = new DepartmentService();
		List<Department> departments = departmentService.getDepartments();

		req.setAttribute("departments", departments);
		req.getRequestDispatcher("signup.jsp").forward(req, res);

	}

	// doGetでsignup.jspへforwardする。
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = req.getSession();
		if (isValid(req, messages) == true) {

			User user = new User();
			user.setLoginId(req.getParameter("loginId"));
			user.setPassword(req.getParameter("password"));
			user.setName(req.getParameter("name"));
			user.setBranchId(req.getParameter("branchId"));
			user.setDepartmentId(req.getParameter("departmentId"));

			new UserService().register(user);
			// フォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("management.jsp");
			dispatcher.forward(req, res);

		} else {
			session.setAttribute("errorMessages", messages);
			res.sendRedirect("signup");
		}
	}

	// isValid メソッド (SQLServerConnection) バリデーション処理
	// SQLServerConnection オブジェクトが閉じられておらず、有効であるかどうか。
	private boolean isValid(HttpServletRequest req, List<String> messages) {
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");

		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("氏名を入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
