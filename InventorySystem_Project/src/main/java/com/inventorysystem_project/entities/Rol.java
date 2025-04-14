package com.inventorysystem_project.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "rol" }) })
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String rol;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	@JsonBackReference
	private Usuario user;

	public Rol(Long id, String rol, Usuario user) {
		this.id = id;
		this.rol = rol;
		this.user = user;
	}

	public Rol() {

	}


	//GETTERS AND SETTERES

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}