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
	private SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

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

	public List<Epic> retrieveEpicBySprintId(int id) {
		List<Epic> epics = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Epic> query = session.createSQLQuery("SELECT * FROM epics e JOIN sprints s ON e.sprint_id=s.id WHERE e.sprint_id=:sprintid");
		query.setParameter("sprintid", id);
		epics = query.list();
		transaction.commit();
		session.close();
		return epics;
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

	public boolean addEpics(Epic epic) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(
				"INSERT INTO epics(CODE,NAME,START_DATE,EXPECTED_END_DATE,SPRINT_ID) VALUES(:code,:name,:startDate,:expectedEndDate,:sprintId)");
		query.setParameter("code", epic.getCode());
		query.setParameter("name", epic.getName());
		java.sql.Date sqlstartDate = new java.sql.Date(epic.getStartDate().getTime());
		query.setParameter("startDate", sqlstartDate);
		java.sql.Date sqlEndDate = new java.sql.Date(epic.getExpectedEndDate().getTime());
		query.setParameter("expectedEndDate", sqlEndDate);
		query.setParameter("sprintId", epic.getSprintId().getId());

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
