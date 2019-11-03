package com.example.demo.service;

import com.example.demo.bean.PageMakerVO;

public class PageMakerTest {

	public static void main(String[] args) {

		PageMakerVO maker = new PageMakerVO();
		maker.setStartPage(2);
		maker.setTotalcount(10);
		maker.setEndPage(3, 3);
		
		System.out.println(maker.getStartPage());
		System.out.println(maker.getEndPage());
	}

}
