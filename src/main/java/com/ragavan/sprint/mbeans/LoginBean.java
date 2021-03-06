package com.ragavan.sprint.mbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
		//String url = "index?faces-redirect=true";
		try {
			u = user.retrieveUserByEmail(getEmail());
			if (u.getPassword().equals(getPassword())) {
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				session.setAttribute("userSession", u);
				return "dashboard?faces-redirect=true";
			}
			else{
				String errorMessage = "Invalid Login";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
				FacesContext.getCurrentInstance().addMessage("form:msgId", message);
			}
		} catch (DataAccessException e) {
			String errorMessage = "Invalid Email";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			FacesContext.getCurrentInstance().addMessage("form:email", message);
		}

		return null;
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		if (session.getAttribute("userSession") != null) {
			session.invalidate();
		}
		return "index?faces-redirect=true";
	}
}
