package org.cp.projet.comptes.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Client implements Serializable {
	@Id
	@GeneratedValue
	private Long code;
	private String nom;
	private String email;
	// quand je demande à Hibernate un client , il va charger les info : code et date
	// et pas la collection des comptes qu'en cas de besoin et quand j'appelle
	// getComptes c'est à ce moment là ou elle va charger les comptes
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Collection<Compte> comptes;

	public Client(String nom) {
		super();
		this.nom = nom;
	}

	public Client(Long code) {
		super();
		this.code = code;
	}

	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}
	

	
}
