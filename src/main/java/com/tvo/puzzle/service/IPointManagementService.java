package com.tvo.puzzle.service;

import com.tvo.puzzle.dto.PointManagementDTO;

public interface IPointManagementService {
	boolean saveOrUpdate(PointManagementDTO pointManagementDTO);
}
