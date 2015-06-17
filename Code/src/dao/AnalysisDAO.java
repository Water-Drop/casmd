package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCHelper;

public class AnalysisDAO {
	JDBCHelper jh = new JDBCHelper();
	public String getLevelByMD5(String fileMd5){
		String rtn = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = jh.getConnection();
			ps = conn.prepareStatement("SELECT Id,Level,Status FROM casmd.verifyfile WHERE FileMD5=? AND Status!=2");
			ps.setString(1, fileMd5);
			rs = ps.executeQuery();
			if (rs.next() == true){
				rtn = rs.getString("Level");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jh.close(conn);
		}
		return rtn;
	}
}
