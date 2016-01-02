package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public class TreatmentFactory implements ITreatmentFactory {

	@Override
	public Treatment createDrugTreatment(String diagnosis, String drug, float dosage) {
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDrug(drug);
		treatment.setDosage(dosage);
		treatment.setTreatmentType(TreatmentType.DRUG_TREATMENT.getTag());
		return treatment;
	}

	@Override
	public Treatment createSurgery(Date surgeryDate, String diagnosis) {
		Surgery treatment = new Surgery();
		treatment.setSurgeryDate(surgeryDate);
		treatment.setDiagnosis(diagnosis);
		return treatment;
	}

	@Override
	public Treatment createRadiology(List<RadiologyDateValues> rdv, String diagnosis) {
		Radiology treatment = new Radiology();
		treatment.setRadiologydates(rdv);
		treatment.setDiagnosis(diagnosis);
		return treatment;
	}



}
