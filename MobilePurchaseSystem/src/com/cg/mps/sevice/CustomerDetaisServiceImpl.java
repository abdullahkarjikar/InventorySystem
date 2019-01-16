package com.cg.mps.sevice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.mps.dao.CustomerDao;
import com.cg.mps.dao.CustomerDaoImpl;
import com.cg.mps.exception.MpsException;
import com.cg.mps.model.CustomerDetails;

public class CustomerDetaisServiceImpl implements CustomerDetaisService {
	CustomerDetails customerDetails = new CustomerDetails();
	CustomerDao customerDao = new CustomerDaoImpl();

	@Override
	public boolean validateDetails(CustomerDetails customerDetails)
			throws MpsException {
		List<String> list = new ArrayList<String>();
		boolean validateFlag=false;

		if (!validateName(customerDetails.getCustomerName())) {
			list.add("First Letter Must be Capital and in length is 5 Characters");
		}
		if (!validateEmail(customerDetails.geteMailId())) {
			list.add("Invalid eMail id");
		}
		if (!validateMobile(customerDetails.getCustomerMobileNumber())) {
			list.add("Mobile Number must start with 6|7|8|9 and should have 10 digits");
		}
		if (!customerDao.getMobileId(customerDetails.getMobileId())) {
			list.add("This MobileId doesnot exists or not in Stock");
		}
		if (list.size() > 0) {
			validateFlag=false;
			System.err.println(list);
			
		} else {
			validateFlag=true;
		}

		return validateFlag;
	}

	@Override
	public boolean validateName(String customerName) {
		String customerNameRegEx = "[A-Z]{1}[a-zA-Z\\s]{4,19}$";
		boolean nameflag = false;
		nameflag = Pattern.matches(customerNameRegEx, customerName);
		return nameflag;
	}

	@Override
	public boolean validateEmail(String eMailId) {
		String eMailIdRegEx = "[A-Za-z.0-9]*@[A-za-z]*\\.[A-za-z]*";
		boolean eMailIdflag = false;
		eMailIdflag = Pattern.matches(eMailIdRegEx, eMailId);
		return eMailIdflag;
	}

	@Override
	public boolean validateMobile(String customerMobileNumber) {
		String mobileNumberRegEx = "[6|7|8|9]{1}[0-9]{9}$";
		boolean mobileNumber = false;
		mobileNumber = Pattern.matches(mobileNumberRegEx, customerMobileNumber);
		return mobileNumber;
	}

	@Override
	public List<CustomerDetails> viewEntireStock() throws MpsException {
		return customerDao.viewEntireStock();
	}

	@Override
	public int deleteMobileRecord(int mobileId) throws MpsException {
		return customerDao.deleteRecordByMobileId(mobileId);
	}

	@Override
	public List<CustomerDetails> getRecordsByPrice(int minPrice, int maxPrice) throws MpsException {
		return customerDao.getRecordsByPrice(minPrice,maxPrice);
	}

	@Override
	public int PurchaseMobileNew(CustomerDetails customerDetails)
			throws MpsException {
		return customerDao.insertNewPurchaseDetails(customerDetails);
	}
}
