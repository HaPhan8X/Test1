package com.tvo.puzzle.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import com.tvo.puzzle.dto.PresentManagementDTO;

public class PresentManagementModel extends ListDataModel<PresentManagementDTO> {

	public PresentManagementModel(List<PresentManagementDTO> list){
		super(list);
	}
}
