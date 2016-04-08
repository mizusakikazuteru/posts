package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Post;
import dao.DateDao;

public class DateService {
	//昇順
	public List<Post> getDatesAsc() {

		Connection connection = null;

		try {
			connection = getConnection();
			return DateDao.getDatesAsc(connection);
		} finally {
			close(connection);
		}
	}
	//降順
	public List<Post> getDatesDesc() {

		Connection connection = null;

		try {
			connection = getConnection();
			return DateDao.getDatesDesc(connection);
		} finally {
			close(connection);
		}
	}
	//投稿日期間検索

	private static final int LIMIT_NUM = 1000;

	public List<Post> getDates(String createdAsc, String createdAtDesc  ) {

		Connection connection = null;

		try {
			connection = getConnection();
			return DateDao.getDates(connection, createdAsc, createdAtDesc, LIMIT_NUM);
		} finally {
			close(connection);
		}
	}
}

