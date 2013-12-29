package com.tvo.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.NewsDao;
import com.tvo.puzzle.dto.NewsDTO;
import com.tvo.puzzle.entity.News;
import com.tvo.puzzle.service.INewsService;

@Service("newsService")
public class NewsServiceImpl implements INewsService {

	@Autowired
	NewsDao newsDao;	
	
	private void entityToDTO(News entity, NewsDTO dto)
	{
		dto.setId(entity.getId());
		dto.setNews_id(entity.getNews_id());
		dto.setType(entity.getType());
		dto.setTitle(entity.getTitle());
		dto.setHeader(entity.getHeader());
		dto.setContent(entity.getContent());
		dto.setLanguage(entity.getLanguage());
		dto.setImage(entity.getImage());
	}

	@Override
	@Transactional
	public Integer countNewsByKeywordAndType(String keyword, String type) {		
		return newsDao.countNewsByKeywordAndType(keyword, type);
	}

	@Override
	@Transactional
	public List<NewsDTO> findRangeNewsByKeywordAndType(String keyword,
			String type, Integer limit, Integer offset) {
		List<News> listEntity = newsDao.findRangeNewsByKeywordAndType(keyword, type, limit, offset);
		List<NewsDTO> listDTO = new ArrayList<NewsDTO>();
		for(News entity : listEntity)
		{
			NewsDTO dto = new NewsDTO();
			entityToDTO(entity, dto);
			listDTO.add(dto);
		}
		return listDTO;
	}

}
