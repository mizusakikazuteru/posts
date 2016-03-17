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

import beans.Post;
import service.PostService;

@WebServlet(urlPatterns = { "/post" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		req.getRequestDispatcher("post.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = req.getSession();
		if (isValid(req, messages) == true) {

			Post post = new Post();

			post.setSubject(req.getParameter("subject"));
			post.setText(req.getParameter("text"));
			post.setCategory(req.getParameter("category"));
			post.setCreatedAt(req.getParameter("createdAt"));
			post.setUserId(req.getParameter("userId"));


			new PostService().register(post);
			// フォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
			dispatcher.forward(req, res);

		} else {
			session.setAttribute("errorMessages", messages);
			res.sendRedirect("post");
		}
	}

	// isValid メソッド (SQLServerConnection) バリデーション処理
	// SQLServerConnection オブジェクトが閉じられておらず、有効であるかどうか。
	private boolean isValid(HttpServletRequest req, List<String> messages) {
		String Subject = req.getParameter("subject");
		String Text = req.getParameter("text");
		String Category = req.getParameter("category");

		if (StringUtils.isEmpty(Subject) == true) {
			messages.add("件名を入力してください");
		}
		if (StringUtils.isEmpty(Text) == true) {
			messages.add("本文を入力してください");
		}
		if (StringUtils.isEmpty(Category) == true) {
			messages.add("カテゴリーを入力してください");
		}
		//if (StringUtils.isEmpty(str)y)

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
