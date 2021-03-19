package com.excilys.computer.database.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;

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

		CriteriaQuery<Company> query = cb.createQuery(Company.class);
		Root<Company> company = query.from(Company.class);

		query.select(company).where(cb.equal(company.get("name"), name));

		return em.createQuery(query).getSingleResult();
	}

	public List<Company> getListCompany(int page) {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Company> query = cb.createQuery(Company.class);
		Root<Company> computer = query.from(Company.class);

		query.select(computer);

		return em.createQuery(query).setFirstResult(MAX_ENTRY_PRINT * page)
				.setMaxResults(MAX_ENTRY_PRINT).getResultList();
	}

	public List<Company> getListCompany() {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Company> query = cb.createQuery(Company.class);
		Root<Company> computer = query.from(Company.class);

		query.select(computer);

		return em.createQuery(query).getResultList();
	}
	
	public void deleteCompanyById(int id) {
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaDelete<Computer> queryComputer = cb.createCriteriaDelete(Computer.class);
		Root<Computer> rootComputer = queryComputer.from(Computer.class);
		queryComputer.where(cb.equal(rootComputer.get("company_id"), id));
		
		CriteriaDelete<Company> queryCompany = cb.createCriteriaDelete(Company.class);
		Root<Company> rootCompany = queryCompany.from(Company.class);
		queryCompany.where(cb.equal(rootCompany.get("id"), id));
		
		em.createQuery(queryComputer).executeUpdate();
		em.createQuery(queryCompany).executeUpdate();
		em.getTransaction().commit();
		em.close();

	}
}
