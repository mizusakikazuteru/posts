package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Category;
import dao.CategoryDao;

public class CategoryService {

	public List<Category> getCategories() {
		Connection connection = null;
		try {
			connection = getConnection();
			return CategoryDao.getCategories(connection);
		} finally {
			close(connection);
		}
	}
}
