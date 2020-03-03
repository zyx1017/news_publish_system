package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.CategoryDao;
import com.ssm.po.Category;
import com.ssm.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> findCategoryList() {
		return categoryDao.selectCategoryList();
	}

	public Category findCategoryById(Integer categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	@Override
	public int updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	@Override
	public int deleteCategory(Integer categoryId) {
		categoryDao.updateCategorytoNews(categoryId);
		return categoryDao.delCategory(categoryId);
		
	}

	@Override
	public int addCategory(Category category) {
		
		return categoryDao.addCategory(category);
	}

}
