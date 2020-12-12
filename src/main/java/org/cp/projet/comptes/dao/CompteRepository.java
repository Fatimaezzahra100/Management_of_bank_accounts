package org.cp.projet.comptes.dao;

import org.cp.projet.comptes.entities.Compte;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte, String> {
	@Query("select c from Compte c where c.numCompte=:x ")
	Compte findCompte(@Param("x") String codeCompte);

}
