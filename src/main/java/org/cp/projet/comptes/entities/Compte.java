package org.cp.projet.comptes.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// Si on a l'héritage et on veux faire le mapping ORM
@Inheritance (strategy =InheritanceType.SINGLE_TABLE)
//
@DiscriminatorColumn (name = "TYPE_CPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Compte implements Serializable {
	@Id // string pas de generated value
	private String numCompte;
	private Date dateCreation;
	private double solde;
	@ManyToOne
	@JoinColumn(name = "CODE_CLIENT")
	private Client client;
	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations;

	public Compte(Date dateCreation, double solde) {
		super();
		this.dateCreation = dateCreation;
		this.solde = solde;
	}

	public Compte(String numCompte, Date dateCreation, double solde, Client client) {
		super();
		this.numCompte = numCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}
	

}
