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

public class PostmesDao {

	public List<Post> getPost(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM postings INNER JOIN users ON postings.user_id = users.id  ");
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Post> ret = toPostList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Post> toPostList(ResultSet rs)
			throws SQLException {

		List<Post> ret = new ArrayList<Post>();
		try {
			while (rs.next()) {

				Post post = new Post();
				int id = rs.getInt("id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp createdAt = rs.getTimestamp("created_at");
				String name = rs.getString("name");

				post.setId(id);
				post.setSubject(subject);
				post.setText(text);
				post.setCategory(category);
				post.setCreatedAt(createdAt);
				post.setName(name);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}


