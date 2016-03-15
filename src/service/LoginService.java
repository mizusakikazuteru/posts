package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;

import beans.User;
import dao.UserDao;
import util.CipherUtil;

//ログイン処理のみ
public class LoginService {

	public User login(String loginId, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			//DAOはe-mailorIDとパスワードからユーザーの情報を取り出す。
			User user = userDao
					.getUser(connection, loginId, encPassword);

			commit(connection);


			return user;//LoginServletへ情報を渡す。
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
