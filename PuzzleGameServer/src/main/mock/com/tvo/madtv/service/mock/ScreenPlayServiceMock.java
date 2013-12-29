package com.tvo.madtv.service.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tvo.madtv.dto.ScreenPlayDTO;
import com.tvo.madtv.service.IScreenPlayService;

@Service("screenPlayServiceMock")
public class ScreenPlayServiceMock implements IScreenPlayService {

	public List<ScreenPlayDTO> findRange(Integer showCategoryId, String name,
			Integer limit, Integer offset) {
		List<ScreenPlayDTO> result = new ArrayList<ScreenPlayDTO>();
		
		ScreenPlayDTO screenplay = new ScreenPlayDTO();
		screenplay.setId(1);
		screenplay.setName("Titanic");
		screenplay.setXp(100);
		screenplay.setLovePoint(1);
		screenplay.setEstimatedViewer(50000);
		screenplay.setMoneyGold(5000);
		screenplay.setMoneyCoin(30);
		//TODO level
		//TODO xpviewerdecrease
		screenplay.setReduceRate(25);
		screenplay.setProductionTime(5);
		screenplay.setImage("image.jpg");
		screenplay.setEpisodesNumber(2);
		result.add(screenplay);
		
		return result;
	}

	public int create(ScreenPlayDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean update(ScreenPlayDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	public int countAll(Integer showCategoryId, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ScreenPlayDTO findById(Integer screenplayId) {
		// TODO Auto-generated method stub
		return null;
	}

}
