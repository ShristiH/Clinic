package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IPatientFactory;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.ITreatmentExporter;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;

/**
 * Session Bean implementation class PatientService
 */
@Stateless(name="PatientServiceBean")
public class PatientService implements IPatientServiceLocal,
		IPatientServiceRemote {
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PatientService.class.getCanonicalName());

	private IPatientFactory patientFactory;
	//private PatientFactory patientFactory; 
	
	private PatientDtoFactory patientDtoFactory;

	private IPatientDAO patientDAO;

	/**
	 * Default constructor.
	 */

	public PatientService() {
		// TODO initialize factories
		patientFactory = new PatientFactory();
		patientDtoFactory = new PatientDtoFactory();
	}
	

	@Inject @ClinicDomain
	private EntityManager em;

	@PostConstruct
	private void initialize(){
		patientDAO = new PatientDAO(em);
		logger.info("Inside post contruct : "+em);
	}

	/**
	 * @see IPatientService#addPatient(String, Date, long)
	 */
	@Override
	public long addPatient(PatientDto dto) throws PatientServiceExn {
		// Use factory to create patient entity, and persist with DAO
		try {
			Patient patient = patientFactory.createPatient(dto.getPatientId(), dto.getName(), dto.getDob(), dto.getAge());
			patientDAO.addPatient(patient);
			return patient.getId();
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#getPatient(long)
	 */
	@Override
	public PatientDto getPatient(long id) throws PatientServiceExn {
		// TODO use DAO to get patient by database key
		try{
			Patient patient = patientDAO.getPatient(id);
			PatientDto dto = patientDtoFactory.createPatientDto(patient);
			return dto;
				
		}catch (PatientExn e)
		{
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#getPatientByPatId(long)
	 */
	@Override
	public PatientDto getPatientByPatId(long pid) throws PatientServiceExn {
		// TODO use DAO to get patient by patient id
		try{
			Patient patient = patientDAO.getPatientByPatientId(pid);
			PatientDto dto = patientDtoFactory.createPatientDto(patient);
			return dto;
			
			
		}catch (PatientExn e)
		{
			throw new PatientServiceExn(e.toString());
		}
	}
	

	public class TreatmentExporter implements ITreatmentExporter<TreatmentDto> {
		
		private ObjectFactory factory = new ObjectFactory();
		
		@Override
		public TreatmentDto exportDrugTreatment(long tid, String diagnosis, String drug,
				float dosage) {
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			DrugTreatmentType drugInfo = factory.createDrugTreatmentType();
			drugInfo.setDosage(dosage);
			drugInfo.setName(drug);
			dto.setDrugTreatment(drugInfo);
			return dto;
		}

		@Override
		public TreatmentDto exportRadiology(long tid, String diagnosis, List<Date> dates) {
			// TODO Auto-generated method stub	
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			RadiologyType radiologyInfo = factory.createRadiologyType();
			for(Date radiologyDate: dates) {
				radiologyInfo.getDate().add(radiologyDate);
			}
			dto.setRadiology(radiologyInfo);
			return dto;
		}

		@Override
		public TreatmentDto exportSurgery(long tid, String diagnosis, Date date) {
			// TODO Auto-generated method stub	
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			SurgeryType surgeryInfo = factory.createSurgeryType();
			surgeryInfo.setDate(date);
			dto.setSurgery(surgeryInfo);
			return dto;

		}
		
	}
	
	@Override
	public TreatmentDto getTreatment(long id, long tid)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		// Export treatment DTO from patient aggregate
		try {
			Patient patient = patientDAO.getPatient(id);
			TreatmentExporter visitor = new TreatmentExporter();
			return patient.exportTreatment(tid, visitor);
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		} catch (TreatmentExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	@Resource(name="SiteInfo")
	private String siteInformation;
	

	@Override
	public String siteInfo() {
		return siteInformation;
	}

	@Override
	public void deletePatients() {
		logger.info("Inside delete Patient");
		patientDAO.deletePatients();
		logger.info("after delete");
		
	}

}
