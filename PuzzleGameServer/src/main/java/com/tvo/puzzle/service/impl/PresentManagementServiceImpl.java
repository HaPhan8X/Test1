package com.tvo.puzzle.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.PresentManagementDao;
import com.tvo.puzzle.dto.MemberDTO;
import com.tvo.puzzle.dto.PresentManagementDTO;
import com.tvo.puzzle.dto.ProductDTO;
import com.tvo.puzzle.entity.Member;
import com.tvo.puzzle.entity.PresentManagement;
import com.tvo.puzzle.service.IPresentManagementService;
import com.tvo.puzzle.util.Constants;
import com.tvo.puzzle.util.PropertiesUtil;

@Service("presentManagementService")
public class PresentManagementServiceImpl implements IPresentManagementService {

	@Autowired
	PresentManagementDao pmDAO;

	@Override
	@Transactional
	public List<PresentManagementDTO> getAll() {
		List<PresentManagement> listEntity = new ArrayList<PresentManagement>();
		listEntity = pmDAO.findAll();
		if (listEntity != null) {
			List<PresentManagementDTO> listDTO = new ArrayList<PresentManagementDTO>();
			for (PresentManagement pm : listEntity) {
				PresentManagementDTO pmDTO = new PresentManagementDTO();
				entityToDTO(pm, pmDTO);
				listDTO.add(pmDTO);
			}
			return listDTO;
		}
		return null;
	}

	private void entityToDTO(PresentManagement entity, PresentManagementDTO dto) {
		dto.setId(entity.getId());

		MemberDTO m = new MemberDTO();
		m.setId(entity.getTblMember().getId());
		dto.setTblMember(m);

		ProductDTO p = new ProductDTO();
		p.setId(entity.getTblProduct().getId());
		p.setProductName(entity.getTblProduct().getProductName());
		dto.setTblProduct(p);
		dto.setRegisterDate(entity.getRegisterDate());
		dto.setMemberName(entity.getMemberName());
		dto.setPostalCode(entity.getPostalCode());
		dto.setAddress(entity.getAddress());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setMailAddress(entity.getMailAddress());
		if (entity.getGender())
			dto.setGender("Male");
		else
			dto.setGender("Female");
		dto.setMessage(entity.getMessage());
	}

	@Override
	@Transactional
	public void getCVSFile(List<PresentManagementDTO> listDTO, String fileName) {		
		try {			
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			response.setContentType("application/octet-stream");
			ServletOutputStream out = response.getOutputStream();
			StringBuffer sb = generateCsvFileBuffer(listDTO);
	 
			InputStream in =  new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
	 
			byte[] outputByte = new byte[4096];
			while(in.read(outputByte, 0, 4096) != -1)
			{
				out.write(outputByte, 0, 4096);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static StringBuffer generateCsvFileBuffer(List<PresentManagementDTO> listDTO){
		StringBuffer writer = new StringBuffer();
		writer.append("Product Name \t\t Name of Member \t\t Gender \t\t Address \t\t Phone Number \t\t Mail Address \t\t\t Message           ");
		writer.append(System.getProperty("line.separator"));
		for (PresentManagementDTO pmDTO : listDTO) {
			writer.append(String.format("%-25s", pmDTO.getTblProduct()
					.getProductName())
					+ String.format("%-32s", pmDTO.getMemberName())
					+ String.format("%-24s", pmDTO.getGender())
					+ String.format("%-24s", pmDTO.getAddress())
					+ String.format("%-24s", pmDTO.getPhoneNumber())
					+ String.format("%-20s", pmDTO.getMailAddress())
					+ String.format("%20s", pmDTO.getMessage()) + " ");
			writer.append(System.getProperty("line.separator"));
		}
		return writer;
	}

	@Override
	@Transactional
	public Integer countPMByKeywordAndProduct(String keyword,
			Integer selecselectedProductId,Integer selectedProjectId) {
		return pmDAO.countPMBykeywordAndCategory(keyword,
				selecselectedProductId,selectedProjectId);
	}

	@Override
	@Transactional
	public List<PresentManagementDTO> findRangePMByKeywordAndProduct(
			String keyword, Integer selectedProductId,Integer selectedProjectId, Integer limit,
			Integer offset) {
		List<PresentManagement> listEntity = pmDAO
				.findRangePMByKeywordAndProduct(keyword, selectedProductId,selectedProjectId,
						limit, offset);
		List<PresentManagementDTO> listDTO = new ArrayList<PresentManagementDTO>();
		for (PresentManagement entity : listEntity) {
			PresentManagementDTO dto = new PresentManagementDTO();
			entityToDTO(entity, dto);
			listDTO.add(dto);
		}
		return listDTO;
	}

}
