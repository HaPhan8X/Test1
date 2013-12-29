package com.tvo.madtv.service.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tvo.madtv.dto.ShowCategoryDTO;
import com.tvo.madtv.service.IShowCategoryService;

@Service("showCategoryServiceMock")
public class ShowCategoryServiceMock implements IShowCategoryService {

	public List<ShowCategoryDTO> getall() {
		List<ShowCategoryDTO> result = new ArrayList<ShowCategoryDTO>();
		ShowCategoryDTO category = new ShowCategoryDTO();
		category.setId(1);
		category.setName("Sex");
//		category.setLovePoint(1000);
		category.setImage("image.jpg");
		result.add(category);
		
		category = new ShowCategoryDTO();
		category.setId(2);
		category.setName("Romantic");
//		category.setLovePoint(100);
		category.setImage("image.jpg");
		result.add(category);
		
		category = new ShowCategoryDTO();
		category.setId(3);
		category.setName("Action");
//		category.setLovePoint(100);
		category.setImage("image.jpg");
		result.add(category);
		
		category = new ShowCategoryDTO();
		category.setId(4);
		category.setName("Comedy");
//		category.setLovePoint(100);
		category.setImage("image.jpg");
		result.add(category);
		
		category = new ShowCategoryDTO();
		category.setId(5);
		category.setName("Dramma");
//		category.setLovePoint(10);
		category.setImage("image.jpg");
		result.add(category);
		
		return result;
	}

}
