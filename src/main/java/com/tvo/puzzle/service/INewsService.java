package com.tvo.puzzle.service;

import java.util.List;

import com.tvo.puzzle.dto.NewsDTO;

public interface INewsService {
	Integer countNewsByKeywordAndType(String keyword,String type);
	List<NewsDTO> findRangeNewsByKeywordAndType(String keyword,String type,Integer limit,Integer offset);
}
