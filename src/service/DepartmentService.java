package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Department;
import dao.DepartmentDao;

public class DepartmentService {
	public List<Department> getDepartments() {
		Connection connection = null;
		try {
			connection = getConnection();
			return DepartmentDao.getDepartments(connection);
		} finally {
			close(connection);
		}
	}
}
