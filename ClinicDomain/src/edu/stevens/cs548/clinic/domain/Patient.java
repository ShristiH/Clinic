package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

/**
 * Entity implementation class for Entity: Patient
 *
 */
/*
 * TODO
 */

@NamedQueries({
	@NamedQuery(
		name="SearchPatientByPatientID",
		query="select p from Patient p where p.patientId = :pid"),
	@NamedQuery(
		name="CountPatientByPatientID",
		query="select count(p) from Patient p where p.patientId = :pid"),
	@NamedQuery(
		name = "RemoveAllPatients", 
		query = "delete from Patient p"),
	@NamedQuery(
		name = "SearchPatientByNameDOB",
		query = "select p from Patient p where p.name = :name and p.birthDate = :dob")
	
})

/*
 * TODO
 */
@Entity
@Table(name="PATIENT")
public class Patient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// TODO JPA annotations
	@Id
	@GeneratedValue
	private long id;
	
	private long patientId;
	
	private String name;
	
	// TODO JPA annotation
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
	// TODO JPA annotations (propagate deletion of patient to treatments)
	@OneToMany(mappedBy = "patient", cascade = REMOVE)
	@OrderBy
	private List<Treatment> treatments;

	protected List<Treatment> getTreatments() {
		return treatments;
	}

	protected void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public void setTreatmentDAO (ITreatmentDAO tdao) {
		this.treatmentDAO = tdao;
	}
	
	public long addTreatment (Treatment t) {
		// Persist treatment and set forward and backward links
		this.treatmentDAO.addTreatment(t);
		this.getTreatments().add(t);
		if (t.getPatient() != this) {
			t.setPatient(this);
		}
		return t.getId();
	}
	
	public List<Long> getTreatmentIds() {
		List<Long> tIdList = new ArrayList<Long>();
		for (Treatment t : this.getTreatments()) {
			tIdList.add(t.getId());
		}
		return tIdList;
	}
	
	
	
	public <T> T exportTreatment(long tid, ITreatmentExporter<T> visitor) throws TreatmentExn {
		// Export a treatment without violated Aggregate pattern
		// Check that the exported treatment is a treatment for this patient.
		Treatment t = treatmentDAO.getTreatment(tid);
		if (t.getPatient() != this) {
			throw new TreatmentExn("Inappropriate treatment access: patient = " + id + ", treatment = " + tid);
		}
		return t.export(visitor);
	}
	
	public void addDrugTreatment(String diagnosis, String drug, float dosage, Provider provider)
	{
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDosage(dosage);
		treatment.setDrug(drug);
		treatment.setProvider(provider);
		this.addTreatment(treatment);
	}
	
	public void addSurgery(String diagnosis, Date date, Provider provider)
	{
		Surgery treatment = new Surgery();
		treatment.setDiagnosis(diagnosis);
		treatment.setSurgeryDate(date);
		treatment.setProvider(provider);
		this.addTreatment(treatment);
	}
	
	public void addRadiology(List<Date> radiologydates, Provider provider, String diagnosis)
	{
		Radiology treatment = new Radiology();
		for(Date date: radiologydates)
		{
			RadiologyDateValues rdv = new RadiologyDateValues();
			rdv.setDate(date);
			treatment.getRadiologydates().add(rdv);
		
		}
		treatment.setDiagnosis(diagnosis);
		treatment.setProvider(provider);
		this.addTreatment(treatment);
		
	}

	
	public <T> void visitTreatments(ITreatmentExporter<T> visitor)
	{
		for(Treatment t: this.getTreatments())
		{
			t.export(visitor);
		}
	}
	
	public void deleteTreatment(long tid) throws TreatmentExn
	{
		Treatment t = treatmentDAO.getTreatment(tid);
		if (t.getPatient() != this) {
			throw new TreatmentExn("Inappropriate treatment access: patient = " + id + ", treatment = " + tid);
		}
		treatmentDAO.deleteTreatment(t);
	}
	
	public Patient() {
		super();
		treatments = new ArrayList<Treatment>();
		/*
		 * TODO initialize lists
		 */
	}
   
}
