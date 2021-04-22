/*****************************************************************
 * File: PersonPojo.java Course materials (21W) CST8277
 *
 * @author Shariar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.jsf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator( "emailValidator")
public class EmailValidator implements Validator<String> {

	// really really (!) simple email pattern: at-least-1-letter, '@', at-least-1-letter
	private static final Pattern EMAIL_PATTERN = Pattern.compile( "^(.+)@(.+)$");

	@Override
	public void validate( FacesContext context, UIComponent component, String value) throws ValidatorException {

		if (value == null) {
			FacesMessage msg = new FacesMessage( "Email should not be empty", "Invalid Email format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		Matcher matcher = EMAIL_PATTERN.matcher(value);

		if (!matcher.matches()){
			FacesMessage msg = new FacesMessage("Please enter a valid email address", "Invalid Email Format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}