package com.ssm.service;
import java.util.List;
import com.ssm.po.Category;
/*
 * 新闻类别Service层接口
 */
public interface CategoryService {
	/**
	 * 查询所有新闻总类
	 * @return
	 */
	public List<Category> findCategoryList();
	/**
	 * 根据ID查询新闻分类
	 * @param categoryId
	 * @return
	 */
	public Category findCategoryById(Integer categoryId);
	
	/**
	 * 更新新闻分类
	 */
	public int updateCategory(Category category);
	
	/**
	 * 删除分类
	 */
	public int deleteCategory(Integer categoryId);
	/**
	 * 新增分类
	 */
	public int addCategory(Category category);
}