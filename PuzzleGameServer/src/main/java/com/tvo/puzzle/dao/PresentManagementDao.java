package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.PresentManagement;

public interface PresentManagementDao extends
		GenericDao<PresentManagement, Integer> {
	
	Integer countPMBykeywordAndCategory(String keyword,
			Integer selecselectedProductId,Integer selectedProjectId);
	List<PresentManagement> findRangePMByKeywordAndProduct(String keyword, Integer selectedProductId, Integer selectedProjectId, Integer limit,
			Integer offset);
}
