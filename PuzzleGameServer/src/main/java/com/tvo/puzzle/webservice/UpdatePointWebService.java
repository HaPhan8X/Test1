package com.tvo.puzzle.webservice;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.tvo.puzzle.dto.MemberDTO;
import com.tvo.puzzle.dto.PointManagementDTO;
import com.tvo.puzzle.dto.ProjectDTO;
import com.tvo.puzzle.service.IMemberService;
import com.tvo.puzzle.service.IPointManagementService;
import com.tvo.puzzle.service.IProjectService;
import com.tvo.puzzle.util.Constants;
import com.tvo.puzzle.webservice.response.ResultResponse;

@Path("/updatePoint")
public class UpdatePointWebService extends BaseWebService {
	IMemberService memberService;
	IPointManagementService pointManagementService;
	IProjectService projectService;
	
	@GET
	@Path("/updatePoint/{playerId}/{projectID}/{point}/{code}")
	@Produces({ "application/xml", "application/json" })
	public ResultResponse updatePoint(@PathParam("playerId") Integer playerId,@PathParam("projectID") Integer projectID,
			@PathParam("point") Integer point, @PathParam("code") String code) {
		ResultResponse res = new ResultResponse();
		memberService = (IMemberService) getService("memberService");
		pointManagementService = (IPointManagementService) getService("pointManagementService");
		projectService = (IProjectService) getService("projectService");
		MemberDTO memberDTO = memberService.findById(playerId);
		ProjectDTO projectDTO = projectService.findById(projectID);
		PointManagementDTO pointManagementDTO = new PointManagementDTO();	
		pointManagementDTO.setTblProject(projectDTO);
		pointManagementDTO.setTblMember(memberDTO);
		pointManagementDTO.setCreatedDate(Calendar.getInstance().getTime());
		pointManagementDTO.setPointValue(point);
		pointManagementDTO.setCode(code);
		memberDTO.setCurrentPoint(memberDTO.getCurrentPoint() + point);
		if (memberService.update(memberDTO) && pointManagementService.saveOrUpdate(pointManagementDTO)) {
			res.setErrCode(Constants.STR_ID_LC_INFO_MSG_001);
			res.setMessage("Success");
			res.setResult(Constants.SUCCESS);
		} else {
			res.setErrCode(Constants.STR_ID_LC_ERR_MSG_001);
			res.setMessage("Fail");
			res.setResult(Constants.FAIL);
		}
		return res;
	}
}
