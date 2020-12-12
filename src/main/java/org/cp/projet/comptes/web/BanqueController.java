package org.cp.projet.comptes.web;




import org.cp.projet.comptes.dao.ClientRepository;
import org.cp.projet.comptes.entities.Client;
import org.cp.projet.comptes.entities.Compte;
import org.cp.projet.comptes.entities.Operation;
import org.cp.projet.comptes.metier.IBanqueMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {

	@Autowired
	private IBanqueMetier metier;
	
	

	@GetMapping(path = "/template1")
	public String index() {
		return "template1";
	}
	
	
	
	

	@GetMapping(path = "/comptes")
	public String listComptes(Model model) {
		return "comptes";
	}

	@GetMapping(path = "/consulterCompte")
	public String consulterCompte(String codeCompte, Model model,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		model.addAttribute("codeCompte", codeCompte);

		try {
			// Récupérer ce que l'utilisateur a tapé
			Compte cp = metier.consulter(codeCompte);
			model.addAttribute("compte", cp);
			Page<Operation> ops = metier.listOperations(codeCompte, page, size);
			model.addAttribute("operations", ops.getContent());
			// PAGINATION 1er chose à faire
			int[] pages = new int[ops.getTotalPages()];
			model.addAttribute("pages", pages);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "comptes";
	}

	@RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String codeCompte2, String codeCompte, double montant,
			String typeOperation) {
		try {
			if (typeOperation.equals("VERS")) {
				metier.verser(codeCompte, montant);
			}

			else if (typeOperation.equals("RET")) {
				metier.retirer(codeCompte, montant);
			}

			else if (typeOperation.equals("VIR")) {
				metier.virement(codeCompte, codeCompte2, montant);
			}

		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCompte?codeCompte=" + codeCompte + "&error=" + e.getMessage();
		}
		return "redirect:/consulterCompte?codeCompte=" + codeCompte;
	}

	@GetMapping(path = "/operation")
	public String listops(Model model, String codeCompte, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return "operation";
	}

	public String consulterOperations(String codeCompte, Model model,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		model.addAttribute("codeCompte", codeCompte);

		try {
			// Récupérer ce que l'utilisateur a tapé
			Compte cp = metier.consulter(codeCompte);
			model.addAttribute("compte", cp);
			Page<Operation> ops = metier.listOperations(codeCompte, page, size);
			model.addAttribute("operations", ops.getContent());
			// PAGINATION 1er chose à faire
			int[] pages = new int[ops.getTotalPages()];
			model.addAttribute("pages", pages);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}

		return "operation";
	}

}
