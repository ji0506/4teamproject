package model;

import java.util.Date;
import java.util.Scanner;

import lombok.Data;

@Data
public class Waybill {
	private int waybillNo;

	private String rcvrName;

	private String rcvrAddr;

	private String rcvrCp;

	private String companyCd;

	private String userId;

	private String nonCp;
	
	private Date regDate;

}
