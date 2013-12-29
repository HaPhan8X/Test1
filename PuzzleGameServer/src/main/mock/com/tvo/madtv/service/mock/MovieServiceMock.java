package com.tvo.madtv.service.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tvo.madtv.dto.ShowCategoryDTO;
import com.tvo.madtv.dto.ShowDTO;
import com.tvo.madtv.dto.ShowEpisodeDTO;
import com.tvo.madtv.service.IMovieService;

@Service("movieServiceMock")
public class MovieServiceMock implements IMovieService {

	@Override
	public List<ShowDTO> getAllMovie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countAllShow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countShowByCategory(Integer selectedCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowDTO> findRangeShow(Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowDTO> findRangeShowByCatgory(Integer limit, Integer offset,
			Integer selectedCategoryId) {
		List<ShowDTO> result = new ArrayList<ShowDTO>();
		
		ShowDTO movie = new ShowDTO();
		movie.setId(3);
		movie.setTitle("Avatar");
		movie.setCategoryId(3);
		movie.setCategoryName("Action");
		movie.setDuration(120);
		movie.setXp(100);
		movie.setLovePoint(10);
		movie.setEstimatedViewer(50000);
		movie.setMoneyGold(5000);
		movie.setMoneyCoin(30);
		movie.setLevelId(10);
		movie.setRequiredFriend(10);
		movie.setNumberOfEpisodes(3);
		movie.setXpViewerDecrease(20);
		movie.setReduceRate(25);
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
		movie.setMoneyGold(5000);
		movie.setMoneyCoin(30);
		movie.setLevelId(10);
		movie.setRequiredFriend(10);
		movie.setNumberOfEpisodes(3);
		movie.setXpViewerDecrease(20);
		movie.setReduceRate(25);
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
		movie.setMoneyGold(5000);
		movie.setMoneyCoin(30);
		movie.setLevelId(10);
		movie.setRequiredFriend(10);
		movie.setNumberOfEpisodes(3);
		movie.setXpViewerDecrease(20);
		movie.setReduceRate(25);
		result.add(movie);
		
		return result;
	}

	@Override
	public List<ShowCategoryDTO> findRangeCategory(Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowCategoryDTO> getAllShowCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShowCategorySelect(List<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int createCategory(ShowCategoryDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ShowCategoryDTO getShowCategoryById(Integer showCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCategory(ShowCategoryDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteMovieSelect(List<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int createMovie(ShowDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateMovie(ShowDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ShowDTO getMovieById(Integer movieId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countAllEpisodeMovie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countEpisodeByMovie(Integer selected) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowEpisodeDTO> findRangeEpisode(Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowEpisodeDTO> findRangeEpisodeByMovie(Integer limit,
			Integer offset, Integer selectedMovieId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createEpisode(ShowEpisodeDTO episode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ShowEpisodeDTO getEpisodeById(Integer episodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEpisode(ShowEpisodeDTO episodeDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEpisodeSelect(List<Integer> ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer countShowByKeyWordAndSelectedCategory(
			Integer selectedCategoryId, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowDTO> findRangerShowByKeyWordAndSelectedCategory(
			Integer selectedCategoryId, String searchKeyword, Integer limit,
			Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countShowCategoryByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowCategoryDTO> findRangeCategoryByKeyword(String keyword,
			Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShowDTO> findRangerPlayerShowFromStore(Integer playerID,
			Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countPlayerShow(Integer playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
