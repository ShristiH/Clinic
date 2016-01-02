package edu.stevens.cs548.clinic.service.web.rest.resources;

import java.net.URI;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceRemote;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;
import edu.stevens.cs548.clinic.service.representations.PatientRepresentation;
import edu.stevens.cs548.clinic.service.representations.ProviderRepresentation;
import edu.stevens.cs548.clinic.service.representations.Representation;
import edu.stevens.cs548.clinic.service.representations.TreatmentRepresentation;


@Path("/provider")
@RequestScoped
public class ProviderResource {
	
    @SuppressWarnings("unused")
    
    final static Logger logger = Logger.getLogger(ProviderResource.class.getCanonicalName());
    
    @Context
    private UriInfo uriInfo;
	
	public class ProviderRecordNotFound extends WebApplicationException {
		private static final long serialVersionUID = 1L;
		public ProviderRecordNotFound(String message) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity(message).type(Representation.MEDIA_TYPE).build());
	     }
	}
	public class ProviderRecordNotCreated extends WebApplicationException{
		private static final long serialVersionUID = 1L;
		public ProviderRecordNotCreated(String message) {
	         super(Response.status(Response.Status.BAD_REQUEST)
	             .entity(message).type(Representation.MEDIA_TYPE).build());
		}
	}
	


    private ProviderDtoFactory providerDtoFactory;
	private TreatmentDtoFactory treatmentDtoFactory;

	/**
	 * Default constructor.
	 */
	public ProviderResource() {
		providerDtoFactory = new ProviderDtoFactory();
		treatmentDtoFactory = new TreatmentDtoFactory();
	}
    
    @Inject
    private IProviderServiceLocal providerService;

    /**
     * Retrieves representation of an instance of ProviderResource
     * @return an instance of String
     */
    @GET
    @Path("{id}")
    @Produces("application/xml")
	public ProviderRepresentation getProvider(@PathParam("id") String id) {
		try {
			long key = Long.parseLong(id);
			ProviderDto providerDto = providerService.getProvider(key);
			ProviderRepresentation providerRep = new ProviderRepresentation(providerDto, uriInfo);
			return providerRep;
		} catch (ProviderServiceExn e) {
			throw new ProviderRecordNotFound("No Provider Found") ;
		}
	}
    
    @GET
    @Path("byNPI")
    @Produces("application/xml")
	public ProviderRepresentation getProviderByProviderId(@QueryParam("id") String providerId) {
		try {

			long pid = Long.parseLong(providerId);
			ProviderDto providerDto = providerService.getProviderByProId(pid);
			ProviderRepresentation providerRep = new ProviderRepresentation(providerDto, uriInfo);
			return providerRep;
		} catch (ProviderServiceExn e) {
			throw new ProviderRecordNotFound("No Provider Found") ;
		}
	}
    
    @POST
	@Path("{id}/treatments")
	@Consumes("application/xml")
	public Response addTreatments(TreatmentRepresentation treatRep,
								  @PathParam("id") String providerId,
								  @HeaderParam("X-Patient") String patientURI
								 ) {
		try {
			long id = Long.parseLong(providerId);
			TreatmentDto treatDto = null;
			if (treatRep.getDrugTreatment() != null) {
				treatDto = treatmentDtoFactory.createDrugTreatmentDto();
				treatDto.setDiagnosis(treatRep.getDiagnosis());
//				treatDto.setId(Representation.getId(treatRep.getId()));
//				treatDto.setPatient(Representation.getId(treatRep.getPatient()));
				treatRep.setProvider(ProviderRepresentation.getProviderLink(id, uriInfo));
				treatDto.setProvider(id);
				logger.info("long id : " + id);
				logger.info("PROVIDER ID : " + treatDto.getProvider());
				treatDto.setPatient(Representation.getId(treatRep.getLinkPatient()));
				DrugTreatmentType drugDto = new DrugTreatmentType();
				drugDto.setDosage(treatRep.getDrugTreatment().getDosage());
				drugDto.setName(treatRep.getDrugTreatment().getName());
				treatDto.setDrugTreatment(drugDto);
			}
			if (treatRep.getSurgery() != null) {
				treatDto = treatmentDtoFactory.createsurgeryTreatmentDto();
				treatDto.setDiagnosis(treatRep.getDiagnosis());
//				treatDto.setId(Representation.getId(treatRep.getId()));
//				treatDto.setPatient(Representation.getId(treatRep.getPatient()));
				treatRep.setProvider(ProviderRepresentation.getProviderLink(id, uriInfo));
				treatDto.setProvider(id);
				treatDto.setPatient(Representation.getId(treatRep.getLinkPatient()));
				SurgeryType surgeryDto = new SurgeryType(); 
				surgeryDto.setDate(treatRep.getSurgery().getDate());
				treatDto.setSurgery(surgeryDto);
			}
			if (treatRep.getRadiology() != null) {
				treatDto = treatmentDtoFactory.createRadiologyTreatmentDto();
				treatDto.setDiagnosis(treatRep.getDiagnosis());
//				treatDto.setId(Representation.getId(treatRep.getId()));
//				treatDto.setPatient(Representation.getId(treatRep.getPatient()));
				treatRep.setProvider(ProviderRepresentation.getProviderLink(id, uriInfo));
				treatDto.setProvider(id);
				treatDto.setPatient(Representation.getId(treatRep.getLinkPatient()));
				RadiologyType radiologyDto = new RadiologyType();
				radiologyDto.getDate().addAll(treatRep.getRadiology().getDate());
				treatDto.setRadiology(radiologyDto);
			}
			long tid = providerService.addTreatment(treatDto);
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("{tid}");
			URI url = ub.build(Long.toString(tid));
			return Response.created(url).build();
		} catch (ProviderServiceExn e) {
			throw new ProviderRecordNotCreated("Unable to add Treatment");
		}
	}
    /**
     * PUT method for updating or creating an instance of ProviderResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    @GET
    @Path("site")
    @Produces("text/plain")
    public String getSiteInfo() {
    	return providerService.siteInfoPro();
    }
    
    @POST
    @Consumes("application/xml")
    public Response addProvider(ProviderRepresentation providerRep) {
    	try {
    		ProviderDto dto = providerDtoFactory.createProviderDto();
    		dto.setProviderId(providerRep.getProviderId());
    		dto.setName(providerRep.getName());
    		dto.setSpecialization(providerRep.getSpecialization());
    		long id = providerService.addProvider(dto);
    		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("{id}");
    		URI url = ub.build(Long.toString(id));
    		return Response.created(url).build();
    	} catch (ProviderServiceExn e) {
    		throw new ProviderRecordNotCreated("Unable to add Provider");
    	}
    }

}