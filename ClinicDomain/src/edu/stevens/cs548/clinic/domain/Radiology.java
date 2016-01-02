package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Treatment;
import java.io.Serializable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
/**
 * Entity implementation class for Entity: Radiology
 *
 */
@Entity
@DiscriminatorValue("R")
public class Radiology extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@ElementCollection
	@CollectionTable(name="RadiologyDateValue", joinColumns =@JoinColumn(name="radiology_fk"))
	private List<RadiologyDateValues> radiologydates = new ArrayList<RadiologyDateValues>();
	
	public List<RadiologyDateValues> getRadiologydates() {
		return radiologydates;
	}

	public void setRadiologydates(List<RadiologyDateValues> radiologydates) {
		this.radiologydates = radiologydates;
	}

	public Radiology() {
		super();
		this.setTreatmentType("R");
	}

	@Override
	public <T> T export(ITreatmentExporter<T> visitor) {
		// TODO Auto-generated method stub
		List<Date> dateList = new ArrayList<Date>();
		for (RadiologyDateValues date : this.getRadiologydates())
		{
			dateList.add(date.getDate());
		}
		return visitor.exportRadiology(this.getId(), 
										this.getDiagnosis(), 
										dateList);
	}
   
}
