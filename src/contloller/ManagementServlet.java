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
import service.IsActiveService;
import service.UserService;

@WebServlet("/management")
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute("loginUser");
//		// 支店コード１/支店名本社ならtrue
//		HttpSession session = req.getSession();
//
//		String branchId = user.getBranchId();
//
//		// ManagementService manegementService = new ManagementService();
//
//		// Branch id = manegementService.branch(branchId);
//
//		if ("1".equals(branchId)) {
//
//			session.setAttribute("branch", branchId);
//			res.sendRedirect("management.jsp");
//		} else {
//
//			List<String> messages = new ArrayList<String>();
//			messages.add("管理者権限がありません。");
//			session.setAttribute("errorMessages", messages);
//			res.sendRedirect("home");
//		}

		UserService users = new UserService();
		IsActiveService isactive = new IsActiveService();

		List<User> userList = users.getUser();
		List<User> isActive = isactive.getIsActive();

		req.setAttribute("userList", userList);
		req.setAttribute("isActive", isActive);

		RequestDispatcher dispatcher = req.getRequestDispatcher("management.jsp");
		dispatcher.forward(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("loginUser");

		User users = new User();

		int Id =Integer.parseInt(req.getParameter("Id"));

		users.setId(Id);
		users.setLoginId(req.getParameter("loginId"));
		users.setName(req.getParameter("name"));

		boolean isActive = Boolean.getBoolean(req.getParameter("isActive"));
		users.setIsActive(isActive);

		res.sendRedirect("management");
		RequestDispatcher dispatcher = req.getRequestDispatcher("management");
		dispatcher.forward(req, res);

	}

}
