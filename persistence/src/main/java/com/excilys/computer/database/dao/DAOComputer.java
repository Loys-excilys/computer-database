package com.excilys.computer.database.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MappeurDate;

@Repository
public class DAOComputer {

	private SessionFactory sessionFactory;

	public DAOComputer(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long getNumberComputer() {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Computer> computer = query.from(Computer.class);
		query.select(cb.count(computer));
		long result = em.createQuery(query).getSingleResult();
		em.close();
		return result;
	}

	public long getSearchNumberComputer(String search) {

		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Computer> computer = query.from(Computer.class);

		query.select(cb.count(computer)).where(cb.like(computer.get("name"), "%" + search + "%"));

		long result = em.createQuery(query).getSingleResult();
		em.close();
		return result;
	}

	public Optional<Computer> getComputer(int id) throws ErrorSaisieUser {

		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Computer> query = cb.createQuery(Computer.class);
		Root<Computer> computer = query.from(Computer.class);

		query.select(computer).where(cb.equal(computer.get("id"), id));

		Optional<Computer> result = Optional.ofNullable(em.createQuery(query).getSingleResult());
		em.close();
		return result;
	}

	public List<Computer> getListComputer(Page page) throws ErrorSaisieUser {

		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Computer> query = cb.createQuery(Computer.class);
		Root<Computer> computer = query.from(Computer.class);

		query.select(computer);
		
		List<Computer> result = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		return result;
	}

	public List<Computer> getSearchComputer(String search, Page page) throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Computer> query = cb.createQuery(Computer.class);
		Root<Computer> computer = query.from(Computer.class);

		query.select(computer).where(cb.like(computer.get("name"), "%" + search + "%"));

		
		List<Computer> result = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		return result;
	}

	public List<Computer> getListComputerOrder(String orderField, String sort, Page page) throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Computer> query = cb.createQuery(Computer.class);
		Root<Computer> computer = query.from(Computer.class);

		query.select(computer);

		if (sort.compareTo("ASC") == 0) {
			query.orderBy(cb.asc(computer.get(orderField)));
		} else if (sort.compareTo("DESC") == 0) {
			query.orderBy(cb.desc(computer.get(orderField)));
		}
		
		List<Computer> result = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		return result;
	}

	public List<Computer> getSearchComputerOrder(String search, String orderField, String sort, Page page)
			throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Computer> query = cb.createQuery(Computer.class);
		Root<Computer> computer = query.from(Computer.class);

		query.select(computer).where(cb.like(computer.get("name"), "%" + search + "%"));
		
		if (sort.compareTo("ASC") == 0) {
			query.orderBy(cb.asc(computer.get(orderField)));
		} else if (sort.compareTo("DESC") == 0) {
			query.orderBy(cb.desc(computer.get(orderField)));
		}

		List<Computer> result = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		return result;
	}

	public void insertComputer(Computer computer) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(computer);
		tx.commit();
		session.close();
		
	}

	public void updateComputer(Computer computer) {
		
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<Computer> query = cb.createCriteriaUpdate(Computer.class);
		Root<Computer> rootComputer = query.from(Computer.class);
		
		query.set("name", computer.getName());
		query.set("introduced", MappeurDate.optionalLocalDateToDate(computer.getIntroduced()));
		query.set("discontinued", MappeurDate.optionalLocalDateToDate(computer.getDiscontinued()));
		query.set("company_id", computer.getCompany().isPresent() ? computer.getCompany().get().getId() : null);
		query.where(cb.equal(rootComputer.get("id"), computer.getId()));
		
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteComputerById(int id) {
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaDelete<Computer> query = cb.createCriteriaDelete(Computer.class);
		Root<Computer> rootComputer = query.from(Computer.class);
		query.where(cb.equal(rootComputer.get("id"), id));
		
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}
