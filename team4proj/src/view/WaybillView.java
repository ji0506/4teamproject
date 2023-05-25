package view;

import java.util.Scanner;

import dao.WaybillDao;
import model.Parcel;

public class WaybillView {

	private static Scanner scan = new Scanner(System.in);
	
	
	public static void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}

	public static void waybillInfo (String waybillNum) {
		
		
		System.out.println("┌--------------------------------------------------------------------------┐");
		System.out.println("|                                                                          |");
		 System.out.printf("| 운송장 번호 : %-30s|| 택배사 : %-23s|","0000000000", "경동택배");
	}
	

	public static void wbList() {
		WaybillDao wbDao = new WaybillDao();
		System.out.println("택배접수 조회입니다.");
		System.out.println("1. 회원 접수 조회  2. 비회원 접수 조회");
		System.out.println("메뉴선택 : ");
		String menuNo = scan.nextLine();

		if ("1".equals(menuNo)) {
			System.out.println("조회할 송장의 송장번호를 입력해주세요.");
			System.out.println("송장 번호 : ");
			String wbNum = scan.nextLine();
			
		}

	}

	public static void wbList(String cp) {

	}

	public static void wbList(String Id, String pw) {

	}

	public static void main(String[] args) {

	}
}

