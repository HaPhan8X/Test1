package com.tvo.puzzle.dao;

import com.tvo.puzzle.entity.Member;
import com.tvo.puzzle.entity.PointManagement;
import com.tvo.puzzle.entity.PresentManagement;

public interface MemberDao extends GenericDao<Member, Integer> {
	public void saveMember(Member m);

	public void savePointLog(PointManagement point);

	public void savePresent(PresentManagement pm);
}
