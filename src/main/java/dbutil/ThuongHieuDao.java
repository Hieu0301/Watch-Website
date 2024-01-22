package dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ConnectDB.ConnectDatabase;
import entity.DongHo;
import entity.ThuongHieu;

public class ThuongHieuDao {

	private static DataSource dataSource;

	public ThuongHieuDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public List<ThuongHieu> getAllBrands() {
		List<ThuongHieu> listThuongHieu = new ArrayList<ThuongHieu>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM ThuongHieu";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int thuongHieuId = rs.getInt("thuongHieuId");
				String name = rs.getString("name");
				ThuongHieu thuongHieu = new ThuongHieu(thuongHieuId, name);
				listThuongHieu.add(thuongHieu);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(conn, stmt, rs);
		}
		return listThuongHieu;
	}
	
	 public List<DongHo> getDongHoByThuongHieu(String txtSearch) {
		    Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
	        List<DongHo> list = new ArrayList<>();
	        String query = "SELECT * FROM DongHo\r\n"
	        		+ "WHERE ThuongHieu =? ";
	        try {
	            conn = new ConnectDatabase().getConnection();//mo ket noi voi sql
	            stmt = conn.prepareStatement(query);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	                list.add(new DongHo(rs.getInt(1), 
	                		rs.getString(2), 
	                		rs.getFloat(3), 
	                		rs.getInt(4),
	                		rs.getString(5),
	                		rs.getInt(6),
	                		rs.getString(7)));
	            }
	        } catch (Exception e) {
	        }
	        return list;
	    }
	 
	 public List<ThuongHieu> getAllThuongHieu() {
		    Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null; 
	        List<ThuongHieu> list = new ArrayList<>();
	        String query = "select * from ThuongHieu";
	        try {
	            conn = new ConnectDatabase().getConnection();//mo ket noi voi sql
	            stmt = conn.prepareStatement(query);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	                list.add(new ThuongHieu(rs.getInt(1),
	                        rs.getString(2)));
	            }
	        } catch (Exception e) {
	        }
	        return list;
	    }
	    

	private void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ThuongHieuDao dao = new ThuongHieuDao(dataSource);
		
		List<ThuongHieu> listTH = dao.getAllThuongHieu();
		for(ThuongHieu th : listTH) {
			System.out.println(th);
		}
			
	}
}
