package com.leyou.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
     * 根据parentId查询子类目
     * @param pid
     * @return
     */
    public List<Category> queryCategoryListByParentId(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }

}
