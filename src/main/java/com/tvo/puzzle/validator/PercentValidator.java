package com.tvo.puzzle.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("PercentValidator")
public class PercentValidator implements Validator{

	private static final String PRICE_PATTERN = "\\d*\\.?\\d*$";

	private Pattern pattern;
	private Matcher matcher;

	public PercentValidator() {
		pattern = Pattern.compile(PRICE_PATTERN);
	}
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) {

		try {
			matcher = pattern.matcher(value.toString());
			if (!matcher.matches()) {
				FacesMessage msg = new FacesMessage("Percent must be digits.",
						"Invalid Percent format.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
