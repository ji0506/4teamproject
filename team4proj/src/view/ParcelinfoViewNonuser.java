package view;

import java.util.Scanner;

import dao.ParcelDao;
import dao.SuperDao;
import model.Parcel;

public class ParcelinfoViewNonuser {
	
	private static Scanner scan = new Scanner(System.in);

<<<<<<< Updated upstream:team4proj/src/view/ParcelinfoViewNonuser.java
	public static void ParcelInfo(String nonUserCp) {

=======
	public static void ParcelInfo(String nonCp) {
		
>>>>>>> Stashed changes:team4proj/src/view/ParcelinfoView_Nonuser.java
		Parcel parcel = new Parcel();
		ParcelDao pdao = new ParcelDao();

		try {

			int cost;
			int mass;

			// 화면 출력
			System.out.println();
			System.out.println("상품명을 입력해주세요");
			System.out.print("상품명 : ");
			String parcelName = scan.nextLine();

			System.out.println("		[무게 및 크기 측정]");
			System.out.println("---------------------------------------------------------------");
			System.out.println("무게와 크기를 입력해 주세요");
			System.out.println("---------------------------------------------------------------");

			// 무게당 요금 계산
			while (true) {
				System.out.print("무게(kg) :");
				mass = scan.nextInt();
				if (mass <= 2) {
					cost = 5500;
					break;
				} else if (mass <= 5) {
					cost = 6500;
					break;
				} else if (mass <= 10) {
					cost = 7500;
					break;
				} else if (mass <= 20) {
					cost = 8500;
					break;
				} else {
					System.out.println("20kg 초과의 택배는 보낼수 없습니다.");
					continue;
				}
			}

			// 택배 규격 확인
			int width;
			int length;
			int height;

			while (true) {
				System.out.print("가로(cm) :");
				width = scan.nextInt();
				System.out.print("세로(cm) :");
				length = scan.nextInt();
				System.out.print("높이(cm) :");
				height = scan.nextInt();

				if (width + length + height > 160 || width > 100 || length > 100 || height > 100) {
					System.out.println("가능한 택배 규격이 아닙니다.");
					continue;
				}
				break;
			}

			// 택배 크기 정의
			String volume = String.format("%d*%d*%d(cm)", width, length, height);

			// 입력값들 set
			parcel.setParcelName(parcelName);
			parcel.setParcelFee(cost);
			parcel.setParcelWeight(mass);
			parcel.setParcelSize(volume);

			//택배 번호 설정
			int parcelNum = pdao.selectCountId();
			scan.nextLine();// 입력 버퍼를 초기화 하는 역할
			
			// 마지막 확인 화면 출력
			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println();
			System.out.println("                   ○ 운송물 정보 확인 ○");
			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println();
			System.out.printf("    | 내용 : %s || 크기 : %s || 무게 : %d |\n", parcelName, volume, mass);
			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println();
			System.out.println("1. 받는 사람 정보 화면으로 2. 택배 정보 다시 입력 3. 메인 메뉴로");
		
			System.out.print("메뉴 선택: ");
			String menuNo = scan.nextLine();
			
			if ("1".equals(menuNo)) {

				pdao.create(parcel);
				ToReceiverInfoViewNonuser.receiverInfo(nonUserCp, parcelNum);

			} else if ("2".equals(menuNo)) {

				ParcelInfo(nonUserCp);

			} else {

				MainView.main(null);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}

	public static void main(String[] args) {
		SuperDao.Load();
		ParcelInfo(null);
		SuperDao.close();
	}

}