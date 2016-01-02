package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.IProviderFactory;
import edu.stevens.cs548.clinic.domain.ITreatmentExporter;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;

/**
 * Session Bean implementation class PatientService
 */
@Stateless(name = "ProviderServiceBean")
public class ProviderService implements IProviderServiceLocal, IProviderServiceRemote {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ProviderService.class.getCanonicalName());

	private IProviderFactory providerFactory;

	private ProviderDtoFactory providerDtoFactory;

	private IProviderDAO providerDAO;

	private IPatientDAO patientDAO;

	public ProviderService() {
		providerFactory = new ProviderFactory();
		providerDtoFactory = new ProviderDtoFactory();
	}

	@Inject
	@ClinicDomain
	private EntityManager em;

	@PostConstruct
	private void initialize() {
		providerDAO = new ProviderDAO(em);
		patientDAO = new PatientDAO(em);
	}

	@Override
	public long addProvider(ProviderDto dto) throws ProviderServiceExn {
		try {
			Provider provider = providerFactory.createProvider(dto.getProviderId(), dto.getName(),
					dto.getSpecialization());
			providerDAO.addProvider(provider);
			return provider.getId();
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
	}

	@Override
	public ProviderDto getProvider(long id) throws ProviderServiceExn {
		try {
			Provider provider = providerDAO.getProvider(id);
			ProviderDto dto = providerDtoFactory.createProviderDto(provider);
			return dto;

		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}

	}

	@Override
	public ProviderDto getProviderByProId(long pid) throws ProviderServiceExn {
		try {
			Provider provider = providerDAO.getProviderById(pid);
			ProviderDto dto = providerDtoFactory.createProviderDto(provider);
			return dto;

		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
	}

	public class TreatmentExporter implements ITreatmentExporter<TreatmentDto> {

		private ObjectFactory factory = new ObjectFactory();

		@Override
		public TreatmentDto exportDrugTreatment(long tid, String diagnosis, String drug, float dosage) {
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
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			RadiologyType radiologyInfo = factory.createRadiologyType();
			for (Date radiologyDate : dates) {
				radiologyInfo.getDate().add(radiologyDate);
			}
			dto.setRadiology(radiologyInfo);
			return dto;
		}

		@Override
		public TreatmentDto exportSurgery(long tid, String diagnosis, Date date) {
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			SurgeryType surgeryInfo = factory.createSurgeryType();
			surgeryInfo.setDate(date);
			dto.setSurgery(surgeryInfo);
			return dto;

		}

	}

	@Override
	public long addTreatment(TreatmentDto tdto) throws ProviderServiceExn {
		long tid = 0;
		try {
			logger.info("PROVIDER ID : " + tdto.getProvider());
			logger.info("PATIENT ID : " + tdto.getPatient());
			Provider provider = providerDAO.getProvider(tdto.getProvider());
			Patient patient = patientDAO.getPatient(tdto.getPatient());
			if (tdto.getDrugTreatment() != null) {
				logger.info("INSIDE IF");
				tid = provider.addDrugTreatment(tdto.getDiagnosis(), tdto.getDrugTreatment().getName(),
						tdto.getDrugTreatment().getDosage(), patient);
				logger.info("AFTER INSIDE IF");
			} else if (tdto.getRadiology() != null) {
				tid = provider.addRadiology(tdto.getRadiology().getDate(), patient, tdto.getDiagnosis());
			} else if (tdto.getSurgery() != null) {
				tid = provider.addSurgery(tdto.getDiagnosis(), tdto.getSurgery().getDate(), patient);
			}

		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		} catch (PatientExn e) {
			throw new ProviderServiceExn(e.toString());
		}
		return tid;
	}

	@Resource(name = "SiteInfoPro")
	private String siteInformation;

	@Override
	public String siteInfoPro() {
		return siteInformation;
	}

	@Override
	public void deleteProvider() {
		providerDAO.deleteProviders();

	}

}
