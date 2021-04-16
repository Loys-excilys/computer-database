package com.excilys.computer.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.dto.CompanyDatabaseDTO;
import com.excilys.computer.database.dto.ComputerDatabaseDTO;
import com.excilys.computer.database.error.ErrorDAOCompany;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;

@Repository
public class DAOCompany {

	private SessionFactory sessionFactory;

	public DAOCompany(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Company getCompany(String name) throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<CompanyDatabaseDTO> query = cb.createQuery(CompanyDatabaseDTO.class);
		Root<CompanyDatabaseDTO> company = query.from(CompanyDatabaseDTO.class);

		query.select(company).where(cb.equal(company.get("name"), name));
		CompanyDatabaseDTO result;
		try {
			result = em.createQuery(query).getSingleResult();
		}catch (NoResultException | NonUniqueResultException errorResult){
			new ErrorDAOCompany(this.getClass()).idInvalid(errorResult);
			throw new ErrorSaisieUser(this.getClass());
		}
		
		em.close();
		
		return new MapperCompany().companyDatabaseDTOToCompany(result);
	}

	public List<Company> getListCompany(Page page) {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<CompanyDatabaseDTO> query = cb.createQuery(CompanyDatabaseDTO.class);
		Root<CompanyDatabaseDTO> computer = query.from(CompanyDatabaseDTO.class);

		query.select(computer);

		List<CompanyDatabaseDTO> resultDTO = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		List<Company> result = new ArrayList<>();
		for(int i = 0; i < resultDTO.size(); i++) {
			try {
				result.add(new MapperCompany().companyDatabaseDTOToCompany(resultDTO.get(i)));
			} catch (ErrorSaisieUser e) {
				e.databaseCorrupt();
			}
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
			try {
				result.add(new MapperCompany().companyDatabaseDTOToCompany(resultDTO.get(i)));
			} catch (ErrorSaisieUser e) {
				e.databaseCorrupt();
			}
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

	public void updateCompany(Company company) throws ErrorSaisieUser {
		CompanyDatabaseDTO companyDTO = new MapperCompany().companyToCompanyDatabaseDTO(company);
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<CompanyDatabaseDTO> query = cb.createCriteriaUpdate(CompanyDatabaseDTO.class);
		Root<CompanyDatabaseDTO> rootComputer = query.from(CompanyDatabaseDTO.class);
		
		query.set("logo", companyDTO.getLogo());
		query.set("name", companyDTO.getName());
		query.where(cb.equal(rootComputer.get("id"), companyDTO.getId()));
		
		if(em.createQuery(query).executeUpdate() < 1 ) {
			throw new ErrorSaisieUser(this.getClass());
		}
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void deleteCompanyById(int id) throws ErrorSaisieUser {
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
		if (em.createQuery(queryCompany).executeUpdate() == 0) {
			throw new ErrorSaisieUser(this.getClass());
		}
		em.getTransaction().commit();
		em.close();
	}
}
