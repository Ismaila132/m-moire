package sn.pad.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import sn.pad.client.ClientJournalier;
import sn.pad.client.ClientNavire;
import sn.pad.dao.BordereauDAO;
import sn.pad.entites.Bordereau;
//import sn.pad.metier.BordereauProcessing;
import sn.pad.entites.Journalier;
import sn.pad.entites.Navire;

@RestController
//toutes les requetes sont préfixés par l'URL de base suivante:
//http://localhost:3400/journalierapi/...
@RequestMapping("/bordereauapi")
public class BordereauControleur 
{
	//@Autowired
	//private BordereauProcessing bordereauProcessing;
	@Autowired
	private BordereauDAO bordereauDAO;
	@Autowired
	private ClientJournalier clientJournalier;
	@Autowired
	private ClientNavire clientNavire;
	
	//http://localhost:3400/bordereauapi/bordereaux

		@PostMapping("/bordereaux")
		public ResponseEntity<Bordereau> ajouterBordereau(@RequestBody Bordereau bordereau)
		{
			 // Récupérer le journalier à partir du matricule du journalier du bordereau
	        ResponseEntity<Journalier> responseJournalier = clientJournalier.trouverJournalierParMatricule(bordereau.getMatriculeJournalier());
	        if (responseJournalier.getStatusCode() != HttpStatus.OK || responseJournalier.getBody() == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        Journalier journalier = responseJournalier.getBody();
	        
	        bordereau.setNomJournalier(journalier.getNomJournalier());
	        bordereau.setPrenomJournalier(journalier.getPrenomJournalier());
	        bordereau.setCodeCategorie(journalier.getCodeCategorie());
	        // Créer le bordereau
	        Bordereau newBordereau = bordereauDAO.creerBordereau(bordereau);
	        return new ResponseEntity<Bordereau>(newBordereau, HttpStatus.CREATED);
	    
		}
		//http://localhost:3400/bordereauapi/bordereaux
		
		@GetMapping("/bordereaux")
		public ResponseEntity<List<Bordereau>> afficherBordereau()
		{
			List<Bordereau> liste = bordereauDAO.listerBordereaux();
			return new ResponseEntity <List<Bordereau>> (liste,HttpStatus.OK);
		}
		//rechercher bordereau à partir de codeBordereau
		@PreAuthorize("hasAnyRole('CHEF_SERVICE', 'ASSISTANT')")
		@GetMapping("/bordereaux/codebordereau/{codeBordereau}")
		public ResponseEntity<Bordereau> trouverUnBordereau(@PathVariable("codeBordereau") Integer codeBordereau)
		{
			Bordereau bordereau = bordereauDAO.rechercherBordereau(codeBordereau);
			return new ResponseEntity<Bordereau>(bordereau,HttpStatus.OK);
		}
		
		//rechercher bordereau à partir de matriculeJournalier
		
		@GetMapping("/bordereaux/matriculejournalier/{matriculeJournalier}")
		public ResponseEntity<Bordereau> trouverBordereau(@PathVariable("matriculeJournalier") Integer matriculeJournalier)
		{
			Bordereau bordereau = bordereauDAO.rechercherParMatricule(matriculeJournalier);
			return new ResponseEntity<Bordereau>(bordereau,HttpStatus.OK);
		}
		
		// Modifier un bordereau
		
		@PutMapping("/bordereaux")
	    public ResponseEntity<Bordereau> modifierBordereau(@RequestBody Bordereau bordereau) throws Exception 
		{
	        Bordereau existingBordereau = bordereauDAO.rechercherParMatricule(bordereau.getMatriculeJournalier());
	        if (existingBordereau == null) {
	            throw new Exception("Bordereau non trouvé avec la matricule : " + bordereau.getMatriculeJournalier());
	        }
	        
	        // Récupérer les informations mises à jour du journalier
	        ResponseEntity<Journalier> responseJournalier = clientJournalier.trouverJournalierParMatricule(bordereau.getMatriculeJournalier());
	        if (responseJournalier.getStatusCode() != HttpStatus.OK || responseJournalier.getBody() == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        Journalier journalier = responseJournalier.getBody();
	        bordereau.setNomJournalier(journalier.getNomJournalier());
	        bordereau.setPrenomJournalier(journalier.getPrenomJournalier());

	        bordereauDAO.modifierBordereau(bordereau);
	        return new ResponseEntity<>(bordereau, HttpStatus.OK);
	    }
		
		//valider bordereau et enregistrer dans un fichier
	
		@PostMapping("/bordereaux/valider")
		 public ResponseEntity<Void> validerBordereau() 
		{
		        List<Bordereau> bordereaux = bordereauDAO.listerBordereaux();
		        StringBuilder sb = new StringBuilder();

		        for (Bordereau bordereau : bordereaux) {
		            sb.append("Code Bordereau: ").append(bordereau.getCodeBordereau()).append("\n")
		              .append("Matricule Journalier: ").append(bordereau.getMatriculeJournalier()).append("\n")
		              .append("Nom Journalier: ").append(bordereau.getNomJournalier()).append("\n")
		              .append("Prénom Journalier: ").append(bordereau.getPrenomJournalier()).append("\n")
		              .append("Code Catégorie: ").append(bordereau.getCodeCategorie()).append("\n")
		              .append("Date Embauche: ").append(bordereau.getDateEmbauche()).append("\n")
		              .append("Heure Embauche: ").append(bordereau.getHeureEmbauche()).append("\n")
		              .append("Code Port: ").append(bordereau.getCodePort()).append("\n")
		              .append("Code Navire: ").append(bordereau.getCodeNavire()).append("\n")
		              .append("-----------------------------------------------------------------\n");
		        }

		        java.nio.file.Path path = Paths.get("C:/embauche/bordereaux_valides.txt");
		        try {
		            Files.write(path, sb.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		        } catch (IOException e) {
		            e.printStackTrace();
		            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		        }

		        return new ResponseEntity<>(HttpStatus.OK);
		    }
		
		
	 // Supprimer un bordereau par matricule
		
	    @DeleteMapping("/bordereaux/matricule/{matriculeJournalier}")
	    public ResponseEntity<Void> supprimerBordereauParMatricule(@PathVariable("matriculeJournalier") Integer matriculeJournalier) 
	    {
	        bordereauDAO.supprimerBordereauParMatricule(matriculeJournalier);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    //supprimer un bordereau par codeBordereau
	 
		
	    @DeleteMapping("/bordereau/codebordereau/{codeBordereau}")
	    public ResponseEntity<Void> supprimerBordereauParId(@PathVariable("codeBordereau") Integer codeBordereau) 
	    {
	        bordereauDAO.supprimerBordereau(codeBordereau);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
		

	    @GetMapping("journalierapi/journaliers")
	    public ResponseEntity<List<Journalier>> listerJournaliers()
	    {
	    	return clientJournalier.afficherJournaliers();
	    }
		
	
	    @GetMapping("navireapi/navires")
	    public ResponseEntity<List<Navire>> listerNavires()
	    {
	    	return clientNavire.afficherNavires();
	    }
		
}
