package edu.stevens.cs548.clinic.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.Radiology;
import edu.stevens.cs548.clinic.domain.RadiologyDateValues;
import edu.stevens.cs548.clinic.domain.Surgery;
import edu.stevens.cs548.clinic.domain.Treatment;
import edu.stevens.cs548.clinic.domain.TreatmentFactory;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceLocal;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;

/**
 * Session Bean implementation class TestBean
 */
@Singleton
@LocalBean
@Startup
public class InitBean {

	private static Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());

	/**
	 * Default constructor.
	 */
	public InitBean() {
	}
	
	public static void info(String m) {
		logger.info(m);
	}

	public void TestService() {

	}

	@Inject
	private IPatientServiceLocal patService;

	@Inject
	private IProviderServiceLocal proService;

	@PostConstruct
	private void init() {
		
		logger.info("Your name here: Shristi Hingle");

		try {
			PatientDtoFactory patientdtoFactory = new PatientDtoFactory();
			PatientFactory patientFactory = new PatientFactory();
			PatientDto patientdto;
			ProviderDtoFactory providerdtoFactory = new ProviderDtoFactory();
			ProviderFactory providerFactory = new ProviderFactory();
			ProviderDto providerdto;
			TreatmentDtoFactory treatmentdtoFactory = new TreatmentDtoFactory();
			TreatmentFactory treatmentFactory = new TreatmentFactory();
			TreatmentDto treatmentDto;

			patService.deletePatients();
			proService.deleteProvider();
			
			Patient john = patientFactory.createPatient(123490678L, "John Doe", setDate("07/01/1991"), 24);
			patientdto = patientdtoFactory.createPatientDto(john, 24);
			long johnId = patService.addPatient(patientdto);
			info("Patient "+ john.getName() + "created with id " + johnId);

			Provider ross = providerFactory.createProvider(222456798L, "Ross Geller", "Radiology");
			providerdto = providerdtoFactory.createProviderDto(ross);
			long rossId = proService.addProvider(providerdto);
			info("Provider " + ross.getName() + "created with id " + rossId);

			Treatment drugTreatment = treatmentFactory.createDrugTreatment("Vomiting", "Domstal", 2);
			treatmentDto = treatmentdtoFactory.createTreatmentDto((DrugTreatment) drugTreatment);
			treatmentDto.setPatient(johnId);
			treatmentDto.setProvider(rossId);
			long drugid = proService.addTreatment(treatmentDto);
			
			logger.info("Provider " + ross.getName() + " is treating patient "
				+ john.getName() +" - Drug Treatment ID : "+drugid);

			Treatment surgeryTreatment = treatmentFactory.createSurgery(setDate("10/10/2015"), "Facture");
			treatmentDto = treatmentdtoFactory.createTreatmentDto((Surgery)surgeryTreatment);
			treatmentDto.setPatient(johnId);
			treatmentDto.setProvider(rossId);
			long surgid = proService.addTreatment(treatmentDto);
			
			logger.info("Provider " + ross.getName() + " is treating patient "
					+ john.getName() +" - Surgery Treatment ID : "+surgid);
			
			List<RadiologyDateValues> radiologydateList = new ArrayList<RadiologyDateValues>();
			for (int i = 1; i < 4; i++) {
				RadiologyDateValues date = new RadiologyDateValues();
				date.setDate(setDate("10/" + i + "/2015"));
				radiologydateList.add(date);
			}
			Treatment radiologyTreatment = treatmentFactory.createRadiology(radiologydateList,"Lung Cancer");
			treatmentDto = treatmentdtoFactory.createTreatmentDto((Radiology)radiologyTreatment);
			treatmentDto.setPatient(johnId);
			treatmentDto.setProvider(rossId);
			long rid = proService.addTreatment(treatmentDto);
			
			logger.info("Provider " + ross.getName() + " is treating patient "
					+ john.getName() +" - Radiology Treatment ID : "+rid);
			
		} catch (Exception e) {

			IllegalStateException ex = new IllegalStateException("Failed to add patient record.");
			ex.initCause(e);
			throw ex;
			
		} 
	}
	private Date setDate(String stringDate) {
		Date date = null;
		try {
			String pattern = "MM/dd/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(stringDate);
		} catch (Exception e) {
			IllegalStateException ex = new IllegalStateException("Error while setting state");
			ex.initCause(e);
			throw ex;
		}
		return date;
	}

}
