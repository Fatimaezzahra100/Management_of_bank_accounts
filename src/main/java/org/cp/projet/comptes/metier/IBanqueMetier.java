package org.cp.projet.comptes.metier;



import org.cp.projet.comptes.entities.Compte;
import org.cp.projet.comptes.entities.Operation;
import org.springframework.data.domain.Page;


public interface IBanqueMetier {
	public Compte consulter (String codeCompte);
	public void verser (String codeCompte, double montant);
	public void retirer (String codeCompte, double montant);
	public void virement (String codeCompte1, String codeCompte2, double montant);
	public Page<Operation> listOperations (String codeCompte, int page, int size);
	
}
