package com.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.po.Category;
import com.ssm.po.News;
import com.ssm.service.CategoryService;
import com.ssm.service.NewsService;
import com.ssm.utils.PageBean;

@Controller
public class NewsController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private CategoryService categoryService;
	
	//查询新闻分页显示
	@RequestMapping("findNewsByPage.action")
	public String findNewsByPage(String keywords,Integer newsListCategoryId,@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize,Model model){
		PageBean<News> pb = newsService.findNewsByPage(keywords, newsListCategoryId, currentPage, pageSize);
		model.addAttribute("pb", pb);
		model.addAttribute("keywords", keywords);
		model.addAttribute("newsListCategoryId", newsListCategoryId);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageSize", pageSize);
		return "news/news_list";
	}
	
	//设置新闻发布状态   //1-发布  2-撤稿
	@PostMapping("setNewsPublishStatus.action")
	@ResponseBody
	public News setNewsPublicStatus(@RequestBody News news,Model model){
		int flag = newsService.setNewsPublishStatus(news);
		if(flag>0){
			return news;
		}else{
			news.setNewsId(0);
			return news;
		}
	}
	
	//转向添加新闻
	@RequestMapping("toAddNews.action")
	public String toAddNews(Model model){
		List<Category> categoryList = categoryService.findCategoryList();
		model.addAttribute("categoryList", categoryList);
		return "news/add_news";
	}
	
	//添加新闻
	@PostMapping("addNews.action")
	public String addNews(News news,Model model){
		java.util.Date date = new java.util.Date();
		news.setPublishTime(date);
		news.setPublishStatus("1");
		int flag = newsService.addNews(news);
		if(flag >0){
			return "redirect:findNewsByPage.action";
		}else{
			List<Category> categoryList = categoryService.findCategoryList();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("news", news);
			return "/news/add_news";
		}
		
	}
	
	//转向修改页面
	@RequestMapping("toEditNews.action")
	public String toEditNews(Integer newsId,Model model){
		News news = newsService.getNewsByNewsId(newsId);
		if(news !=null){
			model.addAttribute("news",news);
			List<Category> categoryList = categoryService.findCategoryList();
			model.addAttribute("categoryList", categoryList);
		}
		return "news/edit_news";
	}
	
	//修改新闻
	@PostMapping("editNews.action")
	public String editNews(News news,Model model){
		java.util.Date date = new java.util.Date();
		news.setPublishTime(date);
		news.setPublishStatus("1");
		int flag = newsService.editNews(news);
		if(flag >0){
			return "redirect:findNewsByPage.action";
		}else{
			model.addAttribute("news", news);
			List<Category> categoryList = categoryService.findCategoryList();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("msg", "修改失败，请稍后再试");
			return "news/edit_news";
		}
	}
	
	//删除新闻
	@RequestMapping("delNews.action")
	@ResponseBody
	public News delNews(@RequestBody News news,Model model){
		int flag = newsService.delNews(news.getNewsId());
		System.out.println("aaa");
		if(flag >0){
			return news;
		}else{
			news.setNewsId(0);
			return news;
		}
	}
	
	//根据新闻类别ID查询新闻分页  前台页面
	@RequestMapping("/index.action")
	public String index(HttpServletRequest request,HttpServletResponse response,String keywords,Integer newsListCategoryId,
			@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize,Model model){
		
		PageBean<News> pb1 = newsService.findNewsByPage(keywords, 1, currentPage, pageSize);
		model.addAttribute("pb1", pb1);
		PageBean<News> pb2 = newsService.findNewsByPage(keywords, 2, currentPage, pageSize);
		model.addAttribute("pb2", pb2);
		return "../../first";
	}
	
	//根据新闻分类ID查询新闻分页
	@RequestMapping("findNewsByCategoryIdPage.action")
	public String findNewsByCategoryIdPage(HttpServletRequest request,HttpServletResponse response,String keywords,Integer newsListCategoryId,
			@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize,Model model){
		Category category = categoryService.findCategoryById(newsListCategoryId);
		model.addAttribute("category", category);
		PageBean<News> pb = newsService.findNewsByPage(keywords, newsListCategoryId, currentPage, pageSize);
		model.addAttribute("pb", pb);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("newsListCategoryId", newsListCategoryId);
		model.addAttribute("pageSize",pageSize);
		
		return "../../list";
	}
	
	//查询新闻（用于前台新闻内容页）
		@RequestMapping(value = "/findFrontNewsByNewsId.action")
		public String findFrontNewsByNewsId(Integer newsId,Model model) {
			News news = newsService.getNewsByNewsId(newsId);
			if (news != null) {
				model.addAttribute("news", news);
			}
			return "../../detail";
		}
		
		
}
	

