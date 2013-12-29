package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.News;

public interface NewsDao extends GenericDao<News, Integer> {
	Integer countNewsByKeywordAndType(String keyword, String type);
	List<News> findRangeNewsByKeywordAndType(String keyword, String type, Integer limit, Integer offset);
}
