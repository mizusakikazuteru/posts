package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;

import beans.Post;
import dao.PostDao;

public class PostService {


	public void register(Post post) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostDao PostDao = new PostDao();
			dao.PostDao.insert(connection, post);

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
	}}
