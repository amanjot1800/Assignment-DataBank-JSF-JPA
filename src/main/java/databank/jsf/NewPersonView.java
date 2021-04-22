/*****************************************************************
 * File: PersonPojo.java Course materials (21W) CST8277
 *
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.jsf;

import java.io.Serializable;
import java.time.Instant;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import databank.model.PersonPojo;


@Named("newPerson")
@ViewScoped
public class NewPersonView implements Serializable {
	/** explicit set serialVersionUID */
	private static final long serialVersionUID = 1L;

	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phoneNumber;
	protected Instant created;
	protected Instant updated;
	protected int version;

	@Inject
	@ManagedProperty( "#{personController}")
	protected PersonController personController;

	public NewPersonView() {
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName firstName to set
	 */
	public void setFirstName( String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName LastName to set
	 */
	public void setLastName( String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Instant getUpdated() {
		return updated;
	}

	public void setUpdated(Instant updated) {
		this.updated = updated;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void addPerson() {
		if ( allNotNullOrEmpty( firstName, lastName, email, phoneNumber)) {

			PersonPojo theNewPerson = new PersonPojo();

			theNewPerson.setFirstName(getFirstName());
			theNewPerson.setLastName(getLastName());
			theNewPerson.setEmail(getEmail());
			theNewPerson.setPhoneNumber(getPhoneNumber());
			theNewPerson.setCreated(Instant.now());
			theNewPerson.setUpdated(Instant.now());

			personController.addNewPerson( theNewPerson);

			//clean up
			personController.toggleAdding();
			setFirstName( null);
			setLastName( null);
			setEmail(null);
			setPhoneNumber(null);
			setCreated(null);
			setUpdated(null);
			setVersion(0);

		}
	}

	static boolean allNotNullOrEmpty( final Object... values) {
		if ( values == null) {
			return false;
		}
		for ( final Object val : values) {
			if ( val == null) {
				return false;
			}
			if ( val instanceof String) {
				String str = (String) val;
				if ( str.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}