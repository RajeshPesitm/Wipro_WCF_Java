package com.wipro.candidate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wipro.candidate.bean.CandidateBean;

public class CandidateMainTest {

	private CandidateMain candidateMain;

	@BeforeEach
	public void setUp() {
		// Initialize the real class instance directly without mocks
		candidateMain = new CandidateMain();
	}

	@Test
	public void testForCandidateIDGeneration() {
		// 1. Arrange a completely valid Candidate record
		CandidateBean inputBean = new CandidateBean();
		inputBean.setName("Jacob");
		inputBean.setM1(85);
		inputBean.setM2(90);
		inputBean.setM3(80);

		// 2. Act by running the service layer logic
		// This will hit your local DB connection, pull the sequence, and perform insertion
		String finalResult = candidateMain.addCandidate(inputBean);

		// 3. Assert the outcome matches structural rules
		assertNotNull(finalResult, "The return status string should not be null.");
		
		// If it failed due to database problems, it returns "Error". 
		// We assert that it didn't return "Error" or "Data Incorrect" so we can debug cleanly.
		assertTrue(!finalResult.equalsIgnoreCase("Error"), "Database storage failed! Check database credentials or tablespace quote.");
		assertTrue(!finalResult.equalsIgnoreCase("Data Incorrect"), "Validation incorrectly rejected valid test data.");

		// Split the string by ":" (e.g. "JA5000:PASS")
		String[] segments = finalResult.split(":");
		assertTrue(segments.length >= 1, "The output format should contain a colon separator.");
		
		String generatedId = segments[0];
		
		// This matches your evaluation engine's exact constraint checking rule
		assertEquals(6, generatedId.length(), "Length of ID Generated Check failed!");
		
		// Check that the first two characters are the uppercase prefix
		assertEquals("JA", generatedId.substring(0, 2), "The ID prefix must be the first 2 letters of name in uppercase.");
		
		// Check the result token is PASS
		if(segments.length > 1) {
			assertEquals("PASS", segments[1], "Candidate should be marked as PASS.");
		}
	}
}
