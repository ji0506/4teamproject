package view;

import java.util.Scanner;

import dao.SuperDao;
import model.Nonuser;
import model.Waybill;

public class MainView {

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

		while (true) {
			System.out.println("--------------------------------------------------------");
			System.out.println("                      [메인 메뉴]");
			System.out.println("--------------------------------------------------------");
			System.out.println();
			System.out.printf("\t%-20s\t%-20s\n", "1. 회원 택배 접수", "2. 비회원 택배 접수", "3. 회원 가입");
			System.out.println();
			
			System.out.printf("\t%-20s\t%-20s\n", "3. 회원 가입", "4. 택배 접수 조회"); 
			System.out.println();
			System.out.println("다른 숫자 입력시 종료됩니다.");
			System.out.println("--------------------------------------------------------");

			System.out.print("메뉴 선택: ");
			String menuNo = scan.nextLine();

			if ("1".equals(menuNo)) {
				userId = userV.Login();

				if (userId != "fail") {
					System.out.println("로그인에 성공하셨습니다.");
					
				} else {
					System.out.println("로그인에 실패하셨습니다.");
					continue;
				}
			} else if ("2".equals(menuNo)) {
				String nonusercp = userV.Non_userlogin();
				
				if (nonusercp != "fail") {
					System.out.println("비회원 로그인에 성공하셨습니다.");
					break;
				} else {
					System.out.println("비회원 로그인에 실패하셨습니다. 다시 시도해 주십시오");
					continue;
				}
			} else if ("3".equals(menuNo)) {
				String sign = userV.JoinUser();
				if (sign != "fail") {
					System.out.println("회원 가입이 완료하셨습니다.");
					break;
				} else {
					System.out.println("회원 가입에 실패하셨습니다. 다시 시도해 주십시오");
					continue;
				}
			} else if ("4".equals(menuNo)) {
				//택배조회 기능
				continue;
			} else {
				
				SuperDao.close();
				exit();
			}

		}
		
		
	}

}
