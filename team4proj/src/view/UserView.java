package view;

import dao.NonuserDao;
import dao.UserDao;
import model.Nonuser;
import model.User;

public class UserView implements CommonView{

	private UserDao udao = new UserDao();
	private NonuserDao nudao = new NonuserDao();

	public String Login() {
		try {
			// 로그인창 출력
			System.out.println("[로그인]");
			System.out.print("ID\t : ");
			String userid = scan.nextLine();
			System.out.print("Password : ");
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
			System.out.print("이름 : ");
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
			
			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println();
			System.out.println("                   ○  회  원  가  입  ○");
			System.out.println();
			System.out.println("--------------------------------------------------------");
			String userid;
			while (true) {
				System.out.println("ID를 입력해 주십시오 (15글자 제한)  ");
				System.out.print("I      D  : ");
				userid = scan.nextLine();
				User vo = udao.selectById(userid);
				
				if(vo == null) {
					break;
				} else {
					System.out.println("-----------------------");
					System.out.println("중복된 아이디가 존재합니다.");
					System.out.println("다시 시도하여 주십시오.");
					System.out.println("-----------------------");
					continue;
				}
			}
			System.out.print("Password  : ");
			String userpw = scan.nextLine();
			System.out.print("성     함  : ");
			String username = scan.nextLine();
			System.out.print("전 화 번 호 : ");
			String usercp = scan.nextLine();
			System.out.print("주      소 : ");
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

}
