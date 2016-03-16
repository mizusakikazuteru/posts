package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Branch;
import dao.BranchDao;

public class BranchService {
	public List<Branch> getBranches() {
		Connection connection = null;
		try {
			connection = getConnection();
			return BranchDao.getBranches(connection);
		} finally {
			close(connection);
		}
	}
}
