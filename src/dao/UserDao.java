package dao;

//ユーザー情報関連のSQLを発行
import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class UserDao {

	public UserDao() {

	}

	public static List<User> getAllUser(Connection con) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM users  ";

			ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			rs = ps.executeQuery();
			return toUsersList(rs);

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<User> toUsersList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();

		try {

			while (rs.next()) {

				User user = new User();

				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branchId = rs.getString("branch_id");
				String departmentId = rs.getString("department_id");
				String isActive = rs.getString("is_active");

				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branchId);
				user.setDepartmentId(departmentId);
				user.setIsActive(isActive);

				ret.add(user);

			}

			return ret;
		} finally {
			close(rs);
		}
	}

	// ログイン情報を取得
	public User getUser(Connection connection, String loginId, String password) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			List<User> userList = toUserList(rs);
			// beansクラスのUser.javaから＜USER>に格納
			if (userList.isEmpty() == true) {
				// ユーザー情報が空の時NULL
				return null;
			} else if (3 <= userList.size()) {
				// ユーザー情報が2個以上あるときエラー文
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
				// ﾘﾀｰﾝでSERVICEクラスへ戻る
				// get(0)で情報を取り出す。
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}

	// ユーザ情報を結果セットからbeansのUser.javaに入れる（63行目～78行目まで）
	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branchId = rs.getString("branch_id");
				String departmentId = rs.getString("department_id");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branchId);
				user.setDepartmentId(departmentId);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	// ユーザー登録情報をDBへ格納
	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append(" login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(") VALUES (");
			sql.append(" ?"); // login_id
			sql.append(", ?"); // password
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // department_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getBranchId());
			ps.setString(5, user.getDepartmentId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	// ユーザー情報をDBへ更新
	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("INSERT INTO users ( ");
			sql.append(" login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");

			sql.append(" WHERE");
			sql.append(" id = ?");
			sql.append(" AND");
			sql.append(" update_date = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getBranchId());
			ps.setString(5, user.getDepartmentId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	// DBからIDを検索
	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//ユーザー復活・停止
	public static  void updates(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			String sql = "UPDATE users SET  " +
			"is_active = ? "+" WHERE"+" id = ?";


			ps = connection.prepareStatement(sql);

			ps.setInt(1, user.getId());
			ps.setString(2, user.getIsActive());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}


	}
	public static User getisActive(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM users";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			return toIsActiveList(rs);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static User toIsActiveList(ResultSet rs) throws SQLException {

		User ret = new User();

		try {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setIsActive(rs.getString("is_active"));


			}
			return ret;
		} finally {
			close(rs);
		}
	}
}



