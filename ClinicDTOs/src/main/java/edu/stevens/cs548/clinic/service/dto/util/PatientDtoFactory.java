package edu.stevens.cs548.clinic.service.dto.util;

import java.util.List;

import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;

public class PatientDtoFactory {
	
	ObjectFactory factory;
	
	public PatientDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public PatientDto createPatientDto () {
		return factory.createPatientDto();
	}
	
	public PatientDto createPatientDto (Patient p) {
		PatientDto d = factory.createPatientDto();
		/*
		 * TODO: Initialize the fields of the DTO.
		 */
		d.setId(p.getId());
		d.setDob(p.getBirthDate());
		d.setName(p.getName());
		d.setPatientId(p.getPatientId());
		List<Long> tids = p.getTreatmentIds();
		for(Long tid : tids){
			d.getTreatments().add(tid);
		}
		return d;
	}
	public PatientDto createPatientDto (Patient p,int age) {
		PatientDto d = factory.createPatientDto();
		d.setId(p.getId());
		d.setDob(p.getBirthDate());
		d.setName(p.getName());
		d.setPatientId(p.getPatientId());
		d.setAge(age);
		List<Long> tids = p.getTreatmentIds();
		for(Long tid : tids){
			d.getTreatments().add(tid);
		}
		return d;
	}


}
