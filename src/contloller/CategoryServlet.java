package contloller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CategoryService;


@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.getRequestDispatcher("home.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String ctd = req.getParameter("category");

		CategoryService categoryService = new CategoryService();

	}}
//		if (ctd != null) {
//
//			// リダイレクト
//			session.setAttribute("category", ctd);
//
//			res.sendRedirect("home");
//
//		} else {
//			List<String> messages = new ArrayList<String>();
//			messages.add("検索に失敗しました。");
//			session.setAttribute("errorMessages", messages);
//			res.sendRedirect("home");
//		}
//	}

//}