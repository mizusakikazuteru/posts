package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Post;
import exception.SQLRuntimeException;

public class CategoryDao {

	// カテゴリー検索
	public List<Post> getAllCategories(Connection connection, String category, int num) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append(" SELECT * FROM postings where category LIKE '%%' ");
			sql.append(" ORDER BY created_at DESC limit " + num);


			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, "%"+category+"%");

			ResultSet rs = ps.executeQuery();
			List<Post> ret = toCategoryList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	// カテゴリー検索
	private List<Post> toCategoryList(ResultSet rs) throws SQLException {

		List<Post> ret = new ArrayList<Post>();
		try {
			while (rs.next()) {

				Post categories = new Post();
				int id = rs.getInt("id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp createdAt = rs.getTimestamp("created_at");
				String name = rs.getString("name");

				categories.setId(id);
				categories.setSubject(subject);
				categories.setText(text);
				categories.setCategory(category);
				categories.setCreatedAt(createdAt);
				categories.setName(name);

				ret.add(categories);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
