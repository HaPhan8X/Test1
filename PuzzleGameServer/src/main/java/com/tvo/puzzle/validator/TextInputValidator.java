package com.tvo.puzzle.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
@FacesValidator("textInputValidator")
public class TextInputValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) 
	{
		  String inputText = (String)value;
		  inputText = inputText.trim();
		  if(inputText.isEmpty())
		  {
			  FacesMessage msg1 = new FacesMessage(component.getAttributes().get("label")+" is not blank");
			  msg1.setSeverity(FacesMessage.SEVERITY_ERROR);
			  throw new ValidatorException(msg1);
		  }
	}
}
