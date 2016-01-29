package fr.keyconsulting.formation.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.User;

@Service("MyPersister")
public class ORMPersistenceService implements PersistenceService {

	private static final String PERSISTENCE_UNIT_NAME = "model";
	private EntityManagerFactory factory;
	private EntityManager em;

	public ORMPersistenceService() {
		this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.em = factory.createEntityManager();
	}
	
	@Override
	public List<User> selectAllUsers() {
		CriteriaQuery<User> criteria = em.getCriteriaBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		TypedQuery<User> query = em.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public void deleteAllUsers() {
		em.getTransaction().begin();
		CriteriaDelete<User> criteria = em.getCriteriaBuilder().createCriteriaDelete(User.class);
		criteria.from(User.class);
		Query query = em.createQuery(criteria);
		query.executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void insertUser(User user) {
		em.getTransaction().begin();
		if (em.find(User.class, user.getId()) != null) {
			em.merge(user);
		}
		em.persist(user);
		em.getTransaction().commit();
	}

}
