package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.SprintTask;

@Repository
public class SprintTaskDAO {
	@Autowired
	SessionFactory sessionFactory;

	public List<SprintTask> retrieveAllSeedLevels() {
		List<SprintTask> sprintTask = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<SprintTask> query = session.createQuery("from SprintTask", SprintTask.class);
		sprintTask = query.list();
		transaction.commit();
		session.close();
		return sprintTask;
	}
}
