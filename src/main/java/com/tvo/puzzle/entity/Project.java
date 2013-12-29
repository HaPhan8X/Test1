package com.tvo.puzzle.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_project", catalog = "puzzle")
public class Project implements java.io.Serializable{

	private Integer id;
	private String projectName;
	private Set<Product> product = new HashSet<Product>(0);
	private Set<PointManagement> pm = new HashSet<PointManagement>(); 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "project_name", nullable = false, length = 100)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblProject")
	public Set<Product> getProduct() {
		return product;
	}
	
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblProject")
	public Set<PointManagement> getPm() {
		return pm;
	}
	public void setPm(Set<PointManagement> pm) {
		this.pm = pm;
	}
}
