package view;

import java.util.Scanner;

import dao.ParcelDao;
import dao.SuperDao;
import dao.WaybillDao;
import model.Waybill;

public class ToReceiverInfoViewNonuser {
	private static Scanner scan = new Scanner(System.in);

	// 받는곳 입력
	public static void receiverInfo(String nonUserCp, int parcelNum) {
		Waybill wayBill = new Waybill();
		WaybillDao wbDao = new WaybillDao();
		ParcelDao pDao = new ParcelDao();
		
		try {
			// 화면 출력
			// 받는 분 정보 입력
			System.out.println();
			System.out.println("받는 사람 정보");
			System.out.print("받는 사람 이름 : ");
			String ReceiverName = scan.nextLine();
			System.out.print("받는 사람 주소 : ");
			String ReceiverAddr = scan.nextLine();
			System.out.print("받는 사람 전화번호 : ");
			String ReceiverCp = scan.nextLine();

			// 우편번호 찾기
			// 집에서 zipcode() 불가!!!

//			int zipcode = getzipCode(ReceiverAddr);

			// 넘겨 받은 parcelNum 의 왼쪽의 공백을 0으로 채움
			String parcelNumStr = String.format("%05d", parcelNum);
			System.out.println(parcelNumStr);

			int zipcode = 63500; // 임시 zipcode

			// 도서 산간지역 요금 추가
			int surcharge = 0;

			if ((63002 <= zipcode && zipcode <= 63364) || (63500 <= zipcode && zipcode <= 63621)) { // 제주도 우편번호
				surcharge = 4000;
			}
			
			// 무게당 요금과 도서 산간지역을 합쳐 최종 요금 계산
			int totalFee = pDao.selectParcelFee(parcelNum) + surcharge;
			
			System.out.println(totalFee);

			// 우편번호와 택배 번호를 조합하여 운송장 번호 생성
			String wbNum = parcelNumStr + zipcode;

			System.out.println(wbNum);

			// 운송장 기본 정보 입력

			wayBill.setWaybillNo(wbNum);
			wayBill.setRcvrName(ReceiverName);
			wayBill.setRcvrAddr(ReceiverAddr);
			wayBill.setRcvrCp(ReceiverCp);
			wayBill.setCompanyCd("01"); // 택배 코드는 나중에 수정필요
			wayBill.setNonCp(nonUserCp);

			// 운송장 생성
//			wbDao.create(wayBill);

			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println();
			System.out.println("                   ○ 받는 사람 정보 확인 ○");
			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println();
			System.out.printf("    | 이름 : %s || 전화번호 : %s |\n", ReceiverName, ReceiverCp);
			System.out.println();
			System.out.printf("    | 주소 : %s |\n", ReceiverAddr);
			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println();
			System.out.println("1. 결제 화면으로  2. 받는 사람 정보 다시 입력  3. 메인 메뉴로");

			System.out.print("메뉴 선택: ");
			String menuNo = scan.nextLine();

			if ("1".equals(menuNo)) {
				
				String sign = payView(totalFee);
				
				if (sign != "fail"){
					System.out.println("결제 완료");
					//결제 완료 시 운송장데이터 생성
					wbDao.create(wayBill); 
					WaybillView.waybillInfo(wbNum);
				} else {
					System.out.println("결제 취소 되었습니다.");
					
				}

			} else if ("2".equals(menuNo)) {

				receiverInfo(nonUserCp, parcelNum);

			} else {

				MainView.main(null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String payView(int totalFee) {

		try {
			System.out.println("결제 화면 입니다. ");

			System.out.println("결제 요금은 " + totalFee + "입니다.");
			System.out.println("1. 결제   2. 취소");
			System.out.println();
			
			String menuNo = scan.nextLine();
			
			if ("1".equals(menuNo)) {
				return "success";
			} else {
				return "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	public static int getzipCode(String line) {

		String[] strToStrArray = line.split(" ");
		WaybillDao wdao = new WaybillDao();
		int zipcode = 0;

		if (strToStrArray.length == 4) {
			String[] numTobunum = strToStrArray[3].split("-");
			if (numTobunum.length >= 2)
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1], strToStrArray[2],
						Integer.parseInt(numTobunum[0]), Integer.parseInt(numTobunum[1]));
			else
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1], strToStrArray[2],
						Integer.parseInt(numTobunum[0]));

		} else {

			String[] numTobunum = strToStrArray[4].split("-");
			if (numTobunum.length >= 2)
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],
						strToStrArray[3], Integer.parseInt(numTobunum[0]), Integer.parseInt(numTobunum[1]));
			else
				zipcode = wdao.selectzipcode(strToStrArray[0], strToStrArray[1] + " " + strToStrArray[2],
						strToStrArray[3], Integer.parseInt(numTobunum[0]));
		}

		return zipcode;
	}

	public static void main(String[] args) {
		SuperDao.Load();
		receiverInfo(null, 1);
		SuperDao.close();
	}

}