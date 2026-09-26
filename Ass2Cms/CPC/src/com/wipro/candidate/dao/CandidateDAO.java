package com.wipro.candidate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.util.DBUtil;

public class CandidateDAO {
	public String addCandidate(CandidateBean studentBean) {
		String status = "FAIL";
		// write code here
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO CANDIDATE_TBL (ID, NAME, M1, M2, M3, RESULT, GRADE) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DBUtil.getDBConn();
			if (conn != null) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, studentBean.getId());
				pstmt.setString(2, studentBean.getName());
				pstmt.setInt(3, studentBean.getM1());
				pstmt.setInt(4, studentBean.getM2());
				pstmt.setInt(5, studentBean.getM3());
				pstmt.setString(6, studentBean.getResult());
				pstmt.setString(7, studentBean.getGrade());

				int result = pstmt.executeUpdate();
				if (result > 0) {
					status = "SUCCESS";
				}
			}
		} catch (SQLException e) {
			System.out.println("[DAO SQL Error] Insertion failed! Details below:");
			e.printStackTrace(); // This will tell us if it's a constraint, size, or type error
			status = "FAIL";
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// Suppressed exception during cleanup as per standard failure policy
			}
		}

		return status;
	}

	public ArrayList<CandidateBean> getByResult(String criteria) {
		ArrayList<CandidateBean> list = new ArrayList<CandidateBean>();
		// write code here
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		if ("ALL".equalsIgnoreCase(criteria)) {
			sql = "SELECT * FROM CANDIDATE_TBL";
		} else if ("PASS".equalsIgnoreCase(criteria) || "FAIL".equalsIgnoreCase(criteria)) {
			sql = "SELECT * FROM CANDIDATE_TBL WHERE RESULT = ?";
		} else {
			return null;
		}

		try {
			conn = DBUtil.getDBConn();
			if (conn != null) {
				pstmt = conn.prepareStatement(sql);
				if (!"ALL".equalsIgnoreCase(criteria)) {
					pstmt.setString(1, criteria.toUpperCase());
				}

				rs = pstmt.executeQuery();
				while (rs.next()) {
					CandidateBean bean = new CandidateBean();
					bean.setId(rs.getString("ID"));
					bean.setName(rs.getString("NAME"));
					bean.setM1(rs.getInt("M1"));
					bean.setM2(rs.getInt("M2"));
					bean.setM3(rs.getInt("M3"));
					bean.setResult(rs.getString("RESULT"));
					bean.setGrade(rs.getString("GRADE"));
					list.add(bean);
				}
			}
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// Suppressed exception during cleanup
			}
		}

		list = list.isEmpty() ? null : list;
		return list;
	}

	public String generateCandidateId(String name) {
		String id = "JA5001";
		// write code here
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getDBConn();
			if (conn != null) {
				String sql = "SELECT CANDID_SEQ.NEXTVAL FROM DUAL";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					int seqVal = rs.getInt(1);
					//System.out.println("--- -inseide dao.genCanID()-----seqVAL ---"+seqVal);
					String prefix = name.substring(0, 2).toUpperCase();
					id = prefix + seqVal;
					//System.out.println("--- -inseide dao.genCanID() -----id ---"+id);

				}
			}
		} catch (SQLException e) {
			// Suppressed exception during cleanup
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// Suppressed exception during cleanup
			}
		}
		return id;
	}
}
