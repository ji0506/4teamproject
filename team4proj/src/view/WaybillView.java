package view;

import java.text.SimpleDateFormat;
import java.util.Date;

import dao.NonuserDao;
import dao.ParcelDao;
import dao.SuperDao;
import dao.UserDao;
import dao.WaybillDao;
import model.Nonuser;
import model.Parcel;
import model.User;
import model.Waybill;

public class WaybillView implements CommonView  {

	private static WaybillView view = new WaybillView();


	public static void main(String[] args) {
		SuperDao.Load();
		//view.waybillInfo("0000215546");
		SuperDao.close();
	}


	public void waybillInfo(Waybill wb, Parcel parcel) {
			UserDao uDao = new UserDao();
			NonuserDao nuDao = new NonuserDao();

		//	String parcelNumStr = String.valueOf(parcelNum);

			User user = uDao.selectById(wb.getUserId());
			Nonuser Nuser = nuDao.selectById(wb.getNonCp());

			// 들어가야 하는것
			// 받는사람 이름 번호 주소
			String Rname = wb.getRcvrName();
			String Rcp = wb.getRcvrCp();
			String Raddr = wb.getRcvrAddr();
			
			//보내는 사람 이름 번호 주소
			String Sname;
			String Scp;
			String Saddr;

			// 보내는 사람이 회원인가 비회원인가
			if (wb.getNonCp() == null && wb.getUserId() != null) {
				// 회원일시 보내는 사람 정보
				Sname = user.getUserName();
				Scp = user.getUserCp();
				Saddr = user.getUserAddr();

			} else if (wb.getNonCp() != null && wb.getUserId() == null) {
				// 비회원일시 보내는 사람 정보
				Sname = Nuser.getNonuserName();
				Scp = Nuser.getNonuserCp();
				Saddr = Nuser.getNonuserAddr();

			} else {
				// 오류
				System.out.println("오류 입니다. 다시 시도하여 주십시오");
				return;
			}

			// 택배 중량 크기 내용물
			String pSize = parcel.getParcelSize();
			String pName = parcel.getParcelName();
			int pWeight = parcel.getParcelWeight();
			// 택배 접수일(발송일)
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

			Date date = wb.getRegDate();
			if(date == null) {
		        date = new Date();
//		        String nowTime1 = sdf1.format(date);
			}
				
			// 요금 
		
			// 주의사항?

			// 운송장 정보 출력
			System.out.println("┌--------------------------------------------------------------------------┐");
			System.out.printf("| 운송장번호 : %-30s| 택배사 : %-12s\t   |\n", wb.getWaybillNo() , wb.getCompanyName());
			System.out.println("|--------------------------------------------------------------------------|");
			System.out.printf("| 보내는사람 : %-20s  보내는사람 전화번호: %-16s\t   |\n", Sname, Scp);
			System.out.printf("| 보내는사람 주소 : %-50s\t   |\n", Saddr);
			System.out.println("|--------------------------------------------------------------------------|");
			System.out.printf("| 받는사람 : %-20s  받는사람 전화번호: %-20s\t   |\n", Rname, Rcp);
			System.out.printf("| 받는사람 주소 : %-50s\t   |\n", Raddr);
			System.out.println("|--------------------------------------------------------------------------|");
			System.out.printf("| 상품명 : %-50s\t\t   |\n", pName);
			System.out.printf("| 상품 무게 : %-20d  상품 크기: %-25s\t   |\n", pWeight, pSize);
			System.out.println("|--------------------------------------------------------------------------|");
			System.out.printf("|  %-33s| 요금 : %-10s |  %-10s\t   |\n", wb.getMsg(), wb.getTotalFee() ,sdf1.format(date) );
			System.out.println("└--------------------------------------------------------------------------┘");	
	}

	public void wbList() {

		while (true) {

			try {
				WaybillDao wbDao = new WaybillDao();
				ParcelDao pDao = new ParcelDao();
				UserView userV = new UserView();

				System.out.println("조회할 송장의 송장번호를 입력해주세요.");
				System.out.print("송장 번호 : ");
				String wbNum = scan.nextLine();
				Waybill wb = wbDao.selectById(wbNum);
				Parcel pc = pDao.selectWaybillNo(wb.getWaybillNo());

				
				// 운송장이 회원으로 접수 되었을때
				if (wb.getUserId() != null) {
					// 해당 택배를 접수했을때 아이디와 비밀번호를 입력 후 같을 경우 택배 재출력 및 접수 취소를 시킨다.
					System.out.println("해당 기능을 사용하시려면 로그인을 하셔야 합니다.");
					String userId = userV.Login();

					if (userId.equals(wb.getUserId()) ) {
						System.out.println("로그인에 성공하셨습니다.");
						System.out.println("1. 재출력  2. 삭제");
						System.out.print("메뉴 선택 : ");
						String menuNum = scan.nextLine();

						if ("1".equals(menuNum)) {
							waybillInfo(wb,pc);
							break;
						} else {
							wbDao.delete(wbNum);
							System.out.println("접수가 취소되었습니다.");
							break;
						}
					} else if (userId != "fail") {
						System.out.println("해당 계정의 송장이 아닙니다.");
						break;
					} else {
						System.out.println("아이디 패스워드가 틀립니다.");
						break;
					}

				} else { // 운송장이 비회원으로 접수 되었을때
					// 핸드폰 번호를 입력하고 해당 송장번호와 핸드폰 번호가 같을 경우 채출력 및 접수 취소를 시킨다.
					System.out.println("해당 기능을 사용하시려면 해당 택배를 접수할 때 사용하신 전화번호를 입력하셔야 합니다.");

					String inputNonCp = scan.nextLine();
					if (wb.getNonCp() == inputNonCp) {
						System.out.println("비회원 로그인에 성공하셨습니다.");
						System.out.println("1. 재출력  2. 삭제");
						System.out.print("메뉴 선택 : ");
						String menuNum = scan.nextLine();

						if ("1".equals(menuNum)) {
							waybillInfo(wb,pc);
							break;
						} else {
							wbDao.delete(wbNum);
							System.out.println("접수가 취소되었습니다.");
							break;
						}
					} else {
						System.out.println("전화번호가 일치하지 않습니다.");
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();

				System.out.println("다시 시도해 주십시오");
				break;
			}

		}
	}
	
	public static WaybillView getinstance()
	{
		return view;
	}


}
