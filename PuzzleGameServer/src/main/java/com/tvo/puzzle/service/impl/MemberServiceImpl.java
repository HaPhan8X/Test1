
package com.tvo.puzzle.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.MemberDao;
import com.tvo.puzzle.dao.ProductDao;
import com.tvo.puzzle.dto.MemberDTO;
import com.tvo.puzzle.dto.PresentDTO;
import com.tvo.puzzle.entity.Member;
import com.tvo.puzzle.entity.PointManagement;
import com.tvo.puzzle.entity.PresentManagement;
import com.tvo.puzzle.entity.Product;
import com.tvo.puzzle.service.IMemberService;

@Service("memberService")
public class MemberServiceImpl implements IMemberService {

	@Autowired
	MemberDao memberDao;

	@Autowired
	ProductDao productDao;

	@Transactional
	public void saveMember(MemberDTO memberDTO) {
		Member m = dtoToEntity(memberDTO);
		memberDao.saveOrUpdate(m);
		// memberDao.saveMember(m);
	}

	@Override
	@Transactional
	public MemberDTO findById(Integer playerId) {
		Member entity = memberDao.findById(playerId);
		MemberDTO dto = new MemberDTO();
		entityToDTO(entity, dto);
		return dto;
	}
	
	@Transactional
	public boolean savePresentInfo(PresentDTO p) {
		PresentManagement pm = dtoToEntity(p);
		Member m = pm.getTblMember();
		Product pr = pm.getTblProduct();
		if (pm.getRegisterDate().after(pr.getEndTime())
				|| pm.getRegisterDate().before(pr.getStartTime())) {
			return false;
		} else if (!pr.getValid()) {
			return false;
		} else if (m.getCurrentPoint() < pr.getRequiredPoint()) {
			return false;
		}
		m.setCurrentPoint(m.getCurrentPoint() - pr.getRequiredPoint());
		PointManagement point = new PointManagement();
		point.setCreatedDate(new Date());
		point.setTblMember(m);
		point.setPointValue(pr.getRequiredPoint());
		
		memberDao.saveOrUpdate(m);
		memberDao.savePointLog(point);
		memberDao.savePresent(pm);
		return true;
	}
	
	private void dtoToEntity(MemberDTO dto, Member entity)
	{
		entity.setId(dto.getId());
		entity.setUdid(dto.getUdid());
		entity.setRegisterDate(dto.getRegisterDate());
		entity.setUserAgent(dto.getUserAgent());
		entity.setCurrentPoint(dto.getCurrentPoint());
	}
	
	private void entityToDTO(Member entity, MemberDTO dto){		
		dto.setId(entity.getId());
		dto.setUdid(entity.getUdid());
		dto.setRegisterDate(entity.getRegisterDate());
		dto.setUserAgent(entity.getUserAgent());
		dto.setCurrentPoint(entity.getCurrentPoint());
	}

	@Override
	public Member dtoToEntity(MemberDTO memberDTO) {
		Member m = new Member();
		m.setCurrentPoint(0);
		m.setRegisterDate(new Date());
		m.setUdid(memberDTO.getUdid());
		m.setUserAgent(memberDTO.getUserAgent());
		return m;
	}

	public PresentManagement dtoToEntity(PresentDTO pd) {
		PresentManagement pm = new PresentManagement();
		Product p = productDao.getProduct(pd.getProductId());
		Member m = memberDao.findById(pd.getMemberId());
		pm.setAddress(pd.getAddress());
		pm.setGender(pd.isGender());
		pm.setMailAddress(pd.getMailAddress());
		pm.setMemberName(pd.getMemberName());
		pm.setPhoneNumber(pd.getPhoneNumber());
		pm.setPostalCode(pd.getPostalCode());
		pm.setRegisterDate(new Date());
		pm.setMessage(pd.getMessage());
		pm.setTblMember(m);
		pm.setTblProduct(p);
		return pm;
	}
	
	@Override
	@Transactional
	public boolean update(MemberDTO dto) {
		try {
			Member entity = new Member();
			dtoToEntity(dto, entity);
			memberDao.update(entity);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

