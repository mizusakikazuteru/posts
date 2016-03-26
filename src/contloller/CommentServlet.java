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

import org.apache.commons.lang.StringUtils;

import beans.Comment;
import service.CommentService;


@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		@Override
		protected void doPost(HttpServletRequest req,
				HttpServletResponse res) throws IOException, ServletException {

			List<String> comments = new ArrayList<String>();

			HttpSession session = req.getSession();

			if (isValid(req, comments) == true) {

				//User user = (User) session.getAttribute("loginUser");

				Comment comment = new Comment();

				comment.setText(req.getParameter("text"));
				comment.setUserId(req.getParameter("userId"));
				comment.setPostId(req.getParameter("posId"));

				new CommentService().register(comment);

	            res.sendRedirect("/home");
	        } else {
	        	session.setAttribute("errorMessages", comments);
	            RequestDispatcher dispatcher = req.getRequestDispatcher("post.jsp");
	            dispatcher.forward(req, res);

			}

		}

		private boolean isValid(HttpServletRequest req, List<String> messages) {

			String message = req.getParameter("message");

			if (StringUtils.isEmpty(message) == true) {
				messages.add("コメントを入力してください");
			}
			if (140 < message.length()) {
				messages.add("500文字以下で入力してください");
			}
			if (messages.size() == 0) {
				return true;
			} else {
				return false;
			}
		}}


