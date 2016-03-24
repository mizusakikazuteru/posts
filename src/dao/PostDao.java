package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Post;
import exception.SQLRuntimeException;

public class PostDao {

	// 投稿情報を結果セットからbeansのpost.javaに入れる
	public List<Post> toPostList(Connection con) throws SQLException {

		List<Post> ret = new ArrayList<Post>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM postings");

			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			System.out.println(ps);
			System.out.println(rs);

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

		try {
			System.out.println("うううう");
			int i = 1;
			System.out.println(rs.next());
			while (rs.next()) {
				System.out.println(i);
				i++;
				System.out.println("ええええ");

				int id = rs.getInt("id");
				String Subject = rs.getString("subject");

				System.out.println(Subject);

				String Text = rs.getString("text");
				String Category = rs.getString("category");
				Date createdAt = rs.getDate("created_at");
				int UserId = rs.getInt("userId");

				Post post = new Post();
				post.setId(id);
				post.setSubject(Subject);
				post.setText(Text);
				post.setCategory(Category);
				post.setCreatedAt(createdAt);
				post.setUserId(UserId);

				ret.add(post);

			}
			System.out.println(ret);
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
			ps.setInt(4, post.getUserId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
