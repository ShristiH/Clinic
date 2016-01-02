package edu.stevens.cs548.clinic.service.dto.util;

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.Radiology;
import edu.stevens.cs548.clinic.domain.RadiologyDateValues;
import edu.stevens.cs548.clinic.domain.Surgery;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public class TreatmentDtoFactory {
	
	ObjectFactory factory;
	
	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}
	
//	public DrugTreatmentType createDrugTreatmentDto () {
//		return factory.createDrugTreatmentType();
//	}
//
//	public SurgeryType createsurgeryTreatmentDto () {
//		return factory.createSurgeryType();
//	}
//	public RadiologyType createRadiologyTreatmentDto () {
//		return factory.createRadiologyType();
//	}
	
	public TreatmentDto createDrugTreatmentDto () {
		return factory.createTreatmentDto();
	}

	public TreatmentDto createsurgeryTreatmentDto () {
		return factory.createTreatmentDto();
	}
	public TreatmentDto createRadiologyTreatmentDto () {
		return factory.createTreatmentDto();
	}
	
//	public TreatmentDto createTreatmentDto(DrugTreatment t) {
//		TreatmentDto treatment = factory.createTreatmentDto();
//		treatment.setDiagnosis(t.getDiagnosis());
//		DrugTreatmentType drugTreatment = this.createDrugTreatmentDto();
//		drugTreatment.setDosage(t.getDosage());
//		drugTreatment.setName(t.getDrug());
//		treatment.setDrugTreatment(drugTreatment);
//		return treatment;
//	}
	
	public TreatmentDto createTreatmentDto(DrugTreatment t) {
		TreatmentDto treatment = factory.createTreatmentDto();
		treatment.setDiagnosis(t.getDiagnosis());
		DrugTreatmentType drugTreatment = new DrugTreatmentType();
		drugTreatment.setDosage(t.getDosage());
		drugTreatment.setName(t.getDrug());
		treatment.setDrugTreatment(drugTreatment);
		return treatment;
	}
	
	public TreatmentDto createTreatmentDto(Surgery t){
		TreatmentDto treatment = factory.createTreatmentDto();
		treatment.setDiagnosis(t.getDiagnosis());
		SurgeryType surgeryTreatment = new SurgeryType();
		surgeryTreatment.setDate(t.getSurgeryDate());
		treatment.setSurgery(surgeryTreatment);
		return treatment;
		
	}
	
	public TreatmentDto createTreatmentDto(Radiology t){
		TreatmentDto treatment = factory.createTreatmentDto();
		treatment.setDiagnosis(t.getDiagnosis());
		RadiologyType radiologyTreatment = new RadiologyType();
		for(RadiologyDateValues d :t.getRadiologydates()){
			radiologyTreatment.getDate().add(d.getDate());	
		}
		treatment.setRadiology(radiologyTreatment);
		return treatment;
		
	}

}
