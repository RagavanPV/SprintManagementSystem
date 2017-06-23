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

	public List<Epic> retrieveAllEpics() {
		List<Epic> epics = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Epic> query = session.createQuery("from Epic", Epic.class);
		epics = query.list();
		transaction.commit();
		session.close();
		return epics;
	}

	public Epic retrieveEpicById(int id) {
		Epic epic = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Epic> query = session.createQuery("from Epic where id=:id", Epic.class);
		query.setParameter("id", id);
		epic = query.getSingleResult();
		transaction.commit();
		session.close();
		return epic;
	}

	public boolean updateEpic(Epic epic) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session
				.createQuery("UPDATE Epic SET NAME=:n, CODE=:c,START_DATE=:startDate,EXPECTED_END_DATE=:expEndDate,"
						+ "END_DATE=:endDate,SPRINT_ID=:sprintId WHERE id=:id");
		query.setParameter("c", epic.getCode());
		query.setParameter("n", epic.getName());
		query.setParameter("startDate", epic.getStartDate());
		query.setParameter("endDate", epic.getEndDate());
		query.setParameter("expEndDate", epic.getExpectedEndDate());
		query.setParameter("sprintId", epic.getSprintId());
		query.setParameter("id", epic.getId());
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

	public boolean deleteEpic(int id) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from Epic where id=:id");
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
