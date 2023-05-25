package view;

import java.util.Scanner;

import dao.WaybillDao;

public interface CommonView {

	Scanner scan = new Scanner(System.in);

	public default void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}

	// 작성자 : 강철구
	// 작성일자 : 2023-05-25
	//설명 : 요금 계산 
	public default int costs(int mass)
	{
		int cost = 0;
		if (mass <= 2) {
			cost = 5500;
		} else if (mass <= 5) {
			cost = 6500;
		} else if (mass <= 10) {
			cost = 7500;
		} else if (mass <= 20) {
			cost = 8500;
		} 
		return cost;
	}	

	// 작성자 : 명지완
	// 작성일자 : 2023-05-25
	//설명 : 주소 값을 받아 분리하는 기능 
	public default int getzipCode(String line) {

		String[] strToStrArray = line.split(" ");
		WaybillDao wdao = new WaybillDao();
		int zipcode = 0;

		// 가평군 등의 '군'의 경우
		if (strToStrArray.length == 4) {
			String[] numTobunum = strToStrArray[3].split("-"); // 지역번호,부번호 나누기
			if (numTobunum.length >= 2)  //부번호가 있을때
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1], strToStrArray[2],
						Integer.parseInt(numTobunum[0]), Integer.parseInt(numTobunum[1]));
			else //부번호가 없을때
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1], strToStrArray[2],
						Integer.parseInt(numTobunum[0]));

		} 
		else { 	// 안산시 등의 '시'의 경우
			String[] numTobunum = strToStrArray[4].split("-"); // 지역번호,부번호 나누기
			if (numTobunum.length >= 2) //부번호가 있을때
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],
						strToStrArray[3], Integer.parseInt(numTobunum[0]), Integer.parseInt(numTobunum[1]));
			else //부번호가 없을때
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],
						strToStrArray[3], Integer.parseInt(numTobunum[0]));
		}

		return zipcode;
	}

	
	
}
