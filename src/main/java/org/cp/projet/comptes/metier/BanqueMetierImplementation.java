package org.cp.projet.comptes.metier;

import java.util.Date;

import org.cp.projet.comptes.dao.ClientRepository;
import org.cp.projet.comptes.dao.CompteRepository;
import org.cp.projet.comptes.dao.OperationRepository;
import org.cp.projet.comptes.entities.Compte;
import org.cp.projet.comptes.entities.CompteCourant;
import org.cp.projet.comptes.entities.Operation;
import org.cp.projet.comptes.entities.Retrait;
import org.cp.projet.comptes.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//REST
@Transactional
@Service
public class BanqueMetierImplementation implements IBanqueMetier {
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository OperationRepository;

	@Override
	public Compte consulter(String codeCompte) {
		Compte cp = compteRepository.findCompte(codeCompte);
		if (cp == null)
			throw new RuntimeException("compte introuvable !!!!");
		return cp;
	}

	@Override
	public void verser(String codeCompte, double montant) {
		Compte cp = consulter(codeCompte);
		Versement versement = new Versement(new Date(), montant, cp);
		OperationRepository.save(versement);
		// mettre à jour le solde
		cp.setSolde(cp.getSolde() + montant);
		compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte cp = consulter(codeCompte);
		double facilitesCaisse = 0;
		if (cp instanceof CompteCourant)
			facilitesCaisse = ((CompteCourant) cp).getDecouvert();
		if (cp.getSolde() < montant + facilitesCaisse)
			throw new RuntimeException(" Solde insuffisant ");
		Retrait retrait = new Retrait(new Date(), montant, cp);
		OperationRepository.save(retrait);
		// mettre à jour le solde
		cp.setSolde(cp.getSolde() - montant);
		compteRepository.save(cp);

	}

	@Override
	public void virement(String codeCompte1, String codeCompte2, double montant) {
		if (codeCompte1.equals(codeCompte2))
			throw new RuntimeException("Operation impossible in the same compte ");
		retirer(codeCompte1, montant);
		verser(codeCompte2, montant);

	}

	@Override
	public Page<Operation> listOperations(String codeCompte, int page, int size) {
		Page<Operation> pages = OperationRepository.listOperations(codeCompte, PageRequest.of(page, size));
		return pages;

	}

}
