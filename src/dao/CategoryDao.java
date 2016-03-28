package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Category;
import exception.SQLRuntimeException;

public class CategoryDao {

	public List<Category> getCategories(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM users WHERE( concat(postings) LIKE ‘%id%’) ORDER BY id DESC";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			return toCategoryList(rs);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Category> toCategoryList(ResultSet rs) throws SQLException {

		List<Category> ret = new ArrayList<Category>();

		try {
			while (rs.next()) {
				String category = rs.getString("category");

				Category ctd = new Category();
				ctd.setCategory(category);

				ret.add(ctd);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
