package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.Epic;
import com.ragavan.sprint.domains.SprintTask;

@Repository
public class SprintTaskDAO {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	EpicDAO epicDAO;
	@Autowired
	SprintDAO sprintDAO;

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

	public List<SprintTask> retrieveSprintTaskById(int id) {
		List<SprintTask> sprintTask = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<SprintTask> query = session.createQuery("from SprintTask where epicId.sprintId.id=:id", SprintTask.class);
		query.setParameter("id", id);
		sprintTask = query.list();
		transaction.commit();
		session.close();
		return sprintTask;
	}

	public SprintTask retrieveSprintTaskBySprintId(int id) {
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

	public boolean deleteSprintTaskByEpicId(int id) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery("delete from sprint_task where epic_id=:id");
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

	public List<SprintTask> retrieveTasksByRole(int id) {
		List<SprintTask> sprintTask = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<SprintTask> query = session.createQuery("from SprintTask where roleId.id=:id", SprintTask.class);
		query.setParameter("id", id);
		sprintTask = query.list();
		transaction.commit();
		session.close();
		return sprintTask;
	}

	public boolean deleteSprintTaskBySprintId(int id) {
		boolean result = false;
		List<Epic> epics = epicDAO.retrieveEpicBySprintId(id);

		for (Epic epic : epics) {
			int epicId = epic.getId();
			System.out.println(epicId);
			deleteSprintTaskByEpicId(epicId);
			epicDAO.deleteEpic(epicId);
		}
		sprintDAO.deleteSprint(id);

		return result;
	}

}
