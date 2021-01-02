package com.leyou.item.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {
	 
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("list")
	@ResponseBody
	public ResponseEntity<List<Category>> queryCategoryListByParentId(
			@RequestParam(value = "pid", defaultValue = "0") Long pid) {
		try {
			if(pid == null || pid.longValue() < 0) {
				return ResponseEntity.badRequest().build();
			}
			List<Category> categoryList = categoryService.queryCategoryListByParentId(pid);
			if(CollectionUtils.isEmpty(categoryList)) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(categoryList);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
