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

@FacesValidator( "phoneValidator")
public class PhoneValidator implements Validator< String> {

	// North American phonenumber pattern
	private static final Pattern PHONE_PATTERN = Pattern
			.compile( "^(\\+\\d( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");

	@Override
	public void validate( FacesContext context, UIComponent component, String value) throws ValidatorException {

		if ( value == null) {
			FacesMessage msg = new FacesMessage( "Phone Number should not be empty",
					"Invalid Phone Number format.");
			msg.setSeverity( FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException( msg);
		}

		Matcher matcher = PHONE_PATTERN.matcher(value);

		if (!matcher.matches()){
			FacesMessage msg = new FacesMessage("Please enter a valid phone number",
					"Invalid Phone Number Format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}


	}

}