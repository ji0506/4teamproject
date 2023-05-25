package view;

import java.util.Scanner;

import dao.NonuserDao;
import dao.UserDao;
import model.Nonuser;
import model.User;

public class UserView {

	private Scanner scan = new Scanner(System.in);
	private UserDao udao = new UserDao();
	private NonuserDao nudao = new NonuserDao();

	public String Login() {
		try {
			// 로그인창 출력
			System.out.println("[로그인]");
			System.out.print("id:");
			String userid = scan.nextLine();
			System.out.print("pwd:");
			String userpwd = scan.nextLine();

			User user = udao.selectById(userid);

			if (userpwd.equals(user.getUserPwd()))
				return userid;

		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "fail";
	}

	public String Non_userlogin() {
		try {
			Nonuser nuser = new Nonuser();

			System.out.println("[비회원 로그인]");
			System.out.print("성함 :");
			String username = scan.nextLine();
			System.out.print("전화번호 :");
			String usercp = scan.nextLine();
			System.out.print("주소 :");
			String useraddr = scan.nextLine();

			nuser.setNonuserName(username);
			nuser.setNonuserCp(usercp);
			nuser.setNonuserAddr(useraddr);

			if(nudao.create(nuser) == true)	
				return "success";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	public String JoinUser() {
		try {
			System.out.println("[회원 가입]");
			System.out.println("----------------------");
			System.out.print("ID :");
			String userid = scan.nextLine();
			System.out.print("패스워드 :");
			String userpw = scan.nextLine();
			System.out.print("성함 :");
			String username = scan.nextLine();
			System.out.print("전화번호 :");
			String usercp = scan.nextLine();
			System.out.print("주소 :");
			String useraddr = scan.nextLine();

			User user = new User();

			user.setUserId(userid);
			user.setUserPwd(userpw);
			user.setUserName(username);
			user.setUserCp(usercp);
			user.setUserAddr(useraddr);

			if(udao.create(user) ==true)	
				return "success";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	public void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}

}
