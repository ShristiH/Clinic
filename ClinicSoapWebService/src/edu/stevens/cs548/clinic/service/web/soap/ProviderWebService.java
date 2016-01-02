package edu.stevens.cs548.clinic.service.web.soap;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;

//Use JSR-181 annotations to specify Web service.
//Specify: endpoint interface (FQN), target namespace, service name, port name

@WebService(
		endpointInterface = "edu.stevens.cs548.clinic.service.web.soap.IProviderWebService", 
		targetNamespace = "http://cs548.stevens.edu/clinic/service/web/soap", 
		serviceName = "ProviderWebService", portName = "ProviderWebPort")

@SOAPBinding(
		style=SOAPBinding.Style.DOCUMENT,
		use=SOAPBinding.Use.LITERAL,
		parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)

public class ProviderWebService implements IProviderWebService {

	// TODO use CDI to inject the service
	@Inject
	IProviderServiceLocal service;
	
	@Override
	public long addProvider(ProviderDto dto) throws ProviderServiceExn {
		// TODO Auto-generated method stub
		return service.addProvider(dto);
	}

	@Override
	public ProviderDto getProvider(long id) throws ProviderServiceExn {
		// TODO Auto-generated method stub
		return service.getProvider(id);
	}

	@Override
	public ProviderDto getProviderByProId(long pid) throws ProviderServiceExn {
		// TODO Auto-generated method stub
		return service.getProviderByProId(pid);
	}

	@Override
	public long addTreatment(TreatmentDto treatmentDto) throws ProviderServiceExn{
		// TODO Auto-generated method stub
		return service.addTreatment(treatmentDto);
	}

	@Override
	public String siteInfo() {
		return service.siteInfoPro();
	}

}
