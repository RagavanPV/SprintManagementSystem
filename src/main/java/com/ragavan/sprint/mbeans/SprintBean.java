package com.ragavan.sprint.mbeans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ragavan.sprint.data.SprintDAO;
import com.ragavan.sprint.domains.Sprint;

@Component
@RequestScoped
public class SprintBean {

	@Autowired
	SprintDAO dao;
	
	private List<Sprint> sprints;
	private String name;
	private String code;
	private Date startDate;
	private Date endDate;
	private int id;
	public List<Sprint> getSprints() {
		return sprints;
	}
	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String addSprint() {
		Sprint sprint = new Sprint();
		sprint.setCode(getCode());
		sprint.setName(getName());
		sprint.setEndDate(getEndDate());
		System.out.println(sprint.getCode() + " " + sprint.getName()+" "+sprint.getEndDate());
//		System.out.println(sqlDate);
		dao.addSprint(sprint);
		return "dashboard";
	}
}
