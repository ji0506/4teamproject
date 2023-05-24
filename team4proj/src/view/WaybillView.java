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
		
//		
//		UserView userV = new UserView();
//		Nonuser Nuser = new Nonuser();
//		String userId;
//		
//		SuperDao.Load();
//
//		System.out.print("주소: ");
//		WaybillView view = new WaybillView();
//		String str = scan.nextLine();
//		int zipcode = view.getzipCode(str);
//		System.out.println(zipcode);
//
//		
//		
//		SuperDao.close();
//	}
//	
//	public int getzipCode(String line) {
//
//		String[] strToStrArray = line.split(" ");
//		WaybillDao wdao = new WaybillDao();
//		int zipcode = 0;
//		
//		
//		//가평군 등의 '군'의 경우
//		if(strToStrArray.length == 4)
//		{
//			//문자열을 잘라 도,시,동, 번호 구분			
//			String [] numTobunum = strToStrArray[3].split("-");
//			if(numTobunum.length >= 2) 	//부 번호가 있는경우			
//				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1],strToStrArray[2], Integer.parseInt(numTobunum[0]),Integer.parseInt(numTobunum[1]));
//			else  //부 번호가 없는경우	
//				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1],strToStrArray[2], Integer.parseInt(numTobunum[0]));
//
//			
//		} 
//		//안산시 등의 '시'의 경우
//		else {
//			//문자열을 잘라 도,시,동, 번호 구분			
//			String [] numTobunum = strToStrArray[4].split("-");
//			if(numTobunum.length >= 2) //부 번호가 있는경우	
//				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],strToStrArray[3], Integer.parseInt(numTobunum[0]),Integer.parseInt(numTobunum[1]));				
//			else //부 번호가 없는경우	
//				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],strToStrArray[3], Integer.parseInt(numTobunum[0]));				
//		}
//		
//		return zipcode;
	}

	
}
