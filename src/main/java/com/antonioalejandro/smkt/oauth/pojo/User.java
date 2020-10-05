package com.antonioalejandro.smkt.oauth.pojo;

public class User {
	private Long id;
	private String name;
	private String lastname;
	private String username;
	private String email;
	private String password;
	private String photo;
	private Role role;
	
	public User(Long id, String name, String lastname, String username, String email, String password, String photo,
			Role role) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.photo = photo;
		this.role = role;
	}
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role roles) {
		this.role = roles;
	}
	
	
	
}
