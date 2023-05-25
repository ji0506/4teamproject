package model;

import java.util.Date;

import lombok.Data;

@Data
public class Waybill {
	private String waybillNo;

	private String rcvrName;

	private String rcvrAddr;

	private String rcvrCp;

	private String companyCd;

	private String companyName;
	
	private String userId;

	private String nonCp;
	
	private int totalFee;
	
	private Date regDate;

}
