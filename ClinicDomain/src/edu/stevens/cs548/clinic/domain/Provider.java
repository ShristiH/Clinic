package edu.stevens.cs548.clinic.domain;

import static javax.persistence.CascadeType.REMOVE;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.*;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

/**
 * Entity implementation class for Entity: Provider
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SearchProviderByProviderID", query = "select p from Provider p where p.providerId = :providerId"),
		@NamedQuery(name = "CountProviderByProviderID", query = "select count(p) from Provider p where p.providerId = :providerId"),
		@NamedQuery(name = "RemoveAllProviders", query = "delete from Provider p") })
@Table(name = "PROVIDER")

public class Provider implements Serializable {

	private static Logger logger = Logger.getLogger(Provider.class.getCanonicalName());
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	public long id;
	public long providerId;
	public String name;
	public String specialization;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@OneToMany(mappedBy = "provider", cascade = REMOVE)
	@OrderBy
	private List<Treatment> treatments;

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}

	public Provider() {
		super();
	}

	@Transient
	private ITreatmentDAO treatmentDAO;

	public void setTreatmentDAO(ITreatmentDAO tdao) {
		this.treatmentDAO = tdao;
	}

	public <T> T exportTreatment(long tid, ITreatmentExporter<T> visitor) throws TreatmentExn {
		// Export a treatment without violated Aggregate pattern
		// Check that the exported treatment is a treatment for this patient.
		Treatment t = treatmentDAO.getTreatment(tid);
		if (t.getProvider() != this) {
			throw new TreatmentExn("Inappropriate treatment access: provider = " + id + ", treatment = " + tid);
		}
		return t.export(visitor);
	}

	public <T> void visitTreatments(ITreatmentExporter<T> visitor) {
		for (Treatment t : this.getTreatments()) {
			t.export(visitor);
		}
	}

	public long addTreatment(Treatment t) {
		// Persist treatment and set forward and backward links
		logger.info("Inside drug TReatment : addTreatment");
		this.getTreatments().add(t);
		if (t.getProvider() != this) {
			t.setProvider(this);
		}
		return t.getId();
	}

	public long addDrugTreatment(String diagnosis, String drug, float dosage, Patient patient) {
		logger.info("Inside drug TReatment");
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDosage(dosage);
		treatment.setDrug(drug);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();
	}

	public long addSurgery(String diagnosis, Date date, Patient patient) {
		Surgery treatment = new Surgery();
		treatment.setDiagnosis(diagnosis);
		treatment.setSurgeryDate(date);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();
	}

	public long addRadiology(List<Date> radiologydates, Patient patient, String diagnosis) {
		Radiology treatment = new Radiology();
		for (Date date : radiologydates) {
			RadiologyDateValues rdv = new RadiologyDateValues();
			rdv.setDate(date);
			treatment.getRadiologydates().add(rdv);

		}
		treatment.setDiagnosis(diagnosis);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();

	}

}
