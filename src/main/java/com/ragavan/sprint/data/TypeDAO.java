package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.Type;

@Repository
public class TypeDAO {
	@Autowired
	SessionFactory sessionFactory;

	public List<Type> retrieveAllTypes() {
		List<Type> types = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Type> query = session.createQuery("from Type", Type.class);
		types = query.list();
		transaction.commit();
		session.close();
		return types;
	}

	public Type retrieveTypeById(int id) {
		Type types = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Type> query = session.createQuery("from Type where id=:id", Type.class);
		query.setParameter("id", id);
		types = query.getSingleResult();
		transaction.commit();
		session.close();
		return types;
	}
}
