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

import beans.Comment;
import beans.Post;
import beans.User;
import service.CategoryService;
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


		//if (isValid(req, category) == true) {

//		Post cotegories = new Post();
//		cotegories.setCategory(req.getParameter("category"));
//
//			// Post categories = CategoryService.getCategory( categories );
//
//			req.setAttribute("category", category);
//		}
		req.setAttribute("posts", posts);
		req.setAttribute("comments", comments);

		req.getRequestDispatcher("home.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		String category = req.getParameter("category");

		CategoryService categoryService = new CategoryService();
		List<Post> categories = categoryService.getCategory(category);

		HttpSession session = req.getSession();

		if (categories != null) {
			session.setAttribute("categoryList", categories);
			res.sendRedirect("home");

		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("検索に失敗しました。");
			session.setAttribute("errorMessages", messages);

			res.sendRedirect("home");
		}
	}
}