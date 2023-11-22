package com.gg.jobPosting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // .xlsx 파일용
import org.apache.poi.hssf.usermodel.HSSFWorkbook; // .xls 파일용

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@CrossOrigin
@RestController
public class PostController {
	@Autowired
	PostService service;

	@GetMapping("/job-posting")
	public List<PostDTO> SelectAllFromPost() {

		System.out.println("-----------------------------");
		System.out.println("    Post Controller..");
		System.out.println("-----------------------------");

		// List<PostDTO> a = service.postList();
		// System.out.println("company :" + a.get(0).COMPANY);
		return service.postList();
	}

	@GetMapping("/job-com-posting")
	public List<PostDTO> SelectFromComPost(String co_ID) {
		System.out.println("-----------------------------");
		System.out.println("    Post Controller..");
		System.out.println(co_ID);
		System.out.println("-----------------------------");
		List<PostDTO> a = service.compostList(co_ID);
		System.out.println("company :" + a.get(0).CO_ID);
		return service.compostList(co_ID);
	}
}
