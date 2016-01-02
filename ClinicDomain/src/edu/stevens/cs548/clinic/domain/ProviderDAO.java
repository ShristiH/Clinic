package edu.stevens.cs548.clinic.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;

public class ProviderDAO implements IProviderDAO {

	private EntityManager em;
	@Override
	public long addProvider(Provider pro) throws ProviderExn {
		// TODO Auto-generated method stub
		long pid = pro.getProviderId();
		Query query = em.createNamedQuery("CountProviderByProviderID").setParameter("providerId", pid);
		Long numExisting = (Long) query.getSingleResult();
		if (numExisting < 1) {
			// TODO add to database (and sync with database to generate primary key)
			em.persist(pro);
			return pro.getId();
			// Don't forget to initialize the patient aggregate with a treatment DAO
			
			//throw new IllegalStateException("Unimplemented");
			
		} else {
			throw new ProviderExn("Insertion: Provider with provider id (" + pid + ") already exists.");
		}
	}

	@Override
	public Provider getProviderById(long providerID) throws ProviderExn {
		// TODO Auto-generated method stub
		TypedQuery<Provider>query = 
				em.createNamedQuery("SearchProviderByProviderID", Provider.class)
				.setParameter("providerId", providerID);
		List<Provider> providers = query.getResultList();
		if(providers.size() > 1)
		{
			throw new ProviderExn("Duplicate Provider record: provider id = "+ providerID);
		}
		else if(providers.size() < 1 )
		{
			throw new ProviderExn("Provider not found: provider id = "+ providerID);
		}
		else
			return providers.get(0);
	}

	@Override
	public Provider getProvider(long id) throws ProviderExn {
		// TODO Auto-generated method stub
		Provider p = em.find(Provider.class, id);
		if(p==null)
		{
			throw new ProviderExn("Provider not found: primary key = "+id);
		}
		else
		{
			return p;
		}
	}

	@Override
	public void deleteProviders() {
		Query update = em.createNamedQuery("RemoveAllProviders");
		update.executeUpdate();

	}
	public ProviderDAO (EntityManager em)
	{
		this.em = em;
	}

}
