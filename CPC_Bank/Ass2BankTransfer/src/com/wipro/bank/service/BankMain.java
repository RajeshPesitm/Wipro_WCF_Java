package com.wipro.bank.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.dao.BankDAO;
import com.wipro.bank.util.InsufficientFundsException;

public class BankMain {
	public static void main(String[] args) {
		//write code here
	}

	public String checkBalance(String accountNumber) {
		
		//write code here
		BankDAO bankDAO = new BankDAO();

		if (bankDAO.validateAccount(accountNumber)) {

			float balance = bankDAO.findBalance(accountNumber);

			return "BALANCE IS:" + balance;

		} else {

			return "ACCOUNT NUMBER INVALID";
		}
	}
	
	public String transfer(TransferBean transferBean) {
		String status = "";
	    //write code here
		BankDAO bankDAO = new BankDAO();

		try {
			// Step 1: Check if TransferBean is null
			if (transferBean == null) {
				return "INVALID";
			}

			// Step 2: Validate payer account
			if (!bankDAO.validateAccount(transferBean.getFromAccountNumber())) {
				return "INVALID ACCOUNT";
			}

			// Step 2: Validate beneficiary account
			if (!bankDAO.validateAccount(transferBean.getToAccountNumber())) {
				return "INVALID ACCOUNT";
			}

			// Step 3: Find balance of payer
			float fromBalance =
					bankDAO.findBalance(transferBean.getFromAccountNumber());

			float amount = transferBean.getAmount();

			// Step 4: Check sufficient funds
			if (fromBalance < amount) {
				throw new InsufficientFundsException();
			}

			// Find beneficiary balance
			float toBalance =
					bankDAO.findBalance(transferBean.getToAccountNumber());

			// Calculate new balances
			float newFromBalance = fromBalance - amount;
			float newToBalance = toBalance + amount;

			// Step 5: Update payer balance
			boolean payerUpdated =
					bankDAO.updateBalance(
							transferBean.getFromAccountNumber(),
							newFromBalance);

			// Update beneficiary balance
			boolean beneficiaryUpdated =
					bankDAO.updateBalance(
							transferBean.getToAccountNumber(),
							newToBalance);

			// Record transaction
			boolean transactionRecorded =
					bankDAO.transferMoney(transferBean);

			// Step 6: Check successful transfer
			if (payerUpdated && beneficiaryUpdated && transactionRecorded) {
				status = "SUCCESS";
			}

		} catch (InsufficientFundsException e) {
			status = "INSUFFICIENT FUNDS";
		}

		return status;
	}
}
