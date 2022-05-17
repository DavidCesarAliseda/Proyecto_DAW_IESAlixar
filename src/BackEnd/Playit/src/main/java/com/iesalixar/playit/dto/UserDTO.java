package com.iesalixar.playit.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	private String userName;
	private String password;
	private String email;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public UserDTO() {

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
	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", password=" + password + ", email=" + email + ", nombre=" + nombre
				+ ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + "]";
	}
	
	
}
