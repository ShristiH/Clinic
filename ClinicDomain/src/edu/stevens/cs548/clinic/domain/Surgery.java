package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Treatment;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Surgery
 *
 */
@Entity
@DiscriminatorValue("S")
public class Surgery extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	public Date surgeryDate;
	
	public Date getSurgeryDate() {
		return surgeryDate;
	}
	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}
	public Surgery() {
		super();
		this.setTreatmentType("S");
	}
	@Override
	public <T> T export(ITreatmentExporter<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.exportSurgery(this.getId(), 
									this.getDiagnosis(),
									this.getSurgeryDate());
				
	}
	
   
}