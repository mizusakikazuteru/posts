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

import beans.Post;
import beans.User;
import service.PostService;
import service.UserService;

@WebServlet(urlPatterns = { "/home" })

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		PostService post = new PostService();

		List<Post> postList = post.getPosts();

		req.setAttribute("postList", postList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
		dispatcher.forward(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<String> messages = new ArrayList<String>();

		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("loginUser");

		Post post = new Post();
		post.setSubject(req.getParameter("subject"));
		post.setText(req.getParameter("text"));
		post.setCategory(req.getParameter("category"));
		post.setUserId(req.getParameter("userid"));

		new UserService().register(user);
		// フォワード
		RequestDispatcher dispatcher = req.getRequestDispatcher("home");
		dispatcher.forward(req, res);

	}

}
