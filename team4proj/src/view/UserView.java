package view;

import java.util.List;
import java.util.Scanner;

import dao.UserDao;
import model.Nonuser;
import model.User;
import model.Nonuser;

public class UserView {

	private Scanner scan = new Scanner(System.in);
	private UserDao udao = new UserDao();;

//	public void list() {
//		System.out.println();
//		System.out.println("[유저 목록]");
//		System.out.println("---------------------------------------------------------------");
//		System.out.printf("%-6s%-6s%-6s\n", "userId", "email", "name");
//		System.out.println("---------------------------------------------------------------");
//
//		try {
//
//			List<User> list = udao.selectAll();
//
//			for (User u : list) {
//				System.out.printf("%-6s%-12s%-16s\n", u.getUserId(), u.getUserEmail(), u.getUserName());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			exit();
//		}
//
//		mainMenu();
//	}
//
//	public void list2() {
//		System.out.println();
//		System.out.println("[유저 목록]");
//		System.out.println("---------------------------------------------------------------");
//		System.out.printf("%-6s%-6s%-6s\n", "userId", "email", "name");
//		System.out.println("---------------------------------------------------------------");
//
//		try {
//
//			List<User> list = udao.selectAll();
//
//			for (User u : list) {
//				System.out.printf("%-6s%-12s%-16s\n", u.getUserId(), u.getUserEmail(), u.getUserName());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			exit();
//		}
//
//		mainMenu();
//	}
//
//	public void mainMenu() {
//		System.out.println();
//		System.out.println("----------------------------------------");
//		System.out.println("메인 메뉴: 1.유저생성 | 2.개인정보확인 | 3.Exit");
//		System.out.print("메뉴 선택: ");
//		String menuNo = scan.nextLine();
//
//		switch (menuNo) {
//		case "1":
//			create();
//			break;
//		case "2":
//			read();
//			break;
//		case "3":
//			exit();
//			break;
//		}
//	}
//
//	public boolean create() {
//		User use = new User();
//		System.out.println("[회원가입]");
//		System.out.print("아이디:");
//		use.setUserId(scan.nextLine());
//		System.out.print("pwd:");
//		use.setUserPwd(scan.nextLine());
//		System.out.print("이메일:");
//		use.setUserEmail(scan.nextLine());
//		System.out.print("이름:");
//		use.setUserName(scan.nextLine());
//
//		System.out.println("----------------------------------------");
//		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
//		System.out.print("메뉴 선택: ");
//		String menuNo = scan.nextLine();
//
//		if ("1".equals(menuNo)) {
//			try {
//				udao.create(use);
//				return true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				exit();
//			}
//		}
//
//		return false;
//	}

//	public void read() {
//		System.out.println("[개인정보 확인]");
//		System.out.print("bno:");
//		String userId = scan.nextLine();
//
//		try {
//			User use = udao.selectById(userId);
//
//			System.out.println("###########");
//			System.out.println("id : " + use.getUserId());
//			System.out.println("pwd : " + use.getUserPwd());
//			System.out.println("이메일 : " + use.getUserEmail());
//			System.out.println("이름 : " + use.getUserName());
//
//			System.out.println("----------------------------------------");
//			System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.list");
//			System.out.print("메뉴 선택: ");
//			String menuNo = scan.nextLine();
//
//			if ("1".equals(menuNo)) {
//				update(use);
//			} else if ("2".equals(menuNo)) {
//				delete(use);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			exit();
//		}
//
//		list();
//	}

	public String login() {
		System.out.println("[로그인]");
		System.out.print("id:");
		String userId = scan.nextLine();
		System.out.print("pwd:");
		String userpwd = scan.nextLine();

		User use = udao.selectById(userId);

		if (userpwd.equals(use.getUserPwd()))
			return userId;

		return "";
	}

	public String Non_userlogin() {
		try {
			Nonuser nuser = new Nonuser();
			
			System.out.println("[비회원 로그인]");
			System.out.print("성함 :");
			String userId = scan.nextLine();
			System.out.print("전화번호 :");
			String useremail = scan.nextLine();
			System.out.print("주소 :");
			String useraddr = scan.nextLine();
			
			nuser.setNonuserName(userId);
			nuser.setNonuserCp(useremail);
			nuser.setNonuserAddr(useraddr);

		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
		
		return "";
	}

	private void update(User use) {
		try {
			System.out.println("[개인정보 변경]");
			System.out.print("pwd:");
			use.setUserPwd(scan.nextLine());
			System.out.print("이름:");
			use.setUserName(scan.nextLine());
			System.out.print("이메일:");
			use.setUserEmail(scan.nextLine());

			System.out.println("----------------------------------------");
			System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
			System.out.print("메뉴 선택: ");
			String menuNo = scan.nextLine();

			if ("1".equals(menuNo)) {
				udao.update(use);
			}
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
	}

	private void delete(User use) {
		try {
			udao.delete(use);
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
	}

	public void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);

	}

	public static void main(String[] args) {
		try {
			UserView exam = new UserView();
			exam.login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
