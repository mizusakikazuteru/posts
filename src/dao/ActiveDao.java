package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.SQLRuntimeException;
	//復活・停止の更新
public class ActiveDao {

		public static List<User> getActive(Connection connection) {

			PreparedStatement ps = null;

			try {
				String sql = "update users set is_active=if(is_active=1,0,1);";

				ps = connection.prepareStatement(sql);

				ResultSet rs = ps.executeUpdate();

				return toIsActive(rs);
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		private static List<User> toIsActive(ResultSet rs) throws SQLException {

			List<User> ret = new ArrayList<User>();

			try {
				while (rs.next()) {
					User isactive = new User();
					isactive.setIsActive(rs.getBoolean("is_active"));
					ret.add(isactive);

				}
				return ret;
			} finally {
				close(rs);
			}
		}
}
