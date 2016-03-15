package contloller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;


@WebServlet(urlPatterns = { "/index.jsp" })

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse res) throws IOException, ServletException {

		User user = (User) req.getSession().getAttribute("loginUser");
		boolean isShowMessageForm;
		if (user != null) {
			isShowMessageForm = true;
		} else {
			isShowMessageForm = false;
		}

		req.setAttribute("isShowMessageForm", isShowMessageForm);

		req.getRequestDispatcher("/home.jsp").forward(req, res);
	}

}
