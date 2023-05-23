package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Waybill;

public class WaybillDao {

	public WaybillDao() {

	}

	public List<Waybill> selectAll() {

		List<Waybill> list = new ArrayList<>();

		try {
			Connection conn = SuperDao.getConnection();
			String sql = "select * from waybill";
			System.out.println(sql);

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
				Waybill vo = new Waybill();
				vo.setWaybillNo(re.getInt("waybill_no"));
				vo.setRcvrName(re.getString("rcvr_name"));
				vo.setRcvrAddr(re.getString("rcvr_addr"));
				vo.setRcvrCp(re.getString("rcvr_cp"));
				vo.setCompanyCd(re.getString("company_cd"));
				vo.setUserId(re.getString("user_id"));
				vo.setNonCp(re.getString("non_cp"));				
				vo.setRegDate(re.getDate("reg_date"));
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

	public Waybill selectById(String userId) {
		
		Waybill vo = null;

		try {
			Connection conn = SuperDao.getConnection();
			String sql = "select * from waybill where waybill_no=?";

			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet re = stmt.executeQuery();
			if(re != null) {
				vo = new Waybill();
				vo.setWaybillNo(re.getInt("waybill_no"));
				vo.setRcvrName(re.getString("rcvr_name"));
				vo.setRcvrAddr(re.getString("rcvr_addr"));
				vo.setRcvrCp(re.getString("rcvr_cp"));
				vo.setCompanyCd(re.getString("company_cd"));
				vo.setUserId(re.getString("user_id"));
				vo.setNonCp(re.getString("non_cp"));				
				vo.setRegDate(re.getDate("reg_date"));
			}
			re.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return vo;
	}

	public void create(Waybill vo) {


		try {
			Connection conn = SuperDao.getConnection();
			String sql = "insert into waybill(waybill_no,rcvr_name,rcvr_addr,rcvr_cp,company_cd,user_id,non_cp) value(?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, vo.getWaybillNo());
			stmt.setString(2, vo.getRcvrName());
			stmt.setString(3, vo.getRcvrAddr());
			stmt.setString(4, vo.getRcvrCp());
			stmt.setString(5, vo.getCompanyCd());
			stmt.setString(6, vo.getUserId());
			stmt.setString(7, vo.getNonCp());

			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void update(Waybill vo) {
		try {
			Connection conn = SuperDao.getConnection();
			

			String sql = "update waybill set  rcvr_name = ?,rcvr_addr = ?, rcvr_cp = ?, company_cd= ?, user_id=?, non_cp =?  where waybill_no=? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getRcvrName());
			stmt.setString(2, vo.getRcvrAddr());
			stmt.setString(3, vo.getRcvrCp());
			stmt.setString(4, vo.getCompanyCd());
			stmt.setString(5, vo.getUserId());
			stmt.setString(6, vo.getNonCp());
			stmt.setInt(7, vo.getWaybillNo());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(int waybillNo) {

		try {
			Connection conn = SuperDao.getConnection();
			

			String sql = "delete from waybill where waybill_no=? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, waybillNo);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
