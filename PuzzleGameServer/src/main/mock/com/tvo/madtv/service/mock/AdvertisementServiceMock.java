package com.tvo.madtv.service.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tvo.madtv.dto.AdvertisementDTO;
import com.tvo.madtv.service.IAdvertisementService;

@Service("advertisementServiceMock")
public class AdvertisementServiceMock implements IAdvertisementService {

	public List<AdvertisementDTO> getPlayerAdvertisementByPlayerID(int playerID) {
		List<AdvertisementDTO> result = new ArrayList<AdvertisementDTO>();
		AdvertisementDTO ad = new AdvertisementDTO();
		ad.setId(1);
		ad.setName("Ba con soi");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);

		//2
		ad = new AdvertisementDTO();
		ad.setId(2);
		ad.setName("Heneiken");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//3
		ad = new AdvertisementDTO();
		ad.setId(3);
		ad.setName("Coca-cola");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//4
		ad = new AdvertisementDTO();
		ad.setId(4);
		ad.setName("Ferrari");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//5
		ad = new AdvertisementDTO();
		ad.setId(5);
		ad.setName("Dau nhot BP");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//6
		ad = new AdvertisementDTO();
		ad.setId(6);
		ad.setName("Hao hao");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//7
		ad = new AdvertisementDTO();
		ad.setId(7);
		ad.setName("Knorr");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//8
		ad = new AdvertisementDTO();
		ad.setId(8);
		ad.setName("Pepsi");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//9
		ad = new AdvertisementDTO();
		ad.setId(9);
		ad.setName("Adidas");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//10
		ad = new AdvertisementDTO();
		ad.setId(10);
		ad.setName("Nike");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		return result;
	}

	@Override
	public Integer countAllAdvertisement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdvertisementDTO> findRangeAdvertisement(Integer limit,
			Integer offset) {
		List<AdvertisementDTO> result = new ArrayList<AdvertisementDTO>();
		AdvertisementDTO ad = new AdvertisementDTO();
		ad.setId(1);
		ad.setName("Ba con soi");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);

		//2
		ad = new AdvertisementDTO();
		ad.setId(2);
		ad.setName("Heneiken");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//3
		ad = new AdvertisementDTO();
		ad.setId(3);
		ad.setName("Coca-cola");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//4
		ad = new AdvertisementDTO();
		ad.setId(4);
		ad.setName("Ferrari");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//5
		ad = new AdvertisementDTO();
		ad.setId(5);
		ad.setName("Dau nhot BP");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//6
		ad = new AdvertisementDTO();
		ad.setId(6);
		ad.setName("Hao hao");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//7
		ad = new AdvertisementDTO();
		ad.setId(7);
		ad.setName("Knorr");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//8
		ad = new AdvertisementDTO();
		ad.setId(8);
		ad.setName("Pepsi");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//9
		ad = new AdvertisementDTO();
		ad.setId(9);
		ad.setName("Adidas");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//10
		ad = new AdvertisementDTO();
		ad.setId(10);
		ad.setName("Nike");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//11
		ad = new AdvertisementDTO();
		ad.setId(11);
		ad.setName("Colgate");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);

		//12
		ad = new AdvertisementDTO();
		ad.setId(12);
		ad.setName("Vinamilk");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//13
		ad = new AdvertisementDTO();
		ad.setId(13);
		ad.setName("Ford");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//14
		ad = new AdvertisementDTO();
		ad.setId(14);
		ad.setName("Tiger");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		
		//15
		ad = new AdvertisementDTO();
		ad.setId(15);
		ad.setName("Milo");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		result.add(ad);
		return result;
	}

	@Override
	public void createAdvertisement(AdvertisementDTO itemDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AdvertisementDTO getAdvertisementById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAdvertisement(AdvertisementDTO itemDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAdvertisementSelect(List<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AdvertisementDTO> getItemNotInDropList(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countAdvertisementByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdvertisementDTO> findRangeAdvertisementByKeyword(
			String keyword, Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
