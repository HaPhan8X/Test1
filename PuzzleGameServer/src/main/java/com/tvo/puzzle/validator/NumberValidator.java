package com.tvo.puzzle.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("numberValidator")
public class NumberValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) 
	{
		if (Float.valueOf(value.toString())<0)
		{
			FacesMessage msg = new FacesMessage(component.getAttributes().get("label")+" must than 0");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
