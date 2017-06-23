package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.TypeDetail;

@Repository
public class TypeDetailDAO {

	@Autowired
	SessionFactory sessionFactory;

	public List<TypeDetail> retrieveAllSeedLevels() {
		List<TypeDetail> typeDetail = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<TypeDetail> query = session.createQuery("from TypeDetail", TypeDetail.class);
		typeDetail = query.list();
		transaction.commit();
		session.close();
		return typeDetail;
	}
}
