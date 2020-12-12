package org.cp.projet.comptes;

import java.util.Date;


import org.cp.projet.comptes.dao.ClientRepository;
import org.cp.projet.comptes.dao.CompteRepository;
import org.cp.projet.comptes.dao.OperationRepository;
import org.cp.projet.comptes.entities.Client;
import org.cp.projet.comptes.entities.CompteCourant;
import org.cp.projet.comptes.entities.CompteEpargne;

import org.cp.projet.comptes.entities.Retrait;
import org.cp.projet.comptes.entities.Versement;
import org.cp.projet.comptes.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// avec l'objet clientRepository on peut gerer des clients
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private IBanqueMetier banqueMetier;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Client c1 = new Client("Fati", "fatimaezzahra.elg@gmail.com");
		Client c2 = new Client("ABDEL", "abdel-ompl@gmail.com");

		clientRepository.save(c1);
		clientRepository.save(c2);

		CompteCourant cc1 = compteRepository.save(new CompteCourant("B1", new Date(), 5000, c1, 6000));
		CompteEpargne ce1 = compteRepository.save(new CompteEpargne("B2", new Date(), 6000, c2, 5.6));

		operationRepository.save(new Versement(new Date(), 1000, cc1));
		operationRepository.save(new Versement(new Date(), 1000, ce1));

		operationRepository.save(new Retrait(new Date(), 60, cc1));
		operationRepository.save(new Retrait(new Date(), 30, ce1));

		banqueMetier.verser("B1", 555);
		
		System.out.println(banqueMetier.consulter("B1").getSolde());
		
		banqueMetier.virement("B2", "B1", 10);
		

	}

}
