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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.data.User;
import com.excilys.computer.database.dto.CompanyDatabaseDTO;
import com.excilys.computer.database.dto.UserDatabaseDTO;
import com.excilys.computer.database.error.ErrorDAOComputer;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperUser;

@Repository
public class DAOUser {

	private SessionFactory sessionFactory;

	public DAOUser(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean login(String username, String password) throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<UserDatabaseDTO> query = cb.createQuery(UserDatabaseDTO.class);
		Root<UserDatabaseDTO> User = query.from(UserDatabaseDTO.class);

		query.select(User).where(cb.equal(User.get("username"), username));
		UserDatabaseDTO resultDTO;

		try {
			resultDTO = em.createQuery(query).getSingleResult();
		} catch (NoResultException | NonUniqueResultException errorResult) {
			new ErrorDAOComputer(null).idInvalid(errorResult);
			throw new ErrorSaisieUser(this.getClass());
		}
		em.close();
		
		return BCrypt.checkpw(username, resultDTO.getPassword());
	}

	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(new MapperUser().userToUserDatabaseDTO(user));
		tx.commit();
		session.close();

	}

	public List<User> getUserList() {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<UserDatabaseDTO> query = cb.createQuery(UserDatabaseDTO.class);
		Root<UserDatabaseDTO> user = query.from(UserDatabaseDTO.class);

		query.select(user);

		List<UserDatabaseDTO> resultDTO = em.createQuery(query).getResultList();
		em.close();
		List<User> result = new ArrayList<>();
		for (int i = 0; i < resultDTO.size(); i++) {
			try {
				result.add(new MapperUser().userDatabaseDTOToUser(resultDTO.get(i)));
			} catch (ErrorSaisieUser e) {
				e.databaseCorrupt();
			}
		}
		return result;
	}
	
	public List<User> getListUser(Page page) {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<UserDatabaseDTO> query = cb.createQuery(UserDatabaseDTO.class);
		Root<UserDatabaseDTO> user = query.from(UserDatabaseDTO.class);

		query.select(user);

		List<UserDatabaseDTO> resultDTO = em.createQuery(query).setFirstResult(page.getMaxPrint())
				.setMaxResults(page.getMaxPrint()).getResultList();
		em.close();
		List<User> result = new ArrayList<>();
		for (int i = 0; i < resultDTO.size(); i++) {
			try {
				result.add(new MapperUser().userDatabaseDTOToUser(resultDTO.get(i)));
			} catch (ErrorSaisieUser e) {
				e.databaseCorrupt();
			}
		}
		return result;
	}
	
	

	public void updateUser(User user) throws ErrorSaisieUser {
		UserDatabaseDTO userDTO = new MapperUser().userToUserDatabaseDTO(user);
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<UserDatabaseDTO> query = cb.createCriteriaUpdate(UserDatabaseDTO.class);
		Root<UserDatabaseDTO> rootUser = query.from(UserDatabaseDTO.class);

		query.set("authority", userDTO.getAuthority());
		query.set("enabled", userDTO.getEnabled());
		query.where(cb.equal(rootUser.get("id"), userDTO.getId()));

		if(em.createQuery(query).executeUpdate() == 0) {
			throw new ErrorSaisieUser(this.getClass());
		}
		em.getTransaction().commit();
		em.close();
	}

	public void deleteUser(int id) throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaDelete<UserDatabaseDTO> queryCompany = cb.createCriteriaDelete(UserDatabaseDTO.class);
		Root<UserDatabaseDTO> rootUser = queryCompany.from(UserDatabaseDTO.class);
		queryCompany.where(cb.equal(rootUser.get("id"), id));

		if (em.createQuery(queryCompany).executeUpdate() == 0) {
			throw new ErrorSaisieUser(this.getClass());
		}
		em.getTransaction().commit();
		em.close();
	}
}
