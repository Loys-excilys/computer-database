package com.excilys.computer.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.CompanyDatabaseDTO;
import com.excilys.computer.database.dto.ComputerDatabaseDTO;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.mappeur.MappeurDate;

@Repository
public class DAOCompany {

	private static final int MAX_ENTRY_PRINT = 25;

	private SessionFactory sessionFactory;

	public DAOCompany(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Company getCompany(String name) {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<CompanyDatabaseDTO> query = cb.createQuery(CompanyDatabaseDTO.class);
		Root<CompanyDatabaseDTO> company = query.from(CompanyDatabaseDTO.class);

		query.select(company).where(cb.equal(company.get("name"), name));

		CompanyDatabaseDTO result = em.createQuery(query).getSingleResult();
		em.close();
		
		return new MapperCompany().companyDatabaseDTOToCompany(result);
	}

	public List<Company> getListCompany(int page) {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<CompanyDatabaseDTO> query = cb.createQuery(CompanyDatabaseDTO.class);
		Root<CompanyDatabaseDTO> computer = query.from(CompanyDatabaseDTO.class);

		query.select(computer);

		List<CompanyDatabaseDTO> resultDTO = em.createQuery(query).setFirstResult(MAX_ENTRY_PRINT * page)
				.setMaxResults(MAX_ENTRY_PRINT).getResultList();
		em.close();
		List<Company> result = new ArrayList<>();
		for(int i = 0; i < resultDTO.size(); i++) {
			result.add(new MapperCompany().companyDatabaseDTOToCompany(resultDTO.get(i)));
		}
		return result;
	}

	public List<Company> getListCompany() {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<CompanyDatabaseDTO> query = cb.createQuery(CompanyDatabaseDTO.class);
		Root<CompanyDatabaseDTO> computer = query.from(CompanyDatabaseDTO.class);

		query.select(computer);

		List<CompanyDatabaseDTO> resultDTO = em.createQuery(query).getResultList();
		em.close();
		List<Company> result = new ArrayList<>();
		for(int i = 0; i < resultDTO.size(); i++) {
			result.add(new MapperCompany().companyDatabaseDTOToCompany(resultDTO.get(i)));
		}
		return result;
	}
	
	public void insertCompany(Company company) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(new MapperCompany().companyToCompanyDatabaseDTO(company));
		tx.commit();
		session.close();
		
	}

	public void updateCompany(Company company) {
		CompanyDatabaseDTO companyDTO = new MapperCompany().companyToCompanyDatabaseDTO(company);
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<CompanyDatabaseDTO> query = cb.createCriteriaUpdate(CompanyDatabaseDTO.class);
		Root<CompanyDatabaseDTO> rootComputer = query.from(CompanyDatabaseDTO.class);
		
		query.set("name", companyDTO.getName());
		query.where(cb.equal(rootComputer.get("id"), companyDTO.getId()));
		
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void deleteCompanyById(int id) {
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaDelete<ComputerDatabaseDTO> queryComputer = cb.createCriteriaDelete(ComputerDatabaseDTO.class);
		Root<ComputerDatabaseDTO> rootComputer = queryComputer.from(ComputerDatabaseDTO.class);
		queryComputer.where(cb.equal(rootComputer.get("company"), id));
		
		CriteriaDelete<CompanyDatabaseDTO> queryCompany = cb.createCriteriaDelete(CompanyDatabaseDTO.class);
		Root<CompanyDatabaseDTO> rootCompany = queryCompany.from(CompanyDatabaseDTO.class);
		queryCompany.where(cb.equal(rootCompany.get("id"), id));
		
		em.createQuery(queryComputer).executeUpdate();
		em.createQuery(queryCompany).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}
