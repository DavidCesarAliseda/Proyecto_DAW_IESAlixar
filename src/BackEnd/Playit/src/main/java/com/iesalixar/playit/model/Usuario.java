package com.iesalixar.playit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;

	@Column(name = "nombre_usuario", nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido1;

	@Column(nullable = false)
	private String apellido2;

	@Column(nullable = false)
	private String role;

	@OneToMany(mappedBy = "usuario")
	Set<UsuarioContent> userContents;

	@JoinTable(
			name = "userChapter",
			joinColumns = @JoinColumn(name = "fk_user", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "fk_chapter", nullable = false)
	)
	@ManyToMany
	private List<Chapter> chapters = new ArrayList<>();

	public Usuario() {

	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id) {
		this.id_usuario = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<UsuarioContent> getUserContents() {
		return userContents;
	}

	public void setUserContents(Set<UsuarioContent> userContents) {
		this.userContents = userContents;
	}
	
	

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido1, apellido2, email, id_usuario, nombre, password, role, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido1, other.apellido1) && Objects.equals(apellido2, other.apellido2)
				&& Objects.equals(email, other.email) && Objects.equals(id_usuario, other.id_usuario)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(userName, other.userName);
	}

	public void addUsuarioContent(UsuarioContent usuarioContent) {
		userContents.add(usuarioContent);
	}

	public void deleteUsuarioContent(UsuarioContent usuarioContent) {
		userContents.remove(usuarioContent);
	}

	public void addChapter(Chapter chapter) {
		chapters.add(chapter);
	}
	
	public void deleteChapter(Chapter chapter) {
		chapters.remove(chapter);
	}

}