package com.cg.mps.sevice;

import java.util.List;

import com.cg.mps.exception.MpsException;
import com.cg.mps.model.CustomerDetails;

public interface CustomerDetaisService {

	boolean validateDetails(CustomerDetails customerDetails)throws MpsException;

	boolean validateName(String customerName);

	boolean validateEmail(String eMailId);

	boolean validateMobile(String customerMobileNumber);

	List<CustomerDetails> viewEntireStock()throws MpsException;

	int deleteMobileRecord(int mobileId)throws MpsException;

	List<CustomerDetails> getRecordsByPrice(int minPrice, int maxPrice) throws MpsException;

	int PurchaseMobileNew(CustomerDetails customerDetails) throws MpsException;

}
