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


import sn.pad.dao.*;
import sn.pad.entites.*;
import sn.pad.metiers.PortProcessing;

@RestController
//toutes les requetes sont préfixés par l'URL de base suivante:
//http://localhost:3200/portapi/...
@RequestMapping("/portapi")
public class PortControlleur 
{
	@Autowired
	private PortProcessing portprocessing;
	@Autowired
	private PortDAO portDAO;
	
	//http://localhost:3200/portapi/ports
	@PostMapping("/ports")
	public ResponseEntity<Port> ajouterNavire(@RequestBody Port port)
	{
		Port newPort = portDAO.creerPort(port);
		return new ResponseEntity<Port> (newPort,HttpStatus.CREATED);
	}
	
	//http://localhost:3200/portapi/ports
			@GetMapping("/ports")
			public ResponseEntity<List<Port>> afficherPort()
			{
				List<Port> liste = portDAO.listerPorts();
				return new ResponseEntity <List<Port>> (liste,HttpStatus.OK);
			}
			// Rechercher port par id
			@GetMapping("/ports/codeport/{codePort}")
			public ResponseEntity<Port> trouverUnNavire(@PathVariable("codePort") Integer codePort)
			{
				Port port = portDAO.rechercherPort(codePort);
				return new ResponseEntity<Port>(port,HttpStatus.OK);
			}
			//Modifier port
			//http://localhost:3200/portapi/ports
			@PutMapping("/ports")
		    public ResponseEntity<Port> modifierJournalier(@RequestBody Port port) throws Exception 
		    {
		        Port existingPort = portDAO.rechercherPort(port.getCodePort());
		        if (existingPort == null) 
		        {
		            throw new Exception("Port non trouvé avec le code : " + port.getCodePort());
		        }
		        portDAO.modifierPort(existingPort);
		        return new ResponseEntity<>(port, HttpStatus.OK);
		    }
		    
		 // Supprimer un navire
			@DeleteMapping("/ports/codeport/{codePort}")
		    public ResponseEntity<Void> supprimerPort(@PathVariable("codePort") Integer codePort) 
		    {
		        portDAO.supprimerPort(codePort);
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		

}
