package com.tvo.puzzle.entity;
// default package
// Generated Jul 16, 2013 4:38:29 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TblGame generated by hbm2java
 */
@Entity
@Table(name = "tbl_game", catalog = "puzzle")
public class Game implements java.io.Serializable {

	private Integer id;
	private Project project;
	private String name;
	private String description;
	private List<GameDetail> tblGameDetails = new ArrayList<GameDetail>();

	public Game() {
	}

	public Game(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Game(String name, String description,
			List<GameDetail> tblGameDetails) {
		this.name = name;
		this.description = description;
		this.tblGameDetails = tblGameDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false, length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tblGame")
	public List<GameDetail> getTblGameDetails() {
		return this.tblGameDetails;
	}


	public void setTblGameDetails(List<GameDetail> tblGameDetails) {
		this.tblGameDetails = tblGameDetails;
	}

	@ManyToOne
	@JoinColumn(name="project")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
