package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.Sprint;
@Repository
public class SprintDAO {
	@Autowired
	SessionFactory sessionFactory;

	public List<Sprint> retrieveAllSeedLevels() {
		List<Sprint> sprints = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Sprint> query = session.createQuery("from Sprint", Sprint.class);
		sprints = query.list();
		transaction.commit();
		session.close();
		return sprints;
	}
}
