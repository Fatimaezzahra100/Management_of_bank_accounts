package org.cp.projet.comptes.entities;

import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {
	private double montantRetire;

	public Retrait(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
		
	}

	
}
