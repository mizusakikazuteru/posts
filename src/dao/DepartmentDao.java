package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Department;
import exception.SQLRuntimeException;

public class DepartmentDao {

	public static List<Department> getDepartments(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM departments";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			return toDepartmentList(rs);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Department> toDepartmentList(ResultSet rs) throws SQLException {

		List<Department> ret = new ArrayList<Department>();

		try {
			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("name"));
				ret.add(department);

			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
