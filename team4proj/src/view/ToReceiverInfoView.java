package view;

import java.util.Scanner;

import dao.SuperDao;
import dao.WaybillDao;
import model.Waybill;

public class ToReceiverInfoView {
	private static Scanner scan = new Scanner(System.in);
	// 받는곳 입력
	public static void receiver(String userId, int parcelNum) {
		Waybill wayBill = new Waybill();
		WaybillDao wbDao = new WaybillDao();
		
		try {
			// 화면 출력
			// 받는 분 정보 입력 
			System.out.println();
			System.out.println("받는분 정보");
			System.out.print("받는분 성함 : ");
			String ReceiverName = scan.nextLine();
			System.out.print("받는분 주소 : ");
			String ReceiverAddr = scan.nextLine();
			System.out.print("받는분 전화번호 : ");
			String ReceiverCp = scan.nextLine();
			
			//우편번호 찾기
			
			int zipcode = getzipCode(ReceiverAddr);
			
			//우편번호와 택배 번호를 조합하여 운송장 번호 생성
			
			String waybillNum = String.format("%05s+%d",parcelNum,zipcode);
			wayBill.setWaybillNo(waybillNum);
			System.out.println(wayBill.getWaybillNo());
			// 운송장 기본 정보 입력
//			
//			wayBill.setRcvrName(ReceiverName);
//			wayBill.setRcvrAddr(ReceiverAddr);
//			wayBill.setRcvrCp(ReceiverCp);
//			wayBill.setCompanyCd("RJ");
//			wayBill.setUserId(userId);
//			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
	public static int getzipCode(String line) {

		String[] strToStrArray = line.split(" ");
		WaybillDao wdao = new WaybillDao();
		int zipcode = 0;
		
		if(strToStrArray.length == 4)
		{
			String [] numTobunum = strToStrArray[3].split("-");
			if(numTobunum.length >= 2) 
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1],strToStrArray[2], Integer.parseInt(numTobunum[0]),Integer.parseInt(numTobunum[1]));
			else 
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1],strToStrArray[2], Integer.parseInt(numTobunum[0]));

			
		} else {
			
			String [] numTobunum = strToStrArray[4].split("-");
			if(numTobunum.length >= 2) 
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],strToStrArray[3], Integer.parseInt(numTobunum[0]),Integer.parseInt(numTobunum[1]));				
			else
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],strToStrArray[3], Integer.parseInt(numTobunum[0]));				
		}
		
		return zipcode;
	}
	
	public static void main(String[] args) {
		SuperDao.Load();
		receiver(null, 0);
		SuperDao.close();
	}

}
