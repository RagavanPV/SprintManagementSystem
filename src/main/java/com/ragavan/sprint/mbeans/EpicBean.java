package com.ragavan.sprint.mbeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ragavan.sprint.data.EpicDAO;
import com.ragavan.sprint.data.SprintDAO;
import com.ragavan.sprint.domains.Epic;
import com.ragavan.sprint.domains.Sprint;

@Component
@RequestScoped
public class EpicBean {
	@Autowired
	EpicDAO dao;

	@Autowired
	SprintDAO sprintDAO;

	private List<Epic> epics;
	private List<Sprint> sprints;
	private String name;
	private String code;
	private Date startDate;
	private Date endDate;
	private Date expectedEndDate;
	private int sprintId;
	private int id;

	public List<Epic> getEpics() {
		return epics;
	}

	public void setEpics(List<Epic> epics) {
		this.epics = epics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public int getSprintId() {
		return sprintId;
	}

	public void setSprintId(int sprintId) {
		this.sprintId = sprintId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

	@PostConstruct
	public void init() {
		getAllSprints();
	}

	public void getAllSprints() {
		sprints = sprintDAO.retrieveAllSprints();
	}

	public String addEpic() {
		Epic epics = new Epic();
		epics.setCode(getCode());
		epics.setName(getName());
		epics.setStartDate(getStartDate());
		epics.setExpectedEndDate(getExpectedEndDate());
		Sprint s = new Sprint();
		s.setId(getSprintId());
		epics.setSprintId(s);
		dao.addEpics(epics);
		return "dashboard";
	}

}
