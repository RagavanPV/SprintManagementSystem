package com.ragavan.sprint.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragavan.sprint.data.SeedRoleDAO;
import com.ragavan.sprint.domains.SeedRole;

@Service
public class SeedRoleService {

	@Autowired
	SeedRoleDAO roleDAO;

	public List<SeedRole> getAllSeedLevels() {
		List<SeedRole> list = null;
		list = roleDAO.retrieveAllSeedRoles();
		return list;
	}
}
