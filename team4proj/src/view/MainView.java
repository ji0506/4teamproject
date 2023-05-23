package view;

import java.util.Scanner;
import dao.SuperDao;

public class MainView {

	private static Scanner scan = new Scanner(System.in);

	public void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}

	public static void main(String[] args) {

		UserView userV = new UserView();
		String userId;
		SuperDao.Load();

		while (true) {
			System.out.println("--------------------------------------------------------");
			System.out.println("                     [메인 메뉴]");
			System.out.println("--------------------------------------------------------");
			System.out.printf("%-20s%-20s%-20s\n", "1. 회원 택배 접수", "2. 비회원 택배 접수", "3. 회원 가입");
			System.out.println("다른 숫자 입력시 종료됩니다.");
			System.out.println("--------------------------------------------------------");

			System.out.print("메뉴 선택: ");
			String menuNo = scan.nextLine();

			if ("1".equals(menuNo)) {
				userId = userV.Login();

				if (userId != "fail") {
					System.out.println("로그인에 성공하셨습니다.");
					break;
				} else {
					System.out.println("로그인에 실패하셨습니다.");
					continue;
				}
			} else if ("2".equals(menuNo)) {
				userV.Non_userlogin();
				break;
			} else if ("3".equals(menuNo)) {
				userV.JoinUser();
				main(args);
				break;
			} else
				return;
		}
		
		SuperDao.close();

	}

}
