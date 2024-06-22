package sn.pad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sn.pad.dao.BordereauDAO;
import sn.pad.entites.Bordereau;
//import sn.pad.metier.BordereauProcessing;

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
	
	//http://localhost:3400/bordereauapi/bordereaux
		@PostMapping("/bordereaux")
		public ResponseEntity<Bordereau> ajouterBordereau(@RequestBody Bordereau bordereau)
		{
			Bordereau newBordereau = bordereauDAO.creerBordereau(bordereau);
			return new ResponseEntity<Bordereau> (newBordereau,HttpStatus.CREATED);
		}
		//http://localhost:3400/bordereauapi/bordereaux
		@GetMapping("/bordereaux")
		public ResponseEntity<List<Bordereau>> afficherBordereau()
		{
			List<Bordereau> liste = bordereauDAO.listerBordereaux();
			return new ResponseEntity <List<Bordereau>> (liste,HttpStatus.OK);
		}
		//rechercher bordereau à partir de codeBordereau
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
	        if (existingBordereau == null) 
	        {
	            throw new Exception("Bordereau non trouvé avec la matricule : " + bordereau.getMatriculeJournalier());
	        }
	        bordereauDAO.modifierBordereau(bordereau);
	        return new ResponseEntity<>(bordereau, HttpStatus.OK);
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
}
