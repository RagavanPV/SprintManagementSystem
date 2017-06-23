package com.ragavan.sprint.domains;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sprint_task")
public class SprintTask {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "epic_id")
	private Epic epicId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_detail_id")
	private TypeDetail TypeDetailId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private SeedRole roleId;

	/**************** Getters and Setters *********************/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Epic getEpicId() {
		return epicId;
	}

	public void setEpicId(Epic epicId) {
		this.epicId = epicId;
	}

	public TypeDetail getTypeDetailId() {
		return TypeDetailId;
	}

	public void setTypeDetailId(TypeDetail typeDetailId) {
		TypeDetailId = typeDetailId;
	}

	public SeedRole getRoleId() {
		return roleId;
	}

	public void setRoleId(SeedRole roleId) {
		this.roleId = roleId;
	}

}
