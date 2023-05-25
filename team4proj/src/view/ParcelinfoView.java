package view;

import dao.ParcelDao;
import dao.SuperDao;
import model.Parcel;

public class ParcelinfoView implements View{

	private static ParcelinfoView view = new ParcelinfoView();

	
	public void info(String userId) {

		Parcel parcel = new Parcel();
		ParcelDao pdao = new ParcelDao();

		try {
			while (true) {
				int cost;
				int mass;

				// 화면 출력
				System.out.println();
				System.out.println("물품의 내용을 입력해주세요");
				System.out.print("물품 내용 : ");
				String parcelName = scan.nextLine();

				System.out.println("[무게 및 크기 측정]");
				System.out.println("---------------------------------------------------------------");
				System.out.println("무게와 크기를 입력해 주세요");
				System.out.println("---------------------------------------------------------------");

				// 무게당 요금 계산
				while (true) {
					System.out.print("무게(kg) :");
					mass = scan.nextInt();
					
					if(mass > 20) {
						System.out.println("20kg 초과의 택배는 보낼수 없습니다.");
						continue;
					}
					break;
				}
				
				cost = costs(mass);
				
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

				// 데이터 베이스에 입력

				pdao.create(parcel);

				int parcelNum = pdao.selectCountId();

				System.out.println(parcelNum);
				// 마지막 확인 화면 출력
				System.out.println();
				System.out.println("--------------------------------------------------------");
				System.out.println();
				System.out.println("                   ○ 운송물 정보 확인 ○");
				System.out.println("--------------------------------------------------------");
				System.out.printf("    | 내용 : %s || 크기 : %s || 무게 : %d |\n", parcelName, volume, mass);
				System.out.println("--------------------------------------------------------");

				ToReceiverInfoView.getinstance().info(userId, parcelNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		SuperDao.Load();
		view.info(null);
		SuperDao.close();
	}

	public static ParcelinfoView getinstance()
	{
		return view;
	}
}
