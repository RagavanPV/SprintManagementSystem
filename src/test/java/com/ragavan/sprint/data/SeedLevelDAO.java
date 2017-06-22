package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.SeedLevel;

@Repository
public class SeedLevelDAO {

	@Autowired
	SessionFactory sessionFactory;
	public List<SeedLevel> retrieveAllSeedLevels() {
		List<SeedLevel> organizations = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query<SeedLevel> query = session.createQuery("from SeedLevel", SeedLevel.class);
		organizations = query.list();
		return organizations;
	}

}