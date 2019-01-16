package com.cg.mps.dao;

public interface QueryMapper {
	public static final String getMobileIdQuery=
			"SELECT Quantity FROM Mobiles WHERE MobileId=?";
	public static final String insertNewPurchaseDetails = 
			"INSERT INTO PurchaseDetails VALUES(Generate_Purchase_Id.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
	public static final String updateMobileQuantity = 
			"UPDATE Mobiles SET Quantity=Quantity-1 WHERE MobileId=?";
	public static final String viewEntireStockQuery = "SELECT * FROM Mobiles";
	public static final String deleteByMobileIdQuery = "DELETE from Mobiles WHERE MobileId=?";
	public static final String getRecordsByPrice = "SELECT * FROM Mobiles WHERE Price BETWEEN ? AND ?";
}


