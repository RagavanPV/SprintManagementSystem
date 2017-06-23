package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.SeedLevel;
import com.ragavan.sprint.domains.SeedRole;

@Repository
public class SeedRoleDAO {

	@Autowired
	SessionFactory sessionFactory;

	public List<SeedRole> retrieveAllSeedRoles() {
		List<SeedRole> roles = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query<SeedRole> query = session.createQuery("from SeedRole", SeedRole.class);
		System.out.println(query.toString());
		roles = query.list();
		System.out.println(roles);
		return roles;
	}
	
	public boolean updateSeedLevel(SeedLevel seedLevel) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("UPDATE SeedRole SET NAME=:n, CODE=:c WHERE id=:id");
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
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from SeedRole where id=:id");
		query.setParameter("id", id);
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

}