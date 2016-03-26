package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {

	public static List<Comment> getAllComments(Connection con) {


		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM comments  ";

			ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			rs = ps.executeQuery();
			return toCommentList(rs);

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
		private static List<Comment> toCommentList(ResultSet rs) throws SQLException {

			List<Comment> ret = new ArrayList<Comment>();

			try {


			while (rs.next()) {

				Comment comment = new Comment();
				int id = rs.getInt("id");
				String text = rs.getString("text");
				Timestamp createdAt = rs.getTimestamp("created_at");
				String userId = rs.getString("user_id");
				String postId = rs.getString("posting_id");

				comment.setId(id);
				comment.setText(text);
				comment.setCreatedAt(createdAt);
				comment.setUserId(userId);
				comment.setPostId(postId);

				ret.add(comment);

			}

			return ret;
		} finally {
			close(rs);
		}
	}


	public void insert(Connection connection, Comment comment) {



				PreparedStatement ps = null;
				try {
					StringBuilder sql = new StringBuilder();
					sql.append("INSERT INTO comments ( ");
					sql.append(" text");
					sql.append(", created_at");
					sql.append(",user_id");
					sql.append(", posting_id");
					sql.append(") VALUES (");
					sql.append(" ?"); // text
					sql.append(", ?"); // created_at
					sql.append(", ?"); // user_id
					sql.append(", ?"); // posting_id
					sql.append(")");

					ps = connection.prepareStatement(sql.toString());

					ps.setString(1, comment.getUserId());
					ps.setString(2, comment.getText());

					ps.executeUpdate();
				} catch (SQLException e) {
					throw new SQLRuntimeException(e);
				} finally {
					close(ps);
				}
			}

		}
