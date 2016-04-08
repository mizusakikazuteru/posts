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
import service.DateService;
import service.PostService;

@WebServlet(urlPatterns = { "/home" })

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// 日付情報取得→jspへ表示
		DateService dateAscService = new DateService();
		List<Post> dateAsc = dateAscService.getDatesAsc();

		req.setAttribute("dateAsc", dateAsc);

		DateService dateDescService = new DateService();
		List<Post> dateDesc = dateDescService.getDatesDesc();

		req.setAttribute("dateDesc", dateDesc);



		User user = (User) req.getSession().getAttribute("loginUser");

		List<Post> posts = new PostService().getPost();
		List<Comment> comments = new CommentService().getComments();

		req.setAttribute("posts", posts);
		req.setAttribute("comments", comments);

		req.getRequestDispatcher("home.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String category = req.getParameter("category");

		CategoryService categoryService = new CategoryService();
		List<Post> categories = categoryService.getCategory(category);

		HttpSession session = req.getSession();

		if ((categories.size() != 0)) {
			session.setAttribute("categoryList", categories);
			res.sendRedirect("home");

		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("該当の検索条件はありません。");
			session.setAttribute("errorMessages", messages);

			res.sendRedirect("home");
		}
	}
}