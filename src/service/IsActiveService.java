package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;

import beans.User;
import dao.UserDao;

public class IsActiveService {



	public void register(User isactive) {

		Connection connection = null;

		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			UserDao.updates(connection, isactive);

		} finally {
			close(connection);
		}
	}

}