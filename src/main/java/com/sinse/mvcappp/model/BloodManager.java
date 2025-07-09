package com.sinse.mvcappp.model;


/**
 * MVC 패턴에 의해 디자인 영역과 로직 및 데이터 영역을 분리시켜야 유지보수성이 높아지기 때문에 분
 * */
public class BloodManager {

	public String getAdvice(String blood) {
		
		// ==========Model======================
		String msg=""; // NullPointException 방지용
		
		if(blood.equals("A")){
			msg="신중하고 꼼꼼함";
		} else if(blood.equals("B")){
			msg="자유롭고 개성강함";
		} else if(blood.equals("O")){
			msg="지맘대로";	
		} else if(blood.equals("AB")){
			msg="또라이";
		}
		// =====================================
		
		return msg;
	}
}
