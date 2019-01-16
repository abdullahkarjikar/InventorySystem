package com.cg.mps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.mps.exception.MpsException;
import com.cg.mps.model.CustomerDetails;
import com.cg.mps.utility.JdbcUtility;

public class CustomerDaoImpl implements CustomerDao {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	CustomerDetails customerDetails = null;

	@Override
	public boolean getMobileId(int mobileId) throws MpsException {
		boolean result = false;
		connection = JdbcUtility.getConnection();

		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.getMobileIdQuery);
			preparedStatement.setInt(1, mobileId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int checkQuantity = resultSet.getInt(1);
				if (checkQuantity > 0) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.getStackTrace();
			System.err.println("Get mobile ID::Prepared Statement Not Created");
		}
		return result;
	}

	@Override
	public int insertNewPurchaseDetails(CustomerDetails customerDetails)
			throws MpsException {
		connection = JdbcUtility.getConnection();
		int countInsertedRecords = 0;
		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.insertNewPurchaseDetails);
			preparedStatement.setString(1, customerDetails.getCustomerName());
			preparedStatement.setString(2, customerDetails.geteMailId());
			preparedStatement.setString(3,
					customerDetails.getCustomerMobileNumber());
			preparedStatement.setInt(4, customerDetails.getMobileId());

			countInsertedRecords = preparedStatement.executeUpdate();

			preparedStatement = connection
					.prepareStatement(QueryMapper.updateMobileQuantity);
			preparedStatement.setInt(1, customerDetails.getMobileId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Prepared Statement Failed");
		}
		return countInsertedRecords;
	}

	@Override
	public List<CustomerDetails> viewEntireStock() throws MpsException {
		List<CustomerDetails> list = new ArrayList<CustomerDetails>();
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.viewEntireStockQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customerDetails = new CustomerDetails();
				customerDetails.setMobileId(resultSet.getInt(1));
				customerDetails.setMobileName(resultSet.getString(2));
				customerDetails.setMobilePrice(resultSet.getDouble(3));
				customerDetails.setQuantity(resultSet.getInt(4));

				list.add(customerDetails);
			}
		} catch (SQLException e) {
			System.out.println("Prepared Statement not Created");
		}
		return list;
	}

	@Override
	public int deleteRecordByMobileId(int mobileId) throws MpsException {
		int deletedRecord = 0;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(QueryMapper.deleteByMobileIdQuery);
			preparedStatement.setInt(1, mobileId);
			deletedRecord = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Cannot Delete Because Record Exist with ID in Purchase Details");
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new MpsException("Prepared Statement not Closed");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				throw new MpsException("Connection not Closed");
			}
		}
		return deletedRecord;
	}

	@Override
	public List<CustomerDetails> getRecordsByPrice(int minPrice, int maxPrice) throws MpsException{
		connection=JdbcUtility.getConnection();
		List<CustomerDetails> list = new ArrayList<CustomerDetails>();
		try {
			preparedStatement=connection.prepareStatement(QueryMapper.getRecordsByPrice);
			preparedStatement.setInt(1, minPrice);
			preparedStatement.setInt(2, maxPrice);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				customerDetails= new CustomerDetails();
				customerDetails.setMobileId(resultSet.getInt(1));
				customerDetails.setMobileName(resultSet.getString(2));
				customerDetails.setMobilePrice(resultSet.getDouble(3));
				list.add(customerDetails);
			}
			
			
			
		} catch (SQLException e) {
			throw new MpsException(e.getMessage());
		}
		
		return list;
	}
}