package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Post;
import dao.CategoryDao;

public class CategoryService {

	// カテゴリー検索
	private static final int LIMIT_NUM = 1000;

		public List<Post> getCategory(String category ) {

			Connection connection = null;
			try {
				connection = getConnection();

				CategoryDao categoryDao = new CategoryDao();
				List<Post> categories = categoryDao.getAllCategories(connection, category, LIMIT_NUM);

				commit(connection);

				return categories;
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
