package com.ragavan.sprint.mbeans;

import java.util.Date;
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
import com.ragavan.sprint.domains.SprintTask;
import com.ragavan.sprint.domains.Type;
import com.ragavan.sprint.domains.TypeDetail;

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

	private String name;
	private String description;
	private String code;
	private Date startDate;
	private Date expectedEndDate;
	private int typeId;
	private int epicId;
	private int roleId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getEpicId() {
		return epicId;
	}

	public void setEpicId(int epicId) {
		this.epicId = epicId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	private List<Type> types;
	private List<Epic> epics;
	private List<SeedRole> roles;

	@PostConstruct
	public void init() {
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
		typeDetail.setName(getName());
		typeDetail.setDescription(getDescription());
		typeDetail.setStartDate(getStartDate());
		typeDetail.setExpectedEndDate(getExpectedEndDate());
		Type type = new Type();
		type.setId(getTypeId());
		typeDetail.setTypeId(type);
		typeDetailDAO.addTypeDetail(typeDetail);
		int id = typeDetailDAO.getIdByName(getName()).getId();
		SprintTask sprintTask = new SprintTask();
		Epic epic = new Epic();
		epic.setId(getEpicId());
		SeedRole role = new SeedRole();
		role.setId(getRoleId());
		typeDetail.setId(id);
		sprintTask.setEpicId(epic);
		sprintTask.setRoleId(role);
		sprintTask.setTypeDetailId(typeDetail);
		sprintTaskDAO.addSprintTask(sprintTask);
		return "dashboard";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
