package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public interface ITreatmentFactory {
	
	public Treatment createDrugTreatment (String diagnosis, String drug, float dosage);
	
	public Treatment createSurgery (Date surgeryDate, String diagnosis);
	
	public Treatment createRadiology (List<RadiologyDateValues> rdv, String diagnosis);

}
