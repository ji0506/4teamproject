package view;

import java.util.Scanner;

import dao.WaybillDao;
import model.Parcel;
import model.Waybill;

public class WaybillView {

	private static Scanner scan = new Scanner(System.in);

	public static void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}

	public static void waybillInfo(String waybillNum) {

		System.out.println("┌--------------------------------------------------------------------------┐");
		System.out.println("|                                                                          |");
		System.out.printf("| 운송장 번호 : %-30s|| 택배사 : %-23s|", "0000000000", "경동택배");
	}

	public static void wbList() {

		while (true) {
			WaybillDao wbDao = new WaybillDao();
			UserView userV = new UserView();

			Waybill wb;

			System.out.println("조회할 송장의 송장번호를 입력해주세요.");
			System.out.println("송장 번호 : ");
			String wbNum = scan.nextLine();
			wb = wbDao.selectById(wbNum);

			// 운송장이 회원으로 접수 되었을때
			if (wb.getUserId() != null) {
				// 해당 택배를 접수했을때 아이디와 비밀번호를 입력 후 같을 경우 택배 재출력 및 접수 취소를 시킨다.
				System.out.println("해당 기능을 사용하시려면 로그인을 하셔야 합니다.");
				String userId = userV.Login();

				if (wb.getUserId() == userId ) {
					System.out.println("로그인에 성공하셨습니다.");
					System.out.println("1. 재출력  2. 삭제");
					System.out.print("메뉴 선택 : ");
					String menuNum = scan.nextLine();
					
					if("1".equals(menuNum)) {
						waybillInfo(wbNum);
						break;
					} else {
						wbDao.delete(wbNum);
						System.out.println("접수가 취소되었습니다.");
						break;
					}
				} else if (userId != "fail"){
					System.out.println("해당 계정의 송장이 아닙니다.");
					continue;
				} else {
					System.out.println("아이디 패스워드가 틀립니다.");
					continue;
				}

			} else { // 운송장이 비회원으로 접수 되었을때
				// 핸드폰 번호를 입력하고 해당 송장번호와 핸드폰 번호가 같을 경우 채출력 및 접수 취소를 시킨다.
			}
		}

	}

	public static void wbList(String cp) {

	}

	public static void wbList(String Id, String pw) {

	}

	public static void main(String[] args) {

	}
}
