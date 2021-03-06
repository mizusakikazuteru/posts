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
import beans.User;
import service.CommentService;


@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String postid = null;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		HttpSession session = req.getSession();

		List<String> comments = new ArrayList<String>();

		if (isValid(req, comments) == true) {

			User user = (User) session.getAttribute("loginUser");


			Comment comment = new Comment();


			comment.setText(req.getParameter("text"));
			comment.setUserId(user.getId());

			String PostId = req.getParameter("postid");

			int intPostId = Integer.parseInt(PostId);
			comment.setPostId(intPostId);

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

		String text = req.getParameter("text");
		postid = req.getParameter("postid");

		if (StringUtils.isEmpty(text) == true) {
			comments.add("コメントを入力してください");
		}
		if (500 <  comments.size()) {
			comments.add("500文字以下で入力してください");
		}
		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
