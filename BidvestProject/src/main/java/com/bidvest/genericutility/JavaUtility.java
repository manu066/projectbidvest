package com.bidvest.genericutility;

import java.util.Date;
import java.util.Random;

/**
 * it's Contains java specific libraries like getRandomdata & getsystemDate   
 * @author Manu
 *
 */

public class JavaUtility {
	/**
	 * its used to generate the random number with in the range of 1000
	 * @return
	 */
	public static int getRanDomNumber() {
		Random ranDom = new Random();
		int ranDomNum =  ranDom.nextInt(1000);
		return ranDomNum;

	}
	/**
	 *  its used to get the current system date based on YYYY-MM-DD format
	 * @return
	 */
	public String  getSystemDate() {
		Date date = new Date();
		String currentDate = date.toString();
		System.out.println(currentDate);
		String[] arr = currentDate.split(" ");

		String yyyy = arr[5];
		String dd = arr[2];
		int mm = date.getMonth()+1;

		String formate = yyyy+"-"+mm+"-"+dd;
		return formate;
	}
	/**
	 *  its used to get the current system date based on DD-MM-YYYY format 
	 * @return
	 */
	public String  getSystemDateInIST() {
		Date date = new Date();
		String currentDate = date.toString();
		System.out.println(currentDate);
		String[] arr = currentDate.split(" ");

		String yyyy = arr[5];
		String dd = arr[2];
		int mm = date.getMonth()+1;

		String formate = dd+"-"+mm+"-"+yyyy;
		return formate;


	}
}
