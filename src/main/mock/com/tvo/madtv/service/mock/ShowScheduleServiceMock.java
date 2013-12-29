package com.tvo.madtv.service.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tvo.madtv.dto.AdvertisementDTO;
import com.tvo.madtv.dto.ShowDTO;
import com.tvo.madtv.dto.ShowScheduleDTO;
import com.tvo.madtv.service.IShowScheduleService;

@Service("showScheduleServiceMock")
public class ShowScheduleServiceMock implements IShowScheduleService {

	public List<ShowScheduleDTO> getTimetableByPlayerID(int playerID) {
		List<ShowScheduleDTO> result = new ArrayList<ShowScheduleDTO>();
		
		// row 1
		ShowScheduleDTO item = new ShowScheduleDTO();
		item.setIndex(0);
		// add movie
		ShowDTO movie = new ShowDTO();
		movie.setId(1);
		movie.setTitle("Maria Ozawa");
		movie.setCategoryId(1);
		movie.setCategoryName("Sex");
		movie.setDuration(90);
		movie.setXp(1000);
		movie.setLovePoint(100);
		movie.setEstimatedViewer(50000);
		item.setTblShow(movie);
		// add advertisement
		AdvertisementDTO ad = new AdvertisementDTO();
		ad.setId(1);
		ad.setName("Ba con soi");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		item.setTblAdvertisement(ad);
		result.add(item);
		
		// row 2
		item = new ShowScheduleDTO();
		item.setIndex(1);
		// add movie
		movie = new ShowDTO();
		movie.setId(2);
		movie.setTitle("Titanic");
		movie.setCategoryId(2);
		movie.setCategoryName("Romatic");
		movie.setDuration(120);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		item.setTblShow(movie);
		// add advertisement
		ad = new AdvertisementDTO();
		ad.setId(2);
		ad.setName("Heneiken");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		item.setTblAdvertisement(ad);
		result.add(item);
		
		// row 3
		item = new ShowScheduleDTO();
		item.setIndex(1);
		// add movie
		movie = new ShowDTO();
		movie.setId(3);
		movie.setTitle("Avatar");
		movie.setCategoryId(3);
		movie.setCategoryName("Action");
		movie.setDuration(120);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		item.setTblShow(movie);
		// add advertisement
		ad = new AdvertisementDTO();
		ad.setId(3);
		ad.setName("Coca-cola");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		item.setTblAdvertisement(ad);
		result.add(item);
		
		// row 4
		item = new ShowScheduleDTO();
		item.setIndex(1);
		// add movie
		movie = new ShowDTO();
		movie.setId(4);
		movie.setTitle("Transporter");
		movie.setCategoryId(3);
		movie.setCategoryName("Action");
		movie.setDuration(95);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		item.setTblShow(movie);
		// add advertisement
		ad = new AdvertisementDTO();
		ad.setId(4);
		ad.setName("Ferrari");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		item.setTblAdvertisement(ad);
		result.add(item);
		
		// row 5
		item = new ShowScheduleDTO();
		item.setIndex(1);
		// add movie
		movie = new ShowDTO();
		movie.setId(5);
		movie.setTitle("Deadth Race");
		movie.setCategoryId(3);
		movie.setCategoryName("Action");
		movie.setDuration(95);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		item.setTblShow(movie);
		// add advertisement
		ad = new AdvertisementDTO();
		ad.setId(5);
		ad.setName("Dau nhot BP");
		ad.setEarning(20000);
		ad.setPenalty(15000);
		ad.setRequiredViewer(100000);
		item.setTblAdvertisement(ad);
		result.add(item);
		
		return result;
	}

	public List<ShowDTO> getMoviesByPlayerID(int playerID) {
		List<ShowDTO> result = new ArrayList<ShowDTO>();
		ShowDTO movie = new ShowDTO();
		movie.setId(1);
		movie.setTitle("Maria Ozawa");
		movie.setCategoryId(1);
		movie.setCategoryName("Sex");
		movie.setDuration(90);
		movie.setXp(1000);
		movie.setLovePoint(100);
		movie.setEstimatedViewer(50000);
		result.add(movie);
		
		//2
		movie = new ShowDTO();
		movie.setId(2);
		movie.setTitle("Titanic");
		movie.setCategoryId(2);
		movie.setCategoryName("Romatic");
		movie.setDuration(120);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		result.add(movie);
		
		//3
		movie = new ShowDTO();
		movie.setId(3);
		movie.setTitle("Avatar");
		movie.setCategoryId(3);
		movie.setCategoryName("Action");
		movie.setDuration(120);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		result.add(movie);
		
		//4
		movie = new ShowDTO();
		movie.setId(4);
		movie.setTitle("Transporter");
		movie.setCategoryId(3);
		movie.setCategoryName("Action");
		movie.setDuration(95);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		result.add(movie);
		
		//5
		movie = new ShowDTO();
		movie.setId(5);
		movie.setTitle("Deadth Race");
		movie.setCategoryId(3);
		movie.setCategoryName("Action");
		movie.setDuration(95);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		result.add(movie);
		return result;
	}

	public List<AdvertisementDTO> getAdvertisementsByPlayerID(int playerID) {
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
		return result;
	}

	public ShowDTO getCurrentMovieInfo(int playerID) {
		ShowDTO currentMovie = new ShowDTO();
		currentMovie.setId(1);
		currentMovie.setTitle("Maria Ozawa");
		currentMovie.setCategoryId(1);
		currentMovie.setCategoryName("Sex");
		currentMovie.setDuration(90);
		currentMovie.setXp(1000);
		currentMovie.setLovePoint(100);
		currentMovie.setEstimatedViewer(50000);
		return currentMovie;
	}

	public void finishShowMovie(int playerID, int movieId, int advertisementId) {
		// TODO Auto-generated method stub
		
	}

}
