package com.ssm.dao;

import java.util.List;

import com.ssm.po.Category;

public interface CategoryDao {
	/**
	 * 查询所有新闻类别
	 * @return
	 */
	public List<Category> selectCategoryList();
	
	/**
	 * 根据新闻类别ID查询新闻类别
	 * @param categoryId
	 * @return
	 */
	public Category getCategoryById(Integer categoryId);
	
	/**
	 * 修改新闻分类
	 */
	public int updateCategory(Category category);
	
	/**
	 * 删除新闻分类
	 * 删除该分类，并把该分类下所有新闻归结到无分类中
	 */
	public int updateCategorytoNews(Integer categoryId);
	public int delCategory(Integer categoryId);
	/**
	 * 增加分类
	 */
	public int addCategory(Category category);
}
