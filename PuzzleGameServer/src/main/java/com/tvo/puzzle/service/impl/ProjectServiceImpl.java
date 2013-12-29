package com.tvo.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.ProjectDao;
import com.tvo.puzzle.dto.ProjectDTO;
import com.tvo.puzzle.entity.Project;
import com.tvo.puzzle.service.IProjectService;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	ProjectDao projectDao;
	
	@Override
	@Transactional
	public List<ProjectDTO> getAll() {
		List<Project> listEntity = new ArrayList<Project>();
		listEntity = projectDao.getAll();
		if(listEntity != null)
		{
			List<ProjectDTO> listDTO = new ArrayList<ProjectDTO>();
			for(Project entity : listEntity)
			{
				ProjectDTO dto = new ProjectDTO();
				entityToDTO(entity, dto);
				listDTO.add(dto);
			}
			return listDTO;
		}
		return null;
	}
	
	private void entityToDTO(Project entity, ProjectDTO dto)
	{
		dto.setId(entity.getId());
		dto.setProjectName(entity.getProjectName());
	}

	@Override
	@Transactional
	public ProjectDTO findById(Integer projectId) {
		Project entity = projectDao.findById(projectId);
		ProjectDTO dto = new ProjectDTO();
		entityToDTO(entity, dto);
		return dto;
	}

}
