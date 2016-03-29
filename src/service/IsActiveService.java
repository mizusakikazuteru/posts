package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.UserDao;

public class IsActiveService {

	public List<User> getIsActive() {

		Connection connection = null;

		try {
			connection = getConnection();
		return UserDao.getIsActive(connection);

		} finally {
			close(connection);
		}
	}

}