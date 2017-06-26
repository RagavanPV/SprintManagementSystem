package com.ragavan.sprint.mbeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
import com.ragavan.sprint.domains.User;

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
	private List<SprintTask> sprintTask;

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
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		User u = (User) session.getAttribute("userSession");
		if (u.getRoleId().getId() == 2) {
			setTypeId(1);
		} else if (u.getRoleId().getId() == 3) {
			setTypeId(2);
		}
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

	public String getTasksByRole(int id) {

		System.out.println(id);
		setSprintTask(sprintTaskDAO.retrieveTasksByRole(id));
		return "task";
	}

	public String getTaskBySprintId(int id) {

		System.out.println(id);
		sprintTask = sprintTaskDAO.retrieveSprintTaskById(id);
		return "sprintTask";
	}

	public String deleteTaskBySprintId(int id) {
		 sprintTaskDAO.deleteSprintTaskBySprintId(id);
		 return "dashboard";
	}

	public String getAllTasks() {

		setSprintTask(sprintTaskDAO.retrieveAllSprintTasks());
		return "viewSprints?faces-redirect=true";
	}

	public List<SprintTask> getSprintTask() {
		return sprintTask;
	}

	public void setSprintTask(List<SprintTask> sprintTask) {
		this.sprintTask = sprintTask;
	}

	private String toggleActivation(int id, boolean active) {
		System.out.println("abc");
		if (typeDetailDAO.updateActivationById(id, active)) {
			return "viewSprints";
		} else {
			return null;
		}

	}
}
