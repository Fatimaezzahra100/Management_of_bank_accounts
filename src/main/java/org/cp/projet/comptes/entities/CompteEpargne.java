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
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
	private double taux;

	public CompteEpargne(Date dateCreation, double solde, double taux) {
		super(dateCreation, solde);
		this.taux = taux;
	}

	public CompteEpargne(String numCompte, Date dateCreation, double solde, Client client, double taux) {
		super(numCompte, dateCreation, solde, client);
		this.taux = taux;
	}
	
	

}
