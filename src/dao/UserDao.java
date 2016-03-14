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
		System.out.println("hello");
	}

	public User getUser(Connection connection, String login_id,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM user WHERE login_id AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);

			System.out.println(ps.toString());

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			//beansクラスのUser.javaから＜USER>に格納
			if (userList.isEmpty() == true) {
				//ユーザー情報が空の時NULL
				return null;
			} else if (2 <= userList.size()) {
				//ユーザー情報が2個以上あるときエラー文
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
				//ﾘﾀｰﾝでSERVICEクラスへ戻る
				//get(0)で情報を取り出す。
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}
	//ユーザ情報を結果セットからbeansのUser.javaに入れる（65行目～91行目まで）
	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branch_id = rs.getString("branch_id");
				String department_id = rs.getString("department_id");

				User user = new User();
				user.setId(id);
				user.setlogin_id(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setbranch_id(branch_id);
				user.setdepartment_id(department_id);


				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


	//ユーザー登録情報をDBへ格納
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

			ps.setString(1, user.getlogin_id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getbranch_id());
			ps.setString(5, user.getdepartment_id());


			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	//ユーザー情報をDBへ更新
	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE user SET");
			sql.append("INSERT INTO user ( ");
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

			ps.setString(1, user.getlogin_id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getbranch_id());
			ps.setString(5, user.getdepartment_id());


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

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM user WHERE id = ?";

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

}
