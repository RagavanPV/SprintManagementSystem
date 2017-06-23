package com.ragavan.sprint.mbeans;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ragavan.sprint.data.UserDAO;
import com.ragavan.sprint.data.exception.DataAccessException;
import com.ragavan.sprint.domains.User;

@Component
@RequestScoped
public class LoginBean {
	@Autowired
	UserDAO user;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

	public String login() {
		User u;
		String url="index?faces-redirect=true";
		try {
			u = user.retrieveUserByEmail(getEmail());
			if (u.getPassword().equals(getPassword())) {
				url = "dashboard?faces-redirect=true";
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return url;
	}
}
