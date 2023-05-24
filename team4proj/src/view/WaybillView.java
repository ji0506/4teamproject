package view;

import java.util.Scanner;

import dao.SuperDao;
import dao.WaybillDao;
import model.Nonuser;
import model.Waybill;

public class WaybillView {

	private static Scanner scan = new Scanner(System.in);

	public static void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}

	public static void main(String[] args) {
		
		
		UserView userV = new UserView();
		Nonuser Nuser = new Nonuser();
		String userId;
		
		SuperDao.Load();

		System.out.print("주소: ");
		String str = scan.nextLine();


		
		
		SuperDao.close();
	}
	
	public int getzipCode(String line) {

		String[] strToStrArray = line.split(" ");
		WaybillDao wdao = new WaybillDao();
		int zipcode = 0;
		if(strToStrArray.length == 4)
		{
			String [] numTobunum = strToStrArray[3].split("-");
			if(numTobunum.length >= 2) 
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1],strToStrArray[2], Integer.parseInt(numTobunum[0]),Integer.parseInt(numTobunum[1]));
			else 
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1],strToStrArray[2], Integer.parseInt(numTobunum[0]));

			
		} else {
			
			String [] numTobunum = strToStrArray[4].split("-");
			if(numTobunum.length >= 2) 
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],strToStrArray[3], Integer.parseInt(numTobunum[0]),Integer.parseInt(numTobunum[1]));				
			else
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],strToStrArray[3], Integer.parseInt(numTobunum[0]));				
			System.out.println(zipcode);

		}
		
		return zipcode;
	}

}
