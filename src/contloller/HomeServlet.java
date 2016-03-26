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

import beans.Comment;
import beans.Post;
import beans.User;
import service.CommentService;
import service.PostService;
import service.UserService;

@WebServlet(urlPatterns = { "/home" })

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		User user = (User) req.getSession().getAttribute("loginUser");

		PostService post = new PostService();

		List<Post> postList = post.getPosts();

		req.setAttribute("postList", postList);

		CommentService comment = new CommentService();

		List<Comment> commnetList = comment.getComments();

		req.setAttribute("commnetList", commnetList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
		dispatcher.forward(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//List<String> messages = new ArrayList<String>();

		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("loginUser");

		Post post = new Post();
		post.setSubject(req.getParameter("subject"));
		post.setText(req.getParameter("text"));
		post.setCategory(req.getParameter("category"));
		post.setName(req.getParameter("name"));

		new UserService().register(user);
		// フォワード
		RequestDispatcher dispatcher = req.getRequestDispatcher("home");
		dispatcher.forward(req, res);

	}

}
