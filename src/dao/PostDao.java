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
import beans.User;
import chapter6.beans.UserMessage;
import exception.SQLRuntimeException;

public class PostDao {

		public List<Post> getUserMessages(Connection connection, int num) {

		// ユーザ情報を結果セットからbeansのpost.javaに入れる
		private final List<Post> toPostList(ResultSet rs) throws SQLException {

			List<Post> ret = new ArrayList<Post>();
			try {
				while (rs.next()) {
					int id = rs.getInt("id");
					String Subject = rs.getString("subject");
					String Text = rs.getString("text");
					String Category = rs.getString("category");
					Timestamp CreatedAt = rs.getTimestamp("createdAt");
					String UserId = rs.getString("userId");

					Post post = new Post();
					post.setId(id);
					post.setSubject(Subject);
					post.setText(Text);
					post.setCategory(Category);
					post.setCreatedAt(CreatedAt);
					post.setUserId(UserId);

					ret.add(post);


				}
				return ret;
			} finally {
				close(rs);
			}
		}

	}
	public static void insert(Connection connection, Post post) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO postings ( ");
			sql.append(" subject");
			sql.append(", text");
			sql.append(",category");
			sql.append(", created_at");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append(" ?"); // subject
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(", ?"); // user_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, post.getSubject());
			ps.setString(2, post.getText());
			ps.setString(3, post.getCategory());
			//ps.setTimestamp(4, post.getCreatedAt());
			ps.setString(5, post.getUserId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
