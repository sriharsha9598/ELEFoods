package com.capfood.elef.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ELEF_ADMIN")
public class Admin  {
	
	
	

	@Id
	@Column(name="ADMINID")
    private int adminId;
	
	@NotEmpty(message="admin name cannot be empty")
	@Length(max=30)
	@Column(name="ADMINNAME")
	private String adminName;
	
	@NotEmpty(message="user name cannot be empty")
	@Length(max=10)
	@Column(name="USERNAME")
	private String userName;
	
	@NotEmpty(message="password cannot be empty")
	@Length(max=10)
	@Column(name="PASSWORD")
	private String password;
	
	@OneToOne(mappedBy="admin")
	@JoinColumn(name="BRANCH")
	private Branch branch;
	
	public Admin() {
		
	}

	public Admin(int adminId, @NotEmpty(message = "admin name cannot be empty") @Length(max = 30) String adminName,
			@NotEmpty(message = "user name cannot be empty") @Length(max = 10) String userName,
			@NotEmpty(message = "password cannot be empty") @Length(max = 10) String password, Branch branch) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.userName = userName;
		this.password = password;
		this.branch = branch;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", userName=" + userName + ", password="
				+ password + ", branch=" + branch + "]";
	}
	
	
	
}