package org.cp.projet.comptes.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{
	private double decouvert;

	public CompteCourant(Date dateCreation, double solde, double decouvert) {
		super(dateCreation, solde);
		this.decouvert = decouvert;
	}

	public CompteCourant(String numCompte, Date dateCreation, double solde, Client client, double decouvert) {
		super(numCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}
	
	

}
