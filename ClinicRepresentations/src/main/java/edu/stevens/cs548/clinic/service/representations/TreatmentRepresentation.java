package edu.stevens.cs548.clinic.service.representations;

import java.util.Date;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import edu.stevens.cs548.clinic.service.web.rest.data.ObjectFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.RadiologyType;
import edu.stevens.cs548.clinic.service.web.rest.data.SurgeryType;
import edu.stevens.cs548.clinic.service.web.rest.data.TreatmentType;
import edu.stevens.cs548.clinic.service.web.rest.data.dap.LinkType;
import edu.stevens.cs548.clinic.domain.Radiology;
import edu.stevens.cs548.clinic.domain.RadiologyDateValues;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.DrugTreatmentType;


@XmlRootElement
public class TreatmentRepresentation extends TreatmentType {
	
	private ObjectFactory repFactory = new ObjectFactory();
	
	public LinkType getLinkPatient() {
		return this.getPatient();
	}
	
	public LinkType getLinkProvider() {
		return this.getProvider();
	}
	
	public static LinkType getTreatmentLink(long tid, UriInfo uriInfo) {
		UriBuilder ub = uriInfo.getBaseUriBuilder();
		ub.path("treatment");
		UriBuilder ubTreatment = ub.clone().path("{tid}");
		String treatmentURI = ubTreatment.build(Long.toString(tid)).toString();
	
		LinkType link = new LinkType();
		link.setUrl(treatmentURI);
		link.setRelation(Representation.RELATION_TREATMENT);
		link.setMediaType(Representation.MEDIA_TYPE);
		return link;
	}
	
	private TreatmentDtoFactory treatmentDtoFactory;
	
	public TreatmentRepresentation() {
		super();
		treatmentDtoFactory = new TreatmentDtoFactory();
	}
	
	public TreatmentRepresentation (TreatmentDto dto, UriInfo uriInfo) {
		this();
		this.id = getTreatmentLink(dto.getId(), uriInfo);
		this.patient =  PatientRepresentation.getPatientLink(dto.getPatient(), uriInfo);
		this.provider = ProviderRepresentation.getProviderLink(dto.getProvider(), uriInfo);
		this.diagnosis = dto.getDiagnosis();
		
		if (dto.getDrugTreatment() != null) {
			DrugTreatmentType d = new DrugTreatmentType();
			d.setDosage(dto.getDrugTreatment().getDosage());
			d.setName(dto.getDrugTreatment().getName());
			this.setDrugTreatment(d);
//			this.drugTreatment.setDosage(dto.getDrugTreatment().getDosage());
//			this.drugTreatment.setName(dto.getDrugTreatment().getName());
		} else if (dto.getSurgery() != null) {
			SurgeryType s = new SurgeryType();
			s.setDate(dto.getSurgery().getDate());
			//this.surgery.setDate(dto.getSurgery().getDate());
			this.setSurgery(s);
			
		} else if (dto.getRadiology() != null) {
			
			RadiologyType r = new RadiologyType();
			r.getDate().addAll(dto.getRadiology().getDate());
			//this.radiology.getDate().addAll(dto.getRadiology().getDate());
			this.setRadiology(r);
		}
	}

	public TreatmentDto getTreatment() {
		TreatmentDto m = null;
		if (this.getDrugTreatment() != null) {
			m = treatmentDtoFactory.createDrugTreatmentDto();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			edu.stevens.cs548.clinic.service.dto.DrugTreatmentType d = new edu.stevens.cs548.clinic.service.dto.DrugTreatmentType();
			d.setName(this.getDrugTreatment().getName());
			d.setDosage(this.getDrugTreatment().getDosage());
			m.setDrugTreatment(d);

		} else if (this.getSurgery() != null) {
			m = treatmentDtoFactory.createsurgeryTreatmentDto();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			edu.stevens.cs548.clinic.service.dto.SurgeryType s = new edu.stevens.cs548.clinic.service.dto.SurgeryType();
			s.setDate(this.getSurgery().getDate());
			m.setSurgery(s);
			
		} else if (this.getRadiology() != null) {

			m = treatmentDtoFactory.createsurgeryTreatmentDto();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			edu.stevens.cs548.clinic.service.dto.RadiologyType r = new edu.stevens.cs548.clinic.service.dto.RadiologyType();
			r.getDate().addAll(this.getRadiology().getDate());
			m.setRadiology(r);
		}
		return m;
	}

	
}
