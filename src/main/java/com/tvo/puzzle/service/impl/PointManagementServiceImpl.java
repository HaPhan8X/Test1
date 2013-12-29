package com.tvo.puzzle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.PointManagementDao;
import com.tvo.puzzle.dto.PointManagementDTO;
import com.tvo.puzzle.entity.Member;
import com.tvo.puzzle.entity.PointManagement;
import com.tvo.puzzle.entity.Project;
import com.tvo.puzzle.service.IPointManagementService;
@Service("pointManagementService")
public class PointManagementServiceImpl implements IPointManagementService {

	@Autowired
	PointManagementDao pointManagementDAO;
	
	@Override
	@Transactional
	public boolean saveOrUpdate(PointManagementDTO dto) {
		try {
			PointManagement entity = new PointManagement();
			dtoToEntity(dto, entity);
			pointManagementDAO.saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	private void dtoToEntity(PointManagementDTO dto, PointManagement entity)
	{
		Member m = new Member();
		m.setId(dto.getTblMember().getId());
		Project p = new Project();
		p.setId(dto.getTblProject().getId());
		entity.setTblProject(p);
		entity.setTblMember(m);
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setPointValue(dto.getPointValue());
		entity.setCode(dto.getCode());
	}

}
