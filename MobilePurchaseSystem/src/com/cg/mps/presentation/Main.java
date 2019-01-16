package com.cg.mps.presentation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.mps.exception.MpsException;
import com.cg.mps.model.CustomerDetails;
import com.cg.mps.sevice.CustomerDetaisService;
import com.cg.mps.sevice.CustomerDetaisServiceImpl;

public class Main {

	public static void main(String[] args) throws MpsException {

		System.out
				.println("----****----****----****----Menu----****----****----****----");
		System.out.println("1. Purchase Mobile");
		System.out.println("2. View Entire Stock in Store");
		System.out
				.println("3. Delete Mobile Details based on MobileID");
		System.out.println("4. Search Mobile Phones Based on Price");
		System.out
				.println("----****----****----****----*******----****----****----****----");

		CustomerDetails customerDetails = new CustomerDetails();
		Scanner scanner = new Scanner(System.in);
		CustomerDetaisService customerDetailsService = new CustomerDetaisServiceImpl();

		Integer adminChoice = 0;
		int mobileId=0;
		List<CustomerDetails> list = null;

		try {
			System.out.println("Enter your Choice: ");
			adminChoice = scanner.nextInt();

			switch (adminChoice) {
			case 1:
				System.out.println("Enter Customer Details");
				System.out.println("Name: ");
				scanner.nextLine();
				String CustomerName = scanner.nextLine();

				System.out.println("Enter E-mail: ");
				String eMailId = scanner.nextLine();

				System.out.println("Enter Mobile No: ");
				String customerMobileNumber = scanner.nextLine();

				System.out.println("Mobile ID: ");
				mobileId = scanner.nextInt();

				customerDetails.setCustomerMobileNumber(customerMobileNumber);
				customerDetails.setCustomerName(CustomerName);
				customerDetails.seteMailId(eMailId);
				customerDetails.setMobileId(mobileId);

				boolean validateFlag= customerDetailsService
						.validateDetails(customerDetails);
				if(validateFlag){
					int insertedRecords=customerDetailsService.PurchaseMobileNew(customerDetails);
					System.out.println(insertedRecords+" Records Inserted...");
				}

				break;

			case 2:
				list= new ArrayList<CustomerDetails>();
				list= customerDetailsService.viewEntireStock();
				if(list.size()>0){
					System.out.println("MobileID      Mobile Name       Price        Quantity");
					for (CustomerDetails customerDetails2 : list) {
						System.out.println(customerDetails2.getMobileId()+"       "+customerDetails2.getMobileName()+"      "+customerDetails2.getMobilePrice()+ "        "+customerDetails2.getQuantity());
					}	
				}else{
					System.err.println("No Mobiles Records Found");
				}
				break;
			case 3:
				System.out.println("Enter MobileId: ");
				mobileId=scanner.nextInt();
				int deletedRecord=customerDetailsService.deleteMobileRecord(mobileId);
				System.out.println(deletedRecord+" Records Deleted");
				break;
			case 4:
				System.out.println("Enter Minimum price:");
				int minPrice=scanner.nextInt();
				System.out.println("Enter Maximum price: ");
				int maxPrice=scanner.nextInt();
				list=customerDetailsService.getRecordsByPrice(minPrice, maxPrice);
				
				if(list.size()>0){
					System.out.println("MobileID      Mobile Name       Price");
					for (CustomerDetails customerDetails2 : list) {
						System.out.println(customerDetails2.getMobileId()+"       "+customerDetails2.getMobileName()+"      "+customerDetails2.getMobilePrice());
						
					}
				}else{
					System.err.println("No such Mobiles found in Stock");
				}
				
				break;

			default:
				System.err.println("Invalid Choice");
				break;
			}

		} catch (InputMismatchException e) {
			
			System.err
					.println("Please Enter your choice in Digits between 1 to 4 only...");
		}

		scanner.close();

	}
}
