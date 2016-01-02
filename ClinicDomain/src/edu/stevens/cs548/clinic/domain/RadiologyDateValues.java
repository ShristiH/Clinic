package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RadiologyDateValues
 *
 */
@Embeddable

public class RadiologyDateValues implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public RadiologyDateValues() {
		super();
	}
   
}
