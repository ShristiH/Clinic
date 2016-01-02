package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class PatientDAO implements IPatientDAO {

	private EntityManager em;
	private TreatmentDAO treatmentDAO;
	
	public PatientDAO(EntityManager em) {
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PatientDAO.class.getCanonicalName());

	@Override
	public long addPatient(Patient patient) throws PatientExn {
		long pid = patient.getPatientId();
		Query query = em.createNamedQuery("CountPatientByPatientID").setParameter("pid", pid);
		Long numExisting = (Long) query.getSingleResult();
		if (numExisting < 1) {
			// TODO add to database (and sync with database to generate primary key)
			em.persist(patient);
			patient.setTreatmentDAO(this.treatmentDAO);
			return patient.getId();
			// Don't forget to initialize the patient aggregate with a treatment DAO
			
			//throw new IllegalStateException("Unimplemented");
			
		} else {
			throw new PatientExn("Insertion: Patient with patient id (" + pid + ") already exists.");
		}
	}

	@Override
	public Patient getPatient(long id) throws PatientExn {
		// TODO retrieve patient using primary key
		Patient p = em.find(Patient.class, id);
		if(p==null)
		{
			throw new PatientExn("Patient not found: primary key = "+id);
		}
		else
		{
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
	
	}

	@Override
	public Patient getPatientByPatientId(long pid) throws PatientExn {
		// TODO retrieve patient using query on patient id (secondary key)
		TypedQuery<Patient>query = 
				em.createNamedQuery("SearchPatientByPatientID", Patient.class)
				.setParameter("pid", pid);
		List<Patient> patients = query.getResultList();
		if(patients.size() > 1)
		{
			throw new PatientExn("Duplicate Patient record: patient id = "+ pid);
		}
		else if(patients.size() < 1 )
		{
			throw new PatientExn("Patient not found: patient id = "+ pid);
		}
		else
		{
			Patient p = patients.get(0);
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
			
	}
	
	@Override
	public void deletePatients(){
//		em.createQuery("delete t from Treatment t where t.patient.id = :id")
//		.setParameter("id", patient.getPatientId())
//		.executeUpdate();
		Query update = em.createNamedQuery("RemoveAllPatients");
		em.createQuery("Delete from Treatment t").executeUpdate();
		update.executeUpdate();
	}

	@Override
	public List<Patient> getPatientByNameDob(String name, Date dob) {
		// TODO Auto-generated method stub
		TypedQuery<Patient> query =
				em.createNamedQuery("SearchPatientByNameDOB", Patient.class)
				.setParameter("name", name)
				.setParameter("dob", dob);
		List<Patient> patients = query.getResultList();
		for(Patient p:patients)
		{
			p.setTreatmentDAO(this.treatmentDAO);
		}
		return patients;
	}

}
