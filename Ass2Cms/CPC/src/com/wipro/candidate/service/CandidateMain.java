package com.wipro.candidate.service;

import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.WrongDataException;

public class CandidateMain {

	/**
	 * @param args
	 */
	public String addCandidate(CandidateBean candBean) {
		try {
			// 1. Validation Rules
			if (candBean == null) {
				throw new WrongDataException();
			}
			
			if (candBean.getName() == null || candBean.getName().trim().equals("")) {
				throw new WrongDataException();
			}
			
			if (candBean.getName().length() < 2) {
				throw new WrongDataException();
			}
			
			if (candBean.getM1() < 0 || candBean.getM1() > 100 ||
				candBean.getM2() < 0 || candBean.getM2() > 100 ||
				candBean.getM3() < 0 || candBean.getM3() > 100) {
				throw new WrongDataException();
			}
			
			// 2. Generate Candidate ID
			CandidateDAO dao = new CandidateDAO();
			String generatedId = dao.generateCandidateId(candBean.getName());
			candBean.setId(generatedId);
			
			// 3. Compute Results and Grade Structure
			int totalMarks = candBean.getM1() + candBean.getM2() + candBean.getM3();
			String result = "";
			String grade = "";
			
			if (totalMarks >= 240) {
				result = "PASS";
				grade = "Distinction";
			} else if (totalMarks >= 180) {
				result = "PASS";
				grade = "First Class";
			} else if (totalMarks >= 150) {
				result = "PASS";
				grade = "Second Class";
			} else if (totalMarks >= 105) {
				result = "PASS";
				grade = "Third Class";
			} else {
				result = "FAIL";
				grade = "No Grade";
			}
			
			candBean.setResult(result);
			candBean.setGrade(grade);
			
			// 4. Save Record to Database
			String dbStatus = dao.addCandidate(candBean);
			
			// FIX FOR ARRAYINDEXOUTOFBOUNDS: 
			// Return the properly formatted ID:RESULT string to satisfy split(":") test queries
			return candBean.getId() + ":" + candBean.getResult();
			
		} catch (WrongDataException e) {
			return e.toString(); // Returns "Data Incorrect"
		}
	}

	public ArrayList<CandidateBean> displayAll(String criteria) {
		try {
			if (criteria == null || 
				(!criteria.equalsIgnoreCase("PASS") && 
				 !criteria.equalsIgnoreCase("FAIL") && 
				 !criteria.equalsIgnoreCase("ALL"))) {
				throw new WrongDataException();
			}
			
			CandidateDAO dao = new CandidateDAO();
			ArrayList<CandidateBean> list = dao.getByResult(criteria);
			
			// FIX FOR NULLPOINTEREXCEPTION:
			// If no records match, return an empty initialized list instead of raw null
			if (list == null) {
				return new ArrayList<CandidateBean>();
			}
			
			return list;
			
		} catch (WrongDataException e) {
			return null;
		}
	}


	public static void main(String[] args) {
		CandidateMain candidateMain = new CandidateMain();
		CandidateBean inputBean = new CandidateBean();
		inputBean.setName("Jacob");
		inputBean.setM1(85);
		inputBean.setM2(90);
		inputBean.setM3(80);
		String finalResult = candidateMain.addCandidate(inputBean);
		
	}

}
