package contloller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import service.UserService;

@WebServlet("/management")
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute("loginUser");
		// // 支店コード１/支店名本社ならtrue
		HttpSession session = req.getSession();
		//
		// String branchId = user.getBranchId();
		//
		// // ManagementService manegementService = new ManagementService();
		//
		// // Branch id = manegementService.branch(branchId);
		//
		// if ("1".equals(branchId)) {
		//
		// session.setAttribute("branch", branchId);
		// res.sendRedirect("management.jsp");
		// } else {
		//
		// List<String> messages = new ArrayList<String>();
		// messages.add("管理者権限がありません。");
		// session.setAttribute("errorMessages", messages);
		// res.sendRedirect("home");
		// }

		UserService users = new UserService();

		List<User> userList = users.getUser();

		User loginUser = (User) session.getAttribute("loginUser");

		req.setAttribute("userList", userList);

		if (session.getAttribute("isativeUser") == null) {
			User isactiveUser = new UserService().getUser(loginUser.getId());
			// 初回のみ情報をbeanからUser情報を取ってくる
			session.setAttribute("isactiveUser", isactiveUser);
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("management.jsp");
		dispatcher.forward(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		User isActiveUser = getisActiveUser(req, res);

		session.setAttribute("IsActiveUser", isActiveUser);
		try {
			new UserService().updates(isActiveUser);
			res.sendRedirect("management");
		} catch (NoRowsUpdatedRuntimeException e) {
			session.removeAttribute("isActiveUser");

			res.sendRedirect("management");
		}

	}
	// ユーザー編集の為の情報
	private User getIsActiveUser(HttpServletRequest req) throws IOException, ServletException {

		HttpSession session = req.getSession();
		User IsActiveUser = (User) session.getAttribute("editUser");

		boolean isActive = Boolean.getBoolean(req.getParameter("isActive"));
		IsActiveUser.setIsActive(isActive);
		return IsActiveUser;
	}

	// req.sendRedirect("management");
	// RequestDispatcher dispatcher = req.getRequestDispatcher("management");
	// dispatcher.forward(req, res);

}
