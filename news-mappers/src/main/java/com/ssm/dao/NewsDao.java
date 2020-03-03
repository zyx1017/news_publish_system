package com.ssm.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ssm.po.News;
/*
 * 新闻别DAO层接口
 */
public interface NewsDao {
	/**
	 * 获取当前类别新闻总数
	 * @param keywords
	 * @param newsListCategoryId
	 * @return
	 */
	int getNewsCount(@Param("keywords") String keywords, @Param("newsListCategoryId") Integer newsListCategoryId);

	/**
	 * /获取当前类别信息列表
	 * @param keywords
	 * @param newsListCategoryId
	 * @param startRows
	 * @param pageSize
	 * @return
	 */
	List<News> findNewsList(@Param("keywords") String keywords, @Param("newsListCategoryId") Integer newsListCategoryId, @Param("startRows") Integer startRows, @Param("pageSize") Integer pageSize);
	/**
	 * 根据新闻id获取新闻
	 * @param newsId
	 * @return
	 */
	News getNewsByNewsId(Integer newsId);
	/**
	 * 增加新闻
	 * @param news
	 * @return
	 */
	int addNews(News news);
	/**
	 * 修改新闻
	 * @param news
	 * @return
	 */
	int updateNews(News news);
	/**
	 * 修改发布新闻的状态
	 * @param news
	 * @return
	 */
	int setNewsPublishStatus(News news);
	/**
	 * 删除新闻
	 * @param newsId
	 * @return
	 */
	int delNews(Integer newsId);
}