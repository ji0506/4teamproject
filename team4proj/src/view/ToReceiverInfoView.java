package view;

import java.util.List;
import java.util.Scanner;

import dao.SuperDao;
import dao.UserDao;
import dao.WaybillDao;
import model.Useraddress;
import model.Waybill;

public class ToReceiverInfoView {
	private static Scanner scan = new Scanner(System.in);
	// 받는곳 입력
	public static void receiver(String userId, int parcelNum) {
		Waybill wayBill = new Waybill();
		WaybillDao wbDao = new WaybillDao();
		Useraddress Uaddr = new Useraddress();
		UserDao uDao = new UserDao();
		try {
			// 화면 출력
			System.out.println();
			System.out.println("받는 사람 정보");
			System.out.println("1. 즐겨 찾기에서 선택  2. 직접 입력 ");
			String menuNo = scan.nextLine();
			
			String ReceiverName = "";
			String ReceiverAddr = "";
			String ReceiverCp = "";
			
			if("1".equals(menuNo)) {
				List<Useraddress> list = uDao.selectAddrAll(userId);
				System.out.println("몇번 즐겨찾기를 사용하시겠습니까?");
				int num = scan.nextInt();
				
				System.out.println("이름 : " + list.get(num-1).getRcvrName() + 
						"\n주소 : " + list.get(num-1).getRcvrAddr() +
						"\n전화 번호 : "+list.get(num-1).getRcvrCp() +
						"\n 가 맞습니까?");
				System.out.println("1. 해당 즐겨찾기 선택  2. 취소");
				System.out.print("메뉴 선택 : ");
				String subMenuNo = scan.nextLine();
				if("1".equals(subMenuNo)) {
					
					ReceiverName = list.get(num-1).getRcvrName();
					ReceiverAddr = list.get(num-1).getRcvrAddr();
					ReceiverCp = list.get(num-1).getRcvrCp();
					
				} else {
					receiver(userId, parcelNum);
				}
				
			} else {
				
				System.out.println();
				System.out.println("받는 사람 정보");
				System.out.print("받는 사람 성함 : ");
				ReceiverName = scan.nextLine();
				System.out.print("받는 사람 주소 : ");
				ReceiverAddr = scan.nextLine();
				System.out.print("받는 사람 전화번호 : ");
				ReceiverCp = scan.nextLine();
				
				System.out.println("이 받는 사람 정보를 즐겨찾기에 저장하시겠습니까?");
				String subMenuNo = scan.nextLine();
				if("1".equals(subMenuNo)) {
					
					Uaddr.setRcvrName(ReceiverName);
					Uaddr.setRcvrAddr(ReceiverAddr);
					Uaddr.setRcvrCp(ReceiverCp);
					
					uDao.createUserAddress(Uaddr);
					
				} else {
					receiver(userId, parcelNum);
				}
			}
			// 받는 분 정보 입력 
			
			
			//우편번호 찾기
			//집에서 zipcode 불가!!!

//			int zipcode = getzipCode(ReceiverAddr);
			
			//넘겨 받은 parcelNum 의 왼쪽의 공백을 0으로 채움
			String parcelNumStr = String.format("%05d",parcelNum);                  
			System.out.println(parcelNumStr);
			
			//우편번호와 택배 번호를 조합하여 운송장 번호 생성
			int zipcode = 12323; // 임시 zipcode
			
			String wbNum = parcelNumStr+zipcode;
			
			System.out.println(wbNum);
			
			//운송장 기본 정보 입력
			
			wayBill.setWaybillNo(wbNum);
			wayBill.setRcvrName(ReceiverName);
			wayBill.setRcvrAddr(ReceiverAddr);
			wayBill.setRcvrCp(ReceiverCp);
			wayBill.setCompanyCd("01"); // 택배 코드는 나중에 수정필요
			wayBill.setUserId(userId);
			
			//운송장 생성
			wbDao.create(wayBill);

			
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
		receiver("kang", 1);
		SuperDao.close();
	}

}
