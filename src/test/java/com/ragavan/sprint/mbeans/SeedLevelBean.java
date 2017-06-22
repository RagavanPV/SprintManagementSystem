package com.ragavan.sprint.mbeans;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ragavan.sprint.data.SeedLevelDAO;
import com.ragavan.sprint.domains.SeedLevel;

@Component
@RequestScoped
public class SeedLevelBean {
	@Autowired
	SeedLevelDAO levelDAO;

	private List<SeedLevel> organizations;

	public List<SeedLevel> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<SeedLevel> organizations) {
		this.organizations = organizations;
	}

	public String getMeToHomePage() {
		organizations = levelDAO.retrieveAllSeedLevels();
		return null;
	}
}
