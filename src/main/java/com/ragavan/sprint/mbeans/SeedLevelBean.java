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

	private List<SeedLevel> levels;
	private String name;
	private String code;
	private int id;

	public List<SeedLevel> getLevels() {
		return levels;
	}

	public void setLevels(List<SeedLevel> levels) {
		this.levels = levels;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String addLevel() {
		SeedLevel level = new SeedLevel();
		level.setCode(getCode());
		level.setName(getName());
		level.setId(id);
		System.out.println(level.getCode() + " " + level.getName());
		boolean result = levelDAO.addSeedLevel(level);
		return "index";
	}

	public String deleteLevel() {

		boolean result = levelDAO.deleteSeedLevel(getId());
		return "index";
	}

	public String updateLevel() {
		SeedLevel level = new SeedLevel();
		level.setCode(getCode());
		level.setName(getName());
		level.setId(getId());
		boolean result = levelDAO.updateSeedLevel(level);
		return "index";
	}

	public String getAllLevels() {
		levels = levelDAO.retrieveAllSeedLevels();
		return "levels";
	}

}
