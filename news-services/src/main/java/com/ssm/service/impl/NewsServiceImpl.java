package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.NewsDao;
import com.ssm.po.News;
import com.ssm.service.NewsService;
import com.ssm.utils.PageBean;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;
	
	public PageBean<News> findNewsByPage(String keywords, Integer newsListCategoryId, Integer currentPage,
			Integer pageSize) {
		PageBean<News> pageBean = new PageBean<News>();
		int count = newsDao.getNewsCount(keywords, newsListCategoryId);
		List<News> newslist = newsDao.findNewsList(keywords, newsListCategoryId, (currentPage-1)*pageSize, pageSize);
		int totalPage = (int) Math.ceil( (double)count / pageSize);
		
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(newslist);
		return pageBean;
	}

	public News getNewsByNewsId(Integer newsId) {
		// TODO Auto-generated method stub
		return newsDao.getNewsByNewsId(newsId);
	}

	public int setNewsPublishStatus(News news) {
		// TODO Auto-generated method stub
		return newsDao.setNewsPublishStatus(news);
	}

	public int addNews(News news) {
		// TODO Auto-generated method stub
		return newsDao.addNews(news);
	}

	public int editNews(News news) {
		// TODO Auto-generated method stub
		return newsDao.updateNews(news);
	}

	public int delNews(Integer newsId) {
		return newsDao.delNews(newsId);
	}

}
