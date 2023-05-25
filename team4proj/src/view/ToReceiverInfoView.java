package view;

import java.util.List;

import dao.ParcelDao;
import dao.SuperDao;
import dao.UserDao;
import dao.WaybillDao;
import model.Useraddress;
import model.Waybill;

public class ToReceiverInfoView implements CommonView {

	private static ToReceiverInfoView view = new ToReceiverInfoView();

	// 받는곳 입력
	public void info(String userId, int parcelNum, int cost) {
		WaybillDao wbDao = new WaybillDao();
		UserDao uDao = new UserDao();
		ParcelDao pDao = new ParcelDao();

		String ReceiverName = "";
		String ReceiverAddr = "";
		String ReceiverCp = "";

		try {
			// 화면 출력
			while (true) {
				
				while (true) {

					System.out.println();
					System.out.println("받는 사람 정보");
					System.out.println("1. 즐겨 찾기에서 선택  2. 직접 입력 ");
					int menuNo = Integer.parseInt(scan.nextLine());
					if (menuNo == 1) {
						List<Useraddress> list = uDao.selectAddrAll(userId);
						System.out.println("번호\t받는 사람 이름\t받는사람 주소\t받는사람 전화번호");
						System.out.println("---------------------------------------------------");
						// 출력
						for (int i = 0; i < list.size(); i++) {
							System.out.println((i + 1) + "\t" + list.get(i).getRcvrName() + "\t"
									+ list.get(i).getRcvrAddr() + "\t" + list.get(i).getRcvrCp());
						}

						System.out.println("몇번 즐겨찾기를 사용하시겠습니까?");
						int num = Integer.parseInt(scan.nextLine());

						System.out.println(
								"이름 : " + list.get(num - 1).getRcvrName() + "\n주소 : " + list.get(num - 1).getRcvrAddr()
										+ "\n전화 번호 : " + list.get(num - 1).getRcvrCp() + "\n 가 맞습니까?");
						System.out.println("1. 해당 즐겨찾기 선택  2. 취소");
						System.out.print("메뉴 선택 : ");
						
						String subMenuNo = scan.nextLine();
						if ("1".equals(subMenuNo)) {

							ReceiverName = list.get(num - 1).getRcvrName();
							ReceiverAddr = list.get(num - 1).getRcvrAddr();
							ReceiverCp = list.get(num - 1).getRcvrCp();
							break;
						} else {
							System.out.println("잘못 입력하셨습니다");
							continue;
						}

					} else if (menuNo == 2) {

						System.out.println();
						System.out.println("받는 사람 정보");
						System.out.print("받는 사람 성함 : ");
						ReceiverName = scan.nextLine();
						System.out.print("받는 사람 주소 : ");
						ReceiverAddr = scan.nextLine();
						System.out.print("받는 사람 전화번호 : ");
						ReceiverCp = scan.nextLine();

						System.out.println("이 받는 사람 정보를 즐겨찾기에 저장하시겠습니까?");
						System.out.println("  1. 저장    2. 저장하지 않고 계속");
						String subMenuNo = scan.nextLine();
						if ("1".equals(subMenuNo)) {
							Useraddress Uaddr = new Useraddress();
							Uaddr.setUserId(userId);
							Uaddr.setRcvrName(ReceiverName);
							Uaddr.setRcvrAddr(ReceiverAddr);
							Uaddr.setRcvrCp(ReceiverCp);
							uDao.createUserAddress(Uaddr);
							break;
						} else {
							break;
						}
					} else {
						System.out.println("잘못 입력하셨습니다");
						continue;
					}
				}
				// 받는 분 정보 입력

				// 우편번호 찾기
				// 집에서 zipcode 불가!!!
				int zipcode = getzipCode(ReceiverAddr);

				// 넘겨 받은 parcelNum 의 왼쪽의 공백을 0으로 채움
				String parcelNumStr = String.format("%05d", parcelNum+1);
				System.out.println(parcelNumStr);

				// 우편번호와 택배 번호를 조합하여 운송장 번호 생성
//			int zipcode = 12323; // 임시 zipcode

				String wbNum = parcelNumStr + zipcode;

				System.out.println(wbNum);

				// 도서 산간지역 요금 추가
				int surcharge = 0;

				if ((63002 <= zipcode && zipcode <= 63364) || (63500 <= zipcode && zipcode <= 63621)) { // 제주도 우편번호
					surcharge = 4000;
				}

				// 무게당 요금과 도서 산간지역을 합쳐 최종 요금 계산
				int totalFee = cost + surcharge;

				// 운송장 기본 정보 입력
				Waybill wayBill = new Waybill();
				wayBill.setWaybillNo(wbNum);
				wayBill.setRcvrName(ReceiverName);
				wayBill.setRcvrAddr(ReceiverAddr);
				wayBill.setRcvrCp(ReceiverCp);
				wayBill.setCompanyCd("01"); // 택배 코드는 나중에 수정필요.
				wayBill.setTotalFee(totalFee);
				wayBill.setUserId(userId);

				// 받는 사람 정보 확인
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

					if (sign != "fail") {
						System.out.println("결제 완료");
						// 결제 완료 시 운송장데이터 생성
						wbDao.create(wayBill);
						WaybillView.getinstance().waybillInfo(wbNum);
					} else {
						System.out.println("결제 취소 되었습니다.");
						continue;
					}

				} else if ("2".equals(menuNo))
					continue;
				else
					return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	public static void main(String[] args) {
//		SuperDao.Load();
//		view.info("kang", 1,1);
//		SuperDao.close();
//	}

	public static ToReceiverInfoView getinstance() {
		return view;
	}
}
