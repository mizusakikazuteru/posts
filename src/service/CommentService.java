package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Comment;
import dao.CommentDao;

public class CommentService {

	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

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

	public List<Comment> getComments() {
		Connection connection = null;
		try {
			connection = getConnection();

			return CommentDao.getAllComments(connection);
		} catch (Error e) {
		} finally {
			close(connection);

		}
		return null;
	}

}
