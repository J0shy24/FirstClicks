package com.project.firstclicks.entity;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseAudit {

	@Column(nullable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@PrePersist
	public void onPrePersist(Object object) {
		if (object instanceof BaseAudit) {
		final BaseAudit baseAudit = (BaseAudit) object;
		baseAudit.setDateCreated(getDateTimeZone());
		}
	}
	
	public static Date getDateTimeZone() {
		DateTimeZone zone = DateTimeZone.forID("Europe/Madrid");
		DateTime dt = new DateTime(zone);
		return dt.toDate();
	}
}
