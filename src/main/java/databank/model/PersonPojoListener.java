/*****************************************************************
 * File: PersonPojo.java Course materials (21W) CST8277
 *
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.model;

import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class PersonPojoListener {

	private static final Logger LOG = LogManager.getLogger();

	@PrePersist
	public void setCreatedOnDate( PersonPojo person) {
		LOG.debug( "Person @PrePersist before = {}", person);
		Instant now = Instant.now();
		person.setCreated( now);
		//might as well call setUpdatedDate as well
		person.setUpdated( now);
		LOG.debug( "Person @PrePersist after = {}", person);
	}

	@PreUpdate
	public void setUpdatedDate( PersonPojo person) {
		LOG.debug( "Person @PreUpdate before = {}", person);
		person.setUpdated( Instant.now());
		LOG.debug( "Person @PreUpdate after = {}", person);
	}

}