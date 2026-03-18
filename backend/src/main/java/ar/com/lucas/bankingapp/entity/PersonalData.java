package ar.com.lucas.bankingapp.entity;

import java.io.Serializable;
import java.time.LocalDate;

import ar.com.lucas.bankingapp.entity.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal_data")
public class PersonalData implements Serializable {

	private static final long serialVersionUID = 3310572402417247123L;

	@Id
	private Long id; // Este será el mismo ID que AppUser

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	// Relación 1 a 1 con la tabla principal
	@OneToOne
	@MapsId // 🔥 Esta es la clave: le dice a JPA que use el ID de AppUser como ID propio
	@JoinColumn(name = "app_user_id")
	private AppUser appUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

}
