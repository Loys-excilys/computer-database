package com.excilys.computer.database.dao;

import java.util.ArrayList;
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
import com.excilys.computer.database.dto.ComputerDatabaseDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperComputer;
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
		Root<ComputerDatabaseDTO> computer = query.from(ComputerDatabaseDTO.class);
		query.select(cb.count(computer));
		long result = em.createQuery(query).getSingleResult();
		em.close();
		return result;
	}

	public long getSearchNumberComputer(String search) {

		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<ComputerDatabaseDTO> computer = query.from(ComputerDatabaseDTO.class);

		query.select(cb.count(computer)).where(cb.like(computer.get("name"), "%" + search + "%"));

		long result = em.createQuery(query).getSingleResult();
		em.close();
		return result;
	}

	public Optional<Computer> getComputer(int id) throws ErrorSaisieUser {

		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ComputerDatabaseDTO> query = cb.createQuery(ComputerDatabaseDTO.class);
		Root<ComputerDatabaseDTO> computer = query.from(ComputerDatabaseDTO.class);

		query.select(computer).where(cb.equal(computer.get("id"), id));

		Optional<ComputerDatabaseDTO> resultDTO = Optional.ofNullable(em.createQuery(query).getSingleResult());
		em.close();
		
		return Optional.ofNullable(new MapperComputer().computerDatabaseDTOToComputer(resultDTO.orElseThrow()));
	}

	public List<Computer> getListComputer(Page page) throws ErrorSaisieUser {

		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ComputerDatabaseDTO> query = cb.createQuery(ComputerDatabaseDTO.class);
		Root<ComputerDatabaseDTO> computer = query.from(ComputerDatabaseDTO.class);

		query.select(computer);
		
		List<ComputerDatabaseDTO> resultDTO = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		
		List<Computer> result = new ArrayList<>();
		
		for(int i = 0; i < resultDTO.size(); i++) {
			result.add(new MapperComputer().computerDatabaseDTOToComputer(resultDTO.get(i)));
		}
		
		return result;
	}

	public List<Computer> getSearchComputer(String search, Page page) throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ComputerDatabaseDTO> query = cb.createQuery(ComputerDatabaseDTO.class);
		Root<ComputerDatabaseDTO> computer = query.from(ComputerDatabaseDTO.class);

		query.select(computer).where(cb.like(computer.get("name"), "%" + search + "%"));

		
		List<ComputerDatabaseDTO> resultDTO = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		
		List<Computer> result = new ArrayList<>();
		
		for(int i = 0; i < resultDTO.size(); i++) {
			result.add(new MapperComputer().computerDatabaseDTOToComputer(resultDTO.get(i)));
		}
		return result;
	}

	public List<Computer> getListComputerOrder(String orderField, String sort, Page page) throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ComputerDatabaseDTO> query = cb.createQuery(ComputerDatabaseDTO.class);
		Root<ComputerDatabaseDTO> computer = query.from(ComputerDatabaseDTO.class);

		query.select(computer);		
		if (sort.compareTo("ASC") == 0) {
			query.orderBy(cb.asc(computer.get(orderField)));
		} else if (sort.compareTo("DESC") == 0) {
			
			query.orderBy(cb.desc(computer.get(orderField)));
		}
		
		List<ComputerDatabaseDTO> resultDTO = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		
		List<Computer> result = new ArrayList<>();
		
		for(int i = 0; i < resultDTO.size(); i++) {
			result.add(new MapperComputer().computerDatabaseDTOToComputer(resultDTO.get(i)));
		}
		return result;
	}

	public List<Computer> getSearchComputerOrder(String search, String orderField, String sort, Page page)
			throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ComputerDatabaseDTO> query = cb.createQuery(ComputerDatabaseDTO.class);
		Root<ComputerDatabaseDTO> computer = query.from(ComputerDatabaseDTO.class);

		query.select(computer).where(cb.like(computer.get("name"), "%" + search + "%"));
		
		if (sort.compareTo("ASC") == 0) {
			query.orderBy(cb.asc(computer.get(orderField)));
		} else if (sort.compareTo("DESC") == 0) {
			query.orderBy(cb.desc(computer.get(orderField)));
		}

		List<ComputerDatabaseDTO> resultDTO = em.createQuery(query).setFirstResult(page.getMaxPrint() * page.getPage())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		
		List<Computer> result = new ArrayList<>();
		
		for(int i = 0; i < resultDTO.size(); i++) {
			result.add(new MapperComputer().computerDatabaseDTOToComputer(resultDTO.get(i)));
		}
		return result;
	}

	public void insertComputer(Computer computer) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(new MapperComputer().computerToComputerDatabaseDTO(computer));
		tx.commit();
		session.close();
		
	}

	public void updateComputer(Computer computer) {
		ComputerDatabaseDTO computerDTO = new MapperComputer().computerToComputerDatabaseDTO(computer);
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<ComputerDatabaseDTO> query = cb.createCriteriaUpdate(ComputerDatabaseDTO.class);
		Root<ComputerDatabaseDTO> rootComputer = query.from(ComputerDatabaseDTO.class);
		
		query.set("name", computerDTO.getName());
		query.set("introduced", MappeurDate.localDateToDate(computerDTO.getIntroduced()));
		query.set("discontinued", MappeurDate.localDateToDate(computerDTO.getDiscontinued()));
		query.set("company_id", computerDTO.getCompany().getId());
		query.where(cb.equal(rootComputer.get("id"), computerDTO.getId()));
		
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteComputerById(int id) {
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaDelete<ComputerDatabaseDTO> query = cb.createCriteriaDelete(ComputerDatabaseDTO.class);
		Root<ComputerDatabaseDTO> rootComputer = query.from(ComputerDatabaseDTO.class);
		query.where(cb.equal(rootComputer.get("id"), id));
		
		em.createQuery(query).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}
