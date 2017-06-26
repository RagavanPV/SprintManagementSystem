package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.SprintTask;
import com.ragavan.sprint.domains.TypeDetail;

@Repository
public class SprintTaskDAO {
	@Autowired
	SessionFactory sessionFactory;

	public List<SprintTask> retrieveAllSprintTasks() {
		List<SprintTask> sprintTask = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<SprintTask> query = session.createQuery("from SprintTask", SprintTask.class);
		sprintTask = query.list();
		transaction.commit();
		session.close();
		return sprintTask;
	}

	public SprintTask retrieveSprintTaskById(int id) {
		SprintTask sprintTask = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<SprintTask> query = session.createQuery("from SprintTask where id=:id", SprintTask.class);
		query.setParameter("id", id);
		sprintTask = query.getSingleResult();
		transaction.commit();
		session.close();
		return sprintTask;
	}

	public boolean deleteSprintTask(int id) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from SprintTask where id=:id");
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

	public boolean addSprintTask(SprintTask sprintTask) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(
				"INSERT INTO sprint_task(EPIC_ID,TYPE_DETAIL_ID,ROLE_ID) VALUES(:epicId,:typeDetailId,:roleId)");
		query.setParameter("epicId", sprintTask.getEpicId().getId());
		query.setParameter("typeDetailId", sprintTask.getTypeDetailId().getId());
		query.setParameter("roleId", sprintTask.getRoleId().getId());

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
