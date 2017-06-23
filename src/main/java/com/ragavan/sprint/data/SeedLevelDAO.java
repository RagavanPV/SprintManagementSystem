package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.SeedLevel;

@Repository
public class SeedLevelDAO {

	@Autowired
	SessionFactory sessionFactory;

	public List<SeedLevel> retrieveAllSeedLevels() {
		List<SeedLevel> levels = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<SeedLevel> query = session.createQuery("from SeedLevel", SeedLevel.class);
		levels = query.list();
		transaction.commit();
		session.close();
		return levels;
	}

	public boolean addSeedLevel(SeedLevel seedLevel) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query = session.createQuery("INSERT INTO SeedLevel(CODE,NAME) VALUES(':code',':name')");
		query.setParameter("code", seedLevel.getCode());
		query.setParameter("name", seedLevel.getName());
		int rows = query.executeUpdate();
		transaction.commit();
		session.close();
		if (rows > 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public boolean updateSeedLevel(SeedLevel seedLevel) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("UPDATE SeedLevel SET NAME=:n, CODE=:c WHERE id=:id");
		query.setParameter("c", seedLevel.getCode());
		query.setParameter("n", seedLevel.getName());
		query.setParameter("id", seedLevel.getId());
		int rows = query.executeUpdate();
		transaction.commit();
		session.close();
		if (rows > 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public boolean deleteSeedLevel(int id) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("delete from SeedLevel where id=:id");
		query.setParameter("id", id);
		int rows = query.executeUpdate();
		t.commit();
		session.close();
		if (rows > 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

}