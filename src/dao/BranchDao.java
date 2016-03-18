package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Branch;
import exception.SQLRuntimeException;

//支店名をDBから取り出す
public class BranchDao {
	public static List<Branch> getBranches(Connection connection) {

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM branches";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			return toBranchList(rs);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Branch> toBranchList(ResultSet rs) throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();

		try {
			while (rs.next()) {
				Branch branch = new Branch();
				branch.setId(rs.getInt("id"));
				branch.setName(rs.getString("name"));
				ret.add(branch);

			}
			return ret;
		} finally {
			close(rs);
		}
	}
}