package service;
import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;

import beans.User;
import dao.UserDao;
import util.CipherUtil;

//ユーザー登録
public class UserService {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();
			//登録する内容のパスワード暗号化(UtilクラスのCipherUtil,java)
			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);



			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

			commit(connection);
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


	//内容編集
	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

			commit(connection);
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
	//ユーザー情報を取得
	public User getUser(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, userId);

			commit(connection);

			return user;
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
