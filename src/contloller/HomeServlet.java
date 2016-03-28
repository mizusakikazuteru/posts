package contloller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Comment;
import beans.Post;
import beans.User;
import service.CommentService;
import service.PostService;

@WebServlet(urlPatterns = { "/home" })

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		User user = (User) req.getSession().getAttribute("loginUser");


		List<Post> posts = new PostService().getPost();
		List<Comment> comments = new CommentService().getComments();

		//posts.setCategory(req.getParameter("category"));


		req.setAttribute("posts", posts);
		req.setAttribute("comments", comments);
		//req.setAttribute("categories", categories);

		req.getRequestDispatcher("home.jsp").forward(req, res);
	}

}