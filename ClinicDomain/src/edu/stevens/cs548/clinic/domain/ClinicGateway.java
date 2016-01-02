package edu.stevens.cs548.clinic.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClinicGateway implements IClinicGateway {

	@Override
	public IPatientFactory getPatientFactory() {
		// TODO Auto-generated method stub
		return new PatientFactory();
	}

	private EntityManagerFactory emf;
	@Override
	public IPatientDAO getPatientDAO() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		return new PatientDAO(em);
	}

	@Override
	public IProviderFactory getProviderFactory() {
		// TODO Auto-generated method stub
		return new ProviderFactory();
	}

	@Override
	public IProviderDAO getProviderDAO() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		return null;
	}
	
	public ClinicGateway()
	{
		emf = Persistence.createEntityManagerFactory("ClinicDomain");
	}
}
