package org.cp.projet.comptes.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {
	private double montantVerse;

	public Versement(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
		
	}

	
	
	

}
