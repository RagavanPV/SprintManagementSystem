package com.ragavan.sprint.mbeans;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ragavan.sprint.data.SeedRoleDAO;
import com.ragavan.sprint.domains.SeedRole;

@Component
@RequestScoped
public class SeedRoleBean {
	@Autowired
	SeedRoleDAO roleDAO;

	private List<SeedRole> roles;

	public String getAllRoles() {
		roles = roleDAO.retrieveAllSeedRoles();
		return "role";
	}

	public List<SeedRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SeedRole> roles) {
		this.roles = roles;
	}
}
