package contloller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.UserService;

@WebServlet("/management")
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		 User user = (User) req.getSession().getAttribute("loginUser");
		 // 支店コード１/支店名本社ならtrue
		 HttpSession session = req.getSession();

		 int branchId = user.getBranchId();

		  //ManagementService manegementService = new ManagementService();

		  //Branch id = manegementService.branch(branchId);

		 if (1 == (branchId)) {

		 session.setAttribute("branch", branchId);
		 res.sendRedirect("management");
		 } else {

		 List<String> messages = new ArrayList<String>();
		 messages.add("管理者権限がありません。");
		 session.setAttribute("errorMessages", messages);
		 res.sendRedirect("home");
		 }

		UserService users = new UserService();

		List<User> userList = users.getUser();

		req.setAttribute("userList", userList);

//		RequestDispatcher dispatcher = req.getRequestDispatcher("management.jsp");
//		dispatcher.forward(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// formのname属性指定（getParameter)で取り出す
		String userId = req.getParameter("userId");
		String isActive = req.getParameter("isActive");

		User user = new User();

		int intUserId = Integer.parseInt(userId);
		user.setId(intUserId);
		int intIsActive = Integer.parseInt(isActive);
		user.setIsActive(intIsActive);


		new UserService().updates(user);

		res.sendRedirect("management");
		// RequestDispatcher dispatcher =
		// req.getRequestDispatcher("management");
		// dispatcher.forward(req, res);

	}

}
