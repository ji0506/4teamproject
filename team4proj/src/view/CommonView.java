package view;

import java.util.Scanner;

public interface View {

	Scanner scan = new Scanner(System.in);

	public default void exit() {
		System.out.println("** 프로그램 종료 **");
		System.exit(0);
	}

	
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
		} else {
			System.out.println("20kg 초과의 택배는 보낼수 없습니다.");
		}
		return cost;
	}	
	
}
