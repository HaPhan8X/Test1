package com.tvo.puzzle.service;

import com.tvo.puzzle.dto.MemberDTO;
import com.tvo.puzzle.dto.PresentDTO;
import com.tvo.puzzle.entity.Member;

public interface IMemberService {
	public void saveMember(MemberDTO memberDTO);
	public boolean savePresentInfo(PresentDTO p);
	public Member dtoToEntity(MemberDTO memberDTO);
	MemberDTO findById(Integer playerId);
	boolean update(MemberDTO dto);
}

