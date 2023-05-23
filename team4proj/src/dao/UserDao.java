package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDao {

	public UserDao() {

	}

	public List<User> selectAll() {

		List<User> list = new ArrayList<>();

		try {
			Connection conn = SuperDao.getConnection();
			String sql = "select * from user";
			System.out.println(sql);

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
				User vo = new User();
				vo.setUserId(re.getString("user_id"));
				vo.setUserName(re.getString("user_name"));
				vo.setUserCp(re.getString("user_cp"));
				vo.setUserAddr(re.getString("user_addr"));
				list.add(vo);
			}
			re.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public User selectById(String userId) {
		
		User vo = null;

		try {
			Connection conn = SuperDao.getConnection();
			String sql = "select * from user where user_id=?";

			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet re = stmt.executeQuery();
			if(re != null) {
				vo = new User();
				vo.setUserId(re.getString("user_id"));
				vo.setUserPwd(re.getString("user_pw"));
				vo.setUserName(re.getString("user_name"));
				vo.setUserCp(re.getString("user_cp"));
				vo.setUserAddr(re.getString("user_addr"));
			}
			re.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return vo;
	}

	public void create(User vo) {


		try {
			Connection conn = SuperDao.getConnection();
			String sql = "insert into user value(?,?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, vo.getUserId());
			stmt.setString(2, vo.getUserName());
			stmt.setString(3, vo.getUserPwd());
			stmt.setString(4, vo.getUserAddr());
			stmt.setString(5, vo.getUserCp());
			stmt.setString(6, vo.getUserEmail());

			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void update(User vo) {
		try {
			Connection conn = SuperDao.getConnection();
			

			String sql = "update user set  user_name = ?,user_pw = ?, user_addr = ?, user_cp= ?, user_email=?   where user_id=? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getUserName());
			stmt.setString(2, vo.getUserPwd());
			stmt.setString(3, vo.getUserAddr());
			stmt.setString(4, vo.getUserCp());
			stmt.setString(5, vo.getUserEmail());			
			stmt.setString(6, vo.getUserId());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(User vo) {

		try {
			Connection conn = SuperDao.getConnection();
			

			String sql = "delete from user where user_id=? and user_pw=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getUserId());
			stmt.setString(2, vo.getUserPwd());
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
