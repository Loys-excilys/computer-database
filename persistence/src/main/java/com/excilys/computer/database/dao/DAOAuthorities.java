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

import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.dto.AuthoritiesDatabaseDTO;
import com.excilys.computer.database.dto.UserDatabaseDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperAuthorities;

@Repository
public class DAOAuthorities {

	private SessionFactory sessionFactory;

	public DAOAuthorities(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addAuthorities(Authorities authorities) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(new MapperAuthorities().authoritiesToAuthoritiesDatabaseDTO(authorities));
		tx.commit();
		session.close();
	}

	public List<Authorities> getAuthorities() {
		EntityManager em = this.sessionFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<AuthoritiesDatabaseDTO> query = cb.createQuery(AuthoritiesDatabaseDTO.class);
		Root<AuthoritiesDatabaseDTO> authorities = query.from(AuthoritiesDatabaseDTO.class);

		query.select(authorities);

		List<AuthoritiesDatabaseDTO> resultDTO = em.createQuery(query).getResultList();
		em.close();

		List<Authorities> result = new ArrayList<>();

		for (int i = 0; i < resultDTO.size(); i++) {
			try {
				result.add(new MapperAuthorities().authoritiesDatabaseDTOToAuthorities(resultDTO.get(i)));
			} catch (ErrorSaisieUser e) {
				e.databaseCorrupt();
			}
		}

		return result;
	}

	public void updateAuthorities(Authorities authorities) throws ErrorSaisieUser {
		AuthoritiesDatabaseDTO authoritiesDTO = new MapperAuthorities()
				.authoritiesToAuthoritiesDatabaseDTO(authorities);
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaUpdate<AuthoritiesDatabaseDTO> query = cb.createCriteriaUpdate(AuthoritiesDatabaseDTO.class);
		Root<AuthoritiesDatabaseDTO> rootUser = query.from(AuthoritiesDatabaseDTO.class);

		query.set("authority", authoritiesDTO.getAuthority());
		query.where(cb.equal(rootUser.get("id"), authoritiesDTO.getId()));

		if(em.createQuery(query).executeUpdate() < 1) {
			throw new ErrorSaisieUser(this.getClass());
		}
		em.getTransaction().commit();
		em.close();
	}

	public void deleteAuthorities(int id) throws ErrorSaisieUser {
		EntityManager em = this.sessionFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaDelete<UserDatabaseDTO> queryCompany = cb.createCriteriaDelete(UserDatabaseDTO.class);
		Root<UserDatabaseDTO> rootUser = queryCompany.from(UserDatabaseDTO.class);
		queryCompany.where(cb.equal(rootUser.get("authority"), id));

		CriteriaDelete<AuthoritiesDatabaseDTO> queryAuthorities = cb.createCriteriaDelete(AuthoritiesDatabaseDTO.class);
		Root<AuthoritiesDatabaseDTO> rootAuthorities = queryAuthorities.from(AuthoritiesDatabaseDTO.class);
		queryAuthorities.where(cb.equal(rootAuthorities.get("id"), id));

		em.createQuery(queryCompany).executeUpdate();
		if (em.createQuery(queryAuthorities).executeUpdate() == 0) {
			throw new ErrorSaisieUser(this.getClass());
		}
		em.getTransaction().commit();
		em.close();
	}
}
