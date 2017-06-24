package com.ragavan.sprint.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ragavan.sprint.data.EpicDAO;
import com.ragavan.sprint.data.SeedRoleDAO;
import com.ragavan.sprint.data.SprintTaskDAO;
import com.ragavan.sprint.data.TypeDAO;
import com.ragavan.sprint.data.TypeDetailDAO;
import com.ragavan.sprint.domains.Epic;
import com.ragavan.sprint.domains.SeedRole;
import com.ragavan.sprint.domains.Type;
import com.ragavan.sprint.domains.TypeDetail;
import com.ragavan.sprint.dto.TaskDTO;

@Component
@RequestScoped
public class TaskBean {
	@Autowired
	TypeDetailDAO typeDetailDAO;

	@Autowired
	TypeDAO typeDAO;

	@Autowired
	EpicDAO epicDAO;

	@Autowired
	SprintTaskDAO sprintTaskDAO;

	@Autowired
	SeedRoleDAO roleDAO;

	private TaskDTO tasks;
	private List<Type> types;
	private List<Epic> epics;
	private List<SeedRole> roles;

	public TaskDTO getTasks() {
		return tasks;
	}

	public void setTasks(TaskDTO tasks) {
		this.tasks = tasks;
	}

	@PostConstruct
	private void init() {
		types = typeDAO.retrieveAllTypes();
		epics = epicDAO.retrieveAllEpics();
		roles = roleDAO.retrieveAllSeedRoles();
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public List<Epic> getEpics() {
		return epics;
	}

	public void setEpics(List<Epic> epics) {
		this.epics = epics;
	}

	public List<SeedRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SeedRole> roles) {
		this.roles = roles;
	}

	public String addTask() {
		TypeDetail typeDetail = new TypeDetail();
		typeDetail.setName(getTasks().getName());
		typeDetail.setDescription(getTasks().getDescription());
		typeDetail.setStartDate(getTasks().getStartDate());
		typeDetail.setExpectedEndDate(getTasks().getExpectedEndDate());
		Type type = new Type();
		type.setId(getTasks().getTypeId());
		typeDetail.setTypeId(type);
		typeDetailDAO.addTypeDetail(typeDetail);
		return "dashboard";
	}
}
