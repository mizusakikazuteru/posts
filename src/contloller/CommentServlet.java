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

import org.apache.commons.lang.StringUtils;

import beans.Comment;
import beans.Post;
import beans.User;
import service.CommentService;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		HttpSession session = req.getSession();

		List<String> comments = new ArrayList<String>();

		if (isValid(req, comments) == true) {

			 User user = (User) session.getAttribute("loginUser");
			 //Post post = (Post) session.getAttribute("postId");
			Comment comment = new Comment();
			Post post = new Post();

			comment.setText(req.getParameter("text"));
			comment.setUserId(user.getId());
			comment.setPostId(post.getId());

			new CommentService().register(comment);

			res.sendRedirect("home");
		} else {
			session.setAttribute("errorMessages", comments);
			res.sendRedirect("home");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("post.jsp");
//			dispatcher.forward(req, res);

		}

	}

	private boolean isValid(HttpServletRequest req, List<String> comments) {

		String comment = req.getParameter("comment");

		if (StringUtils.isEmpty(comment) == true) {
			comments.add("コメントを入力してください");
//		}
//		if (140 <  comments.length()) {
//			comments.add("500文字以下で入力してください");
//		}
//		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
