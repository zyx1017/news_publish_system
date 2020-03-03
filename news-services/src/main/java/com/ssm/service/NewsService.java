package com.ssm.service;
import com.ssm.po.News;
import com.ssm.utils.PageBean;
/*
 * 新闻Service层接口
 */
public interface NewsService {
	/**
	 * 返回新闻分页数据
	 * @param keywords
	 * @param newsListCategoryId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean<News> findNewsByPage(String keywords, Integer newsListCategoryId, Integer currentPage, Integer pageSize);
	/**
	 * 根据id查询新闻
	 * @param newsId
	 * @return
	 */
	News getNewsByNewsId(Integer newsId);
	/**
	 * 设置新闻发布状态
	 * @param news
	 * @return
	 */
	int setNewsPublishStatus(News news);
	/**
	 * 增加新闻
	 * @param news
	 * @return
	 */
	int addNews(News news);
	/**
	 * 更改新闻
	 * @param news
	 * @return
	 */
	int editNews(News news);
	/**
	 * 删除新闻
	 * @param newsId
	 * @return
	 */
	int delNews(Integer newsId);
}