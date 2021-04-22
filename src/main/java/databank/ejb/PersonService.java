package databank.ejb;

import databank.model.PersonPojo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Singleton
public class PersonService implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LogManager.getLogger();

    @PersistenceContext(name = "databank-PU")
    protected EntityManager em;

    public PersonService(){}

    public List<PersonPojo> readAllPeople() {
        LOG.debug( "reading all people");
        //use the named JPQL query from the PersonPojo class to grab all the people
        TypedQuery< PersonPojo> allPeopleQuery = em.createNamedQuery( PersonPojo.PERSON_FIND_ALL, PersonPojo.class);
        //execute the query and return the result/s.
        return allPeopleQuery.getResultList();
    }

    @Transactional
    public PersonPojo createPerson( PersonPojo person) {
        LOG.debug( "creating a person = {}", person);
        em.persist(person);
        return person;
    }


    public PersonPojo readPersonById( int personId) {
        LOG.debug( "read a specific person = {}", personId);
        return em.find( PersonPojo.class, personId);
    }

    @Transactional
    public PersonPojo updatePerson( PersonPojo personWithUpdates) {
        LOG.debug( "updating a specific person = {}", personWithUpdates);
        return em.merge( personWithUpdates);
    }


    @Transactional
    public void deletePersonById( int personId) {
        LOG.debug( "reading a specific personID = {}", personId);
        PersonPojo person = readPersonById( personId);
        LOG.debug( "deleting a specific person = {}", person);
        if (person != null) {
            em.refresh(person);
            em.remove(person);
        }
    }

}
