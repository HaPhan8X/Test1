package com.tvo.puzzle.webservice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;

import com.tvo.puzzle.dto.MemberDTO;
import com.tvo.puzzle.dto.PresentDTO;
import com.tvo.puzzle.service.IMemberService;
import com.tvo.puzzle.service.IPresentService;
import com.tvo.puzzle.util.Base64Util;
import com.tvo.puzzle.webservice.response.PresentResponse;
import com.tvo.puzzle.webservice.response.RegistrationResponse;

@Path("/member")
public class RegistrationWebService extends BaseWebService{

	IMemberService memberService;
	IPresentService presentService;
	
	@POST
	@Path("/register")
	@Produces("application/xml")
	public RegistrationResponse register(@FormParam("param") String param){
		memberService = (IMemberService)getService("memberService");
		RegistrationResponse  response= new RegistrationResponse();
		JAXBContext jc;
		MemberDTO memberDTO = null;
		try {
			jc = JAXBContext.newInstance(MemberDTO.class);
			Unmarshaller u = jc.createUnmarshaller();
			param = Base64Util.decodeString(param);
			InputStream is = new ByteArrayInputStream(param.getBytes());
			memberDTO = (MemberDTO) u.unmarshal(is);
			if("".equals(memberDTO.getUdid()) || "".equals(memberDTO.getUserAgent())){
				response.setResult("fail");
				response.setMessage("lack udid or user agent");
				return response;
			}
			memberService.saveMember(memberDTO);
		} catch (JAXBException e) {
			response.setResult("fail");
			response.setMessage("cannot parse xml file");
			return response;
		
		} catch (UnsupportedEncodingException e) {
			
			response.setResult("fail");
			response.setMessage(e.getMessage());
			return response;
		}
		response.setMemberDTO(memberDTO);
		response.setResult("success");
		response.setMessage("success");
		return response;
	}
	
	@POST
	@Path("/profile")
	@Produces("application/xml")
	public PresentResponse registerPresent(@FormParam("param") String param){
		memberService = (IMemberService) getService("memberService");
		PresentResponse presentResponse = new PresentResponse();
		JAXBContext jc;
		PresentDTO presentDTO = null;
		try {
			jc = JAXBContext.newInstance(PresentDTO.class);
			Unmarshaller u = jc.createUnmarshaller();
			param = Base64Util.decodeString(param);
			InputStream is = new ByteArrayInputStream(param.getBytes());
			presentDTO = (PresentDTO)u.unmarshal(is);
			
			
			
		} catch (JAXBException e) {
			presentResponse.setMessage(e.getMessage());
			presentResponse.setResult("fail");
			return presentResponse;
		} catch (UnsupportedEncodingException e) {
			presentResponse.setMessage(e.getMessage());
			presentResponse.setResult("fail");
			return presentResponse;
		}
		if(memberService.savePresentInfo(presentDTO)){
			presentResponse.setMessage("present was saved");
			presentResponse.setResult("success");
			presentResponse.setPresentDTO(presentDTO);
			return presentResponse;
		}else{
			presentResponse.setMessage("Error database connection! present was not saved");
			presentResponse.setResult("fail");
			return presentResponse;
		}
	}
	
}
