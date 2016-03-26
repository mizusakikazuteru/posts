package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Post;
import dao.PostDao;

public class PostService {

	public void register(Post posts) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostDao postDao = new PostDao();
			postDao.insert(connection, posts);

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

	public List<Post> getPosts() {
		Connection connection = null;
		try {
			connection = getConnection();

			return PostDao.getAllPosts(connection);
		} catch (Error e) {
		} finally {
			close(connection);

		}
		return null;
	}

}