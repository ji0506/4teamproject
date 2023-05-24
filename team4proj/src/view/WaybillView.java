package view;

import java.util.Scanner;

import model.Parcel;

public class WaybillView {

	private static Scanner scan = new Scanner(System.in);

	public static void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}
	
	public static void waybillList () {
		
		System.out.println("운송장 조회");
		System.out.println("1. 회원 조회 2. 비회원 조회");
		System.out.println("---------------------");
		System.out.print("메뉴 선택 : ");
		String subMenuNo = scan.nextLine();
		if ("1".equals(subMenuNo)) {
								
		} else if ("2".equals(subMenuNo)) {
			
		} else {
			MainView.main(null);
		}
	}

	public static void waybillList(String nonUserCp) {
		// 비회원 조회 
		System.out.println("비회원 운송장 목록");
		
	}

	public static void waybillList(String userId, String userPwd) {

		System.out.println("회원 운송장 목록");

	}
	
	
	public static void waybillInfo (String waybillNum) {
		
		
		System.out.println("┌--------------------------------------------------------------------------┐");
		System.out.println("|                                                                          |");
		 System.out.printf("| 운송장 번호 : %-30s|| 택배사 : %-23s|","0000000000", "경동택배");
	}
	
	
	public static void main(String[] args) {
		waybillInfo("");
	}

}
