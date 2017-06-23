package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.Epic;

@Repository
public class EpicDAO {

	@Autowired
	SessionFactory sessionFactory;

	public List<Epic> retrieveAllSeedLevels() {
		List<Epic> epics = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Epic> query = session.createQuery("from Epic", Epic.class);
		epics = query.list();
		transaction.commit();
		session.close();
		return epics;
	}
}
