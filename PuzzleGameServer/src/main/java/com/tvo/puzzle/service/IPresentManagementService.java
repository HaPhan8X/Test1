package com.tvo.puzzle.service;

import java.util.List;

import com.tvo.puzzle.dto.PresentManagementDTO;

public interface IPresentManagementService {
	List<PresentManagementDTO> getAll();
	void getCVSFile(List<PresentManagementDTO> listDTO,String fileName);
	Integer countPMByKeywordAndProduct(String keyword,Integer selectedProductId,Integer selectedProjectId);
	List<PresentManagementDTO> findRangePMByKeywordAndProduct(String keyword,Integer selectedProductId,Integer selectedProjectId, Integer limit, Integer offset);
}
