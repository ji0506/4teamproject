package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Nonuser;
import model.User;

public class NonuserDao {

	public NonuserDao() {

	}

	public List<Nonuser> selectAll() {

		List<Nonuser> list = new ArrayList<>();

		try {
			Connection conn = SuperDao.getConnection();
			String sql = "select * from nonuser";
			System.out.println(sql);

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
				Nonuser vo = new Nonuser();
				vo.setNonuserCp(re.getString("non_cp"));
				vo.setNonuserName(re.getString("non_name"));
				vo.setNonuserAddr(re.getString("non_addr"));
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

	public Nonuser selectById(String nonCp) {
		
		Nonuser vo = null;

		try {
			Connection conn = SuperDao.getConnection();
			String sql = "select * from nonuser where non_cp=?";

			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nonCp);
			ResultSet re = stmt.executeQuery();
			if(re != null) {
				vo = new Nonuser();
				vo.setNonuserCp(re.getString("non_cp"));
				vo.setNonuserName(re.getString("non_name"));
				vo.setNonuserAddr(re.getString("non_addr"));
			}
			re.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return vo;
	}

	public void create(Nonuser vo) {


		try {
			Connection conn = SuperDao.getConnection();
			String sql = "insert into nonuser value(?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, vo.getNonuserCp());
			stmt.setString(2, vo.getNonuserName());
			stmt.setString(3, vo.getNonuserAddr());
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void update(Nonuser vo) {
		try {
			Connection conn = SuperDao.getConnection();
			

			String sql = "update nonuser set  non_name = ?,non_addr = ?  where non_cp=? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getNonuserName());
			stmt.setString(2, vo.getNonuserAddr());
			stmt.setString(3, vo.getNonuserCp());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Nonuser vo) {

		try {
			Connection conn = SuperDao.getConnection();
			

			String sql = "delete from nonuser where non_cp=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getNonuserCp());
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
