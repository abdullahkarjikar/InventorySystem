package com.cg.mps.dao;

import java.util.List;

import com.cg.mps.exception.MpsException;
import com.cg.mps.model.CustomerDetails;

public interface CustomerDao {

	boolean getMobileId(int mobileId)throws MpsException;

	int insertNewPurchaseDetails(CustomerDetails customerDetails) throws MpsException;

	List<CustomerDetails> viewEntireStock() throws MpsException;

	int deleteRecordByMobileId(int mobileId)throws MpsException;

	List<CustomerDetails> getRecordsByPrice(int minPrice, int maxPrice)throws MpsException;

	

}
