package com.ragavan.sprint.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ragavan.sprint.domains.Sprint;

@Repository
public class SprintDAO {
	@Autowired
	SessionFactory sessionFactory;

	public List<Sprint> retrieveAllSprints() {
		List<Sprint> sprints = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Sprint> query = session.createQuery("from Sprint", Sprint.class);
		sprints = query.list();
		transaction.commit();
		session.close();
		return sprints;
	}

	public Sprint retrieveSprintById(int id) {
		Sprint sprint = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Sprint> query = session.createQuery("from Sprint where id=:id", Sprint.class);
		query.setParameter("id", id);
		sprint = query.getSingleResult();
		transaction.commit();
		session.close();
		return sprint;
	}

	public boolean updateSprintById(Sprint sprint) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session
				.createQuery("UPDATE Sprint SET NAME=:n, CODE=:c,START_DATE=:startDate,EXPECTED_END_DATE=:expEndDate,"
						+ "END_DATE=:endDate,TYPE_ID=:TYPEId WHERE id=:id");
		query.setParameter("c", sprint.getCode());
		query.setParameter("n", sprint.getName());
		query.setParameter("startDate", sprint.getStartDate());
		query.setParameter("endDate", sprint.getEndDate());
		query.setParameter("expEndDate", sprint.getExpectedEndDate());
		query.setParameter("typeId", sprint.getTypeId());
		query.setParameter("id", sprint.getId());
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

	public boolean deleteSprint(int id) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from Sprint where id=:id");
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
