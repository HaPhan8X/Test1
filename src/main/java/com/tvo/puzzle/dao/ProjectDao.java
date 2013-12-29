package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.Project;

public interface ProjectDao extends GenericDao<Project, Integer> {
	List<Project> getAll();
}
