package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;

public class ProviderFactory implements IProviderFactory {

	@Override
	public Provider createProvider(long providerID, String name, String specialization) throws ProviderExn {
		// TODO Auto-generated method stub
		Provider p = new Provider();
		p.setProviderId(providerID);
		p.setName(name);
		p.setSpecialization(specialization);
		return p;
	}

}
