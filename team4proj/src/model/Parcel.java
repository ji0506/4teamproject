package model;

import lombok.Data;

@Data
public class Parcel {
	private int parcelNo;
	
	private String parcelName;

	private int parcelWeight;
	
	private String parcelSize;

	private int parcelFee;

	private int waybillNo;

	
	public int feecalculate() {

		return 0;
	}
}
