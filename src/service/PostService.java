package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Post;
import dao.PostDao;
import dao.PostmesDao;

public class PostService {

	public void register(Post posts) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostDao postDao = new PostDao();
			PostDao.insert(connection, posts);

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

	private static final int LIMIT_NUM = 1000;

	public List<Post> getPost() {

		Connection connection = null;
		try {
			connection = getConnection();

			PostmesDao postmesDao = new PostmesDao();
			List<Post> ret = postmesDao.getPost(connection, LIMIT_NUM);

			commit(connection);

			return ret;
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
