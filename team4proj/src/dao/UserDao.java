package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Useraddress;

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
			while (re.next()) {
				vo = new User();
				vo.setUserId(re.getString("user_id"));
				vo.setUserPwd(re.getString("user_pw"));
				vo.setUserName(re.getString("user_name"));
				vo.setUserCp(re.getString("user_cp"));
				vo.setUserAddr(re.getString("user_addr"));
			}
			re.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return vo;
	}

	public boolean create(User vo) {


		try {
			Connection conn = SuperDao.getConnection();
			String sql = "insert into user values(?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, vo.getUserId());
			stmt.setString(2, vo.getUserName());
			stmt.setString(3, vo.getUserPwd());
			stmt.setString(4, vo.getUserAddr());
			stmt.setString(5, vo.getUserCp());

			stmt.executeUpdate(); // 여기서 에러
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void update(User vo) {
		try {
			Connection conn = SuperDao.getConnection();
			

			String sql = "update user set  user_name = ?,user_pw = ?, user_addr = ?, user_cp= ? where user_id=? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getUserName());
			stmt.setString(2, vo.getUserPwd());
			stmt.setString(3, vo.getUserAddr());
			stmt.setString(4, vo.getUserCp());
			stmt.setString(5, vo.getUserId());

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

	
	
	public boolean createUserAddress(String userId, String rcvrName, String rcvrAddr, String rcvrCp) {


		try {
			Connection conn = SuperDao.getConnection();
			String sql = "insert into user_address(user_id,rcvr_name,rcvr_addr,rcvr_cp) values(?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, rcvrName);
			stmt.setString(3, rcvrAddr);
			stmt.setString(4, rcvrCp);

			stmt.executeUpdate(); // 여기서 에러
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createUserAddress(Useraddress vo) {


		try {
			Connection conn = SuperDao.getConnection();
			String sql = "insert into user_address(user_id,rcvr_name,rcvr_addr,rcvr_cp) values(?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getUserId());
			stmt.setString(2, vo.getRcvrName());
			stmt.setString(3, vo.getRcvrAddr());
			stmt.setString(4, vo.getRcvrCp());

			stmt.executeUpdate(); // 여기서 에러
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public Useraddress selectUserAdddress(String userId) {
		
		Useraddress vo = null;

		try {
			Connection conn = SuperDao.getConnection();
			String sql = "select * from user_address where user_id=?";

			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
				vo = new Useraddress();
				vo.setUserId(re.getString("user_id"));
				vo.setRcvrName(re.getString("rcvr_name"));
				vo.setRcvrAddr(re.getString("rcvr_addr"));
				vo.setRcvrCp(re.getString("rcvr_cp"));
			}
			re.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return vo;
	}
	
	
	public void updateAddressCount(Useraddress vo) {
		try {
			Connection conn = SuperDao.getConnection();
			

			String sql = "update user set freq  = ? where user_id=? and rcvr_name=? and rcvr_addr=? and rcvr_cp=? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getFreq());
			stmt.setString(2, vo.getUserId());
			stmt.setString(3, vo.getRcvrName());
			stmt.setString(4, vo.getRcvrAddr());
			stmt.setString(5, vo.getRcvrCp());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
