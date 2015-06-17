package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.VerifyFile;
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
	public Integer addAnalysisResult(VerifyFile vf){
		Connection conn = null;
		PreparedStatement ips = null;
		PreparedStatement sps = null;
		ResultSet rs = null;
		Integer vid = -1;
		try {
			conn = jh.getConnection();
			conn.setAutoCommit(false);
			ips = conn.prepareStatement("INSERT INTO casmd.verifyfile (Level,FileMD5,FilePath,Status) VALUES (?,?,?,0)");
			ips.setString(1,vf.getLevel());
			ips.setString(2,vf.getFileMD5());
			ips.setString(3,vf.getFilePath());
			sps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			ips.execute();
			rs = sps.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			rs.last();
			int count = rs.getRow();
			if (count != 0){
				rs.first();
				vid = Integer.parseInt(rs.getBigDecimal(1).toString());
				}
			} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			jh.close(conn);
		}
		return vid;	
	}
}
