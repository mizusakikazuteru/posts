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


@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		@Override
		protected void doPost(HttpServletRequest req,
				HttpServletResponse res) throws IOException, ServletException {

			HttpSession session = req.getSession();

			List<String> messages = new ArrayList<String>();

			if (isValid(req, messages) == true) {

				User user = (User) session.getAttribute("loginUser");

				Comment comment = new Comment();
				comment.setText(req.getParameter("message"));
				comment.setUserId(user.getId());

				new CommentService().register(comment);

				res.sendRedirect("");
			} else {
				session.setAttribute("errorMessages", messages);
				res.sendRedirect("");
			}
		}

		private boolean isValid(HttpServletRequest request, List<String> messages) {

			String message = request.getParameter("message");

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


