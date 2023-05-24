package model;

import lombok.Data;

@Data
public class Useraddress {

	private String userId;
	
	private String rcvrName;

	private String rcvrAddr;

	private String rcvrCp;

	private int freq;
}
