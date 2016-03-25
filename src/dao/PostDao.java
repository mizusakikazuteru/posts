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

public class PostDao {

	// 投稿情報を結果セットからbeansのpost.javaに入れる
	public static List<Post> getAllPosts(Connection con) {
		//List<Post> ret = new ArrayList<Post>();

		PreparedStatement ps = null;
		//ResultSet rs = null;
		try {
			String sql = "SELECT * FROM postings INNER JOIN users ON postings.user_id = users.id ";

			ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			rs = ps.executeQuery();
			return toPostList(rs);

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
		private static List<Post> toPostList(ResultSet rs) throws SQLException {

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

	public void insert(Connection connection, Post post) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO postings ( ");
			sql.append(" subject");
			sql.append(", text");
			sql.append(",category");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append(" ?"); // subject
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", ?"); // user_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, post.getSubject());
			ps.setString(2, post.getText());
			ps.setString(3, post.getCategory());
			ps.setString(4, post.getUserId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
