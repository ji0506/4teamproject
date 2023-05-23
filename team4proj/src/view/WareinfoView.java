package view;

import java.util.Scanner;

import dao.ParcelDao;
import model.Parcel;

public class WareinfoView {
	static int parcelNum = 0;
	private static Scanner scan = new Scanner(System.in);

	public static void ParcelInfo() {
		Parcel parcel = new Parcel();

		try {
			parcelNum++;
			int cost;
			int mass;
			
			System.out.println();
			System.out.println("물품의 내용을 입력해주세요");
			System.out.print("물품 내용 : ");
			String parcelName = scan.nextLine();
			

			System.out.println("[무게 및 크기 측정]");
			System.out.println("---------------------------------------------------------------");
			System.out.println("무게와 크기를 입력해 주세요");
			System.out.println("---------------------------------------------------------------");

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
			
			String volume = String.format("%d*%d*%d(cm)", width, length, height);
			
			
			parcel.setParcelNo(parcelNum);
			parcel.setParcelName(parcelName);
			parcel.setParcelFee(cost);
			parcel.setParcelWeight(mass);
			parcel.setParcelSize(volume);
			
			ParcelDao pdao = new ParcelDao();
			pdao.create(parcel);
			
			System.out.println("운송물 정보 확인");
			System.out.println("--------------------------------------------------------");
			System.out.printf("| 내용 : %s || 크기 : %s || 무게 : %d |\n",parcelName,volume,mass);
			System.out.println("--------------------------------------------------------");

		} catch (Exception e) {
			parcelNum--;
			e.printStackTrace();
		}

	}

	public static void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);

	}

	public static void main(String[] args) {
		ParcelInfo();

	}

}
