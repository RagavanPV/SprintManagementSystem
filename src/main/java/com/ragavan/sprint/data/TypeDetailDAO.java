package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.Epic;
import com.ragavan.sprint.domains.TypeDetail;

@Repository
public class TypeDetailDAO {

	@Autowired
	SessionFactory sessionFactory;

	public List<TypeDetail> retrieveAllTypeDetails() {
		List<TypeDetail> typeDetail = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<TypeDetail> query = session.createQuery("from TypeDetail", TypeDetail.class);
		typeDetail = query.list();
		transaction.commit();
		session.close();
		return typeDetail;
	}

	public TypeDetail retrieveTypeDetailById(int id) {
		TypeDetail typeDetail = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<TypeDetail> query = session.createQuery("from TypeDetail where id=:id", TypeDetail.class);
		query.setParameter("id", id);
		typeDetail = query.getSingleResult();
		transaction.commit();
		session.close();
		return typeDetail;
	}

	public boolean addTypeDetail(TypeDetail typeDetail) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(
				"INSERT INTO epics(NAME,DESCRIPTION,START_DATE,EXPECTED_END_DATE,TYPE_ID) VALUES(:name,:description,:startDate,:expectedEndDate,:typeId)");
		query.setParameter("name", typeDetail.getName());
		query.setParameter("description", typeDetail.getDescription());
		java.sql.Date sqlstartDate = new java.sql.Date(typeDetail.getStartDate().getTime());
		query.setParameter("startDate", sqlstartDate);
		java.sql.Date sqlEndDate = new java.sql.Date(typeDetail.getExpectedEndDate().getTime());
		query.setParameter("expectedEndDate", sqlEndDate);
		query.setParameter("sprintId", typeDetail.getTypeId().getId());

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
