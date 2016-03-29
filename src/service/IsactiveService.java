package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.ActiveDao;

public class IsactiveService {

		public List<User> getActive() {

			Connection connection = null;

			try {
				connection = getConnection();
				return ActiveDao.getActive(connection);
			} finally {
				close(connection);
			}
		}
	}

