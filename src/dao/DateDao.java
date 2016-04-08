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

//日付を昇順でDBから取り出す
public class DateDao {
	public static List<Post> getDatesAsc(Connection connection) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM postings ORDER BY created_at ASC");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Post> ret = toDateAscList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Post> toDateAscList(ResultSet rs) throws SQLException {

		List<Post> ret = new ArrayList<Post>();

		try {
			while (rs.next()) {

				Post date = new Post();

				int id = rs.getInt("id");
				Timestamp createdAt = rs.getTimestamp("created_at");

				date.setId(id);
				date.setCreatedAt(createdAt);
				ret.add(date);

			}
			return ret;
		} finally {
			close(rs);
		}
	}

	// 日付を降順でDBから取り出す
	public static List<Post> getDatesDesc(Connection connection) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM postings ORDER BY created_at DESC");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Post> ret = toDateDescList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Post> toDateDescList(ResultSet rs) throws SQLException {

		List<Post> ret = new ArrayList<Post>();

		try {
			while (rs.next()) {

				Post date = new Post();

				int id = rs.getInt("id");
				Timestamp createdAt = rs.getTimestamp("created_at");

				date.setId(id);
				date.setCreatedAt(createdAt);
				ret.add(date);

			}
			return ret;
		} finally {
			close(rs);
		}
	}

	// 日付をDBから期間検索

	public static List<Post> getDates(Connection connection, String createdAsc, String createdAtDesc, int num) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT * FROM postings INNER JOIN users ON postings.user_id = users.id WHERE created_at BETWEEN  ?  AND   ? ");
			sql.append(" ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, createdAsc);
			ps.setString(2, createdAtDesc );



			ResultSet rs = ps.executeQuery();
			List<Post> dateList = toDateList(rs);
			return dateList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Post> toDateList(ResultSet rs) throws SQLException {

		List<Post> ret = new ArrayList<Post>();

		try {
			while (rs.next()) {

				Post date = new Post();
				int id = rs.getInt("id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp createdAt = rs.getTimestamp("created_at");
				String name = rs.getString("name");

				date.setId(id);
				date.setSubject(subject);
				date.setText(text);
				date.setCategory(category);
				date.setCreatedAt(createdAt);
				date.setName(name);
				ret.add(date);

			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
