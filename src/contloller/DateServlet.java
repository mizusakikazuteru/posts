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

import beans.Post;
import service.DateService;


@WebServlet("/date")
public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;





	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String createdAsc = req.getParameter("createdAtasc");
		String createdAtDesc = req.getParameter("createdAtdesc");

		DateService dateService = new DateService();
		List<Post> date = dateService.getDates(createdAsc, createdAtDesc );

		HttpSession session = req.getSession();

		if ((date.size() != 0)) {
			session.setAttribute("dateList", date);
			res.sendRedirect("home");

		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("該当の検索条件はありません。");
			session.setAttribute("errorMessages", messages);

			res.sendRedirect("home");
		}
	}
}