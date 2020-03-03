package com.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.po.Category;
import com.ssm.po.Role;
import com.ssm.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	//信息类别管理
	@RequestMapping("findCategoryList.action")
	public String findCategoryList(Model model){
		List<Category> categoryList = categoryService.findCategoryList();
		model.addAttribute("categoryList", categoryList);
		return "category/category_list";
	}
	
	//toEditCategory
	@RequestMapping(value="toEditCategory.action",method=RequestMethod.GET)
	public String toEditCategory(Integer categoryId,Model model){
		Category category = categoryService.findCategoryById(categoryId);
		model.addAttribute("category", category);
		return "category/edit_category";
	}
	
	//editCategory.action
	@PostMapping("editCategory.action")
	public String editCategory(Category category,Model model){
		int flag = categoryService.updateCategory(category);
		if(flag >=0){
			return "redirect:findCategoryList.action";
		}else{
//			System.out.println("bb");
			model.addAttribute("category", category);
			model.addAttribute("checkMsg", "修改失败，请稍后再试");
			return "category/edit_category";
		}
	}
	
	@PostMapping("delCategory.action")
	@ResponseBody
	public Category delCategory(@RequestBody Category category,Model model){
		
		int flag = categoryService.deleteCategory(category.getCategoryId());
		if(flag>0){
			category.setCategoryId(-1);
			return category;
		}else{
			return category;
		}
		
	}
	
	//toAddCategory.action
	@RequestMapping("toAddCategory.action")
	public String toAddCategory(){
		
		return "category/add_category";
	}
	
	//checkCategoryName.action
	@PostMapping("checkCategoryName.action")
	@ResponseBody
	public Category checkCategoryName(@RequestBody Category category,Model model){
		List<Category> list = categoryService.findCategoryList();
		for (Category c : list) {
			if(c.getCategoryName().equals(category.getCategoryName())){
				return null;
			}
		}
		return category;
	}
	
	//addCategory.action
	@RequestMapping("addCategory.action")
	public String addCategory(Model model,Category category){
		int flag = categoryService.addCategory(category);
		if(flag>0){
			return  "redirect:findCategoryList.action";
		}else{
			model.addAttribute("checkMsg", "请稍后再试");
			return "category/add_category";
		}
		
	}
}
