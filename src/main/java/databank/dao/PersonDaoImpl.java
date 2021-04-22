/*****************************************************************
 * File: PersonPojo.java Course materials (21W) CST8277
 *
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import databank.ejb.PersonService;

import databank.model.PersonPojo;


@Named
@ApplicationScoped
public class PersonDaoImpl implements PersonDao, Serializable {
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	@EJB
	protected PersonService personService;


	@Override
	public List< PersonPojo> readAllPeople() {
		return personService.readAllPeople();
	}

	@Override
	public PersonPojo createPerson( PersonPojo person) {
		return personService.createPerson(person);
	}

	@Override
	public PersonPojo readPersonById( int personId) {
		return personService.readPersonById(personId);
	}

	@Override
	public PersonPojo updatePerson( PersonPojo personWithUpdates) {
		return personService.updatePerson(personWithUpdates);
	}

	@Override
	public void deletePersonById( int personId) {
		personService.deletePersonById(personId);
	}

}