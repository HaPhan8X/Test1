/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tvo.puzzle.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.tvo.puzzle.util.StringUtil;

/****
 * 
 * 
 * @author MinhTQ
 * 
 */
@FacesValidator("PriceValidator")
public class PriceValidator implements Validator {

	private static final String PRICE_PATTERN = "\\d*\\.?\\d*$";

	private Pattern pattern;
	private Matcher matcher;

	public PriceValidator() {
		pattern = Pattern.compile(PRICE_PATTERN);
	} 
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value){
		try {
			if(StringUtil.isNull(value.toString())){
				FacesMessage msg = new FacesMessage("Invalid price.",
						"Invalid price.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				String otherControlId = (String) component.getAttributes().get("otherControlId");
				System.out.println(otherControlId);
				throw new ValidatorException(msg);
			}else{
				matcher = pattern.matcher(value.toString());
				String otherControlId = (String) component.getId();
				if (!matcher.matches()) {
					FacesMessage msg = new FacesMessage("Price must enter digits.",
							"Invalid Price format.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
