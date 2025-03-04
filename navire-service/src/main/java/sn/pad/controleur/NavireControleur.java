package sn.pad.controleur;

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

import sn.pad.dao.*;
import sn.pad.entites.*;
import sn.pad.metier.*;
import sn.pad.Client.*;

@RestController
//toutes les requetes sont préfixés par l'URL de base suivante:
//http://localhost:3100/navireapi/...
@RequestMapping("/navireapi")
public class NavireControleur 
{
	@Autowired
	private NavireProcessing navireprocessing;
	@Autowired
	private NavireDAO navireDAO;
	@Autowired
	private ClientPort clientPort;
	
	//http://localhost:3100/navireapi/navires
	 @PostMapping("/navires")
	    public ResponseEntity<Navire> ajouterNavire(@RequestBody Navire navire) {
	        // Assurez-vous que le navire a un port associé
	        if (navire.getCodePort() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        // Récupérer le port à partir du code du port du navire
	        ResponseEntity<Port> response = clientPort.trouverUnPort(navire.getCodePort());
	        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        Port port = response.getBody();
	        navire.setCodePort(port.getCodePort());

	        // Créer le navire
	        Navire newNavire = navireDAO.creerNavire(navire);
	        return new ResponseEntity<Navire>(newNavire, HttpStatus.CREATED);
	    }
	
	//http://localhost:3100/navireapi/navires
		@GetMapping("/navires")
		public ResponseEntity<List<Navire>> afficherNavires()
		{
			List<Navire> liste = navireDAO.listerNavires();
			return new ResponseEntity <List<Navire>> (liste,HttpStatus.OK);
		}
		
		//Trouver un navire à partir du code navire
		@GetMapping("/navires/codeNavire/{codeNavire}")
		public ResponseEntity<Navire> trouverUnNavire(@PathVariable("codeNavire") Integer codeNavire)
		{
			Navire navire = navireDAO.rechercherNavire(codeNavire);
			return new ResponseEntity<Navire>(navire,HttpStatus.OK);
		}
	
		// Modifier un navire
	    @PutMapping("/navires")
	    public ResponseEntity<Navire> modifierNavire(@RequestBody Navire navire) throws Exception 
	    {
	        Navire existingNavire = navireDAO.rechercherNavire(navire.getCodeNavire());
	        if (existingNavire == null) 
	        {
	            throw new Exception("Journalier non trouvé avec la matricule : " + navire.getCodeNavire());
	        }
	        navireDAO.modifierNavire(existingNavire);
	        return new ResponseEntity<>(navire, HttpStatus.OK);
	    }
	    
	 // Supprimer un navire
	    @DeleteMapping("/navires/codenavire/{codeNavire}")
	    public ResponseEntity<Void> supprimerNavire(@PathVariable("codeNavire") Integer codeNavire) 
	    {
	        navireDAO.supprimerNavire(codeNavire);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    @GetMapping("/ports")
	    public ResponseEntity<List<Port>> listAllPorts()
	    {
	    	return clientPort.afficherPort();
	    }

}
