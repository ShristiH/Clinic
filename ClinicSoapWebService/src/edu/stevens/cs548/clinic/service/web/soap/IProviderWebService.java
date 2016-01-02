package edu.stevens.cs548.clinic.service.web.soap;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.TreatmentNotFoundExn;

@WebService(
		name="IProviderWebPort",
		targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap")
	/*
	 * Endpoint interface for the provider Web service.
	 */
@SOAPBinding(
		style=SOAPBinding.Style.DOCUMENT,
		use=SOAPBinding.Use.LITERAL,
		parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public interface IProviderWebService {

		public long addProvider( @WebParam(name="provider-dto",
		          targetNamespace="http://cs548.stevens.edu/clinic/dto")
		ProviderDto dto) throws ProviderServiceExn;

		@WebMethod
		@WebResult(name="provider-dto",
				   targetNamespace="http://cs548.stevens.edu/clinic/dto")
		public ProviderDto getProvider(long id) throws ProviderServiceExn;

		@WebMethod
		@WebResult(name="provider-dto",
				   targetNamespace="http://cs548.stevens.edu/clinic/dto")
		public ProviderDto getProviderByProId(long pid) throws ProviderServiceExn;

		@WebMethod(operationName="providerAddTreatment")
		public long addTreatment( 
				  @WebParam(name="treatment-dto",
		          targetNamespace="http://cs548.stevens.edu/clinic/dto")TreatmentDto treatmentDto)  throws ProviderServiceExn;

		@WebMethod
		public String siteInfo();
		

}
