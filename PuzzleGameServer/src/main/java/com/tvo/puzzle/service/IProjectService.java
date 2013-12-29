package com.tvo.puzzle.service;

import java.util.List;

import com.tvo.puzzle.dto.ProjectDTO;

public interface IProjectService {
	List<ProjectDTO> getAll();
	ProjectDTO findById(Integer projectId);
}
