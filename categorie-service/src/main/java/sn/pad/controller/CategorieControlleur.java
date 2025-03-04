package sn.pad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.pad.dao.*;
import sn.pad.entites.*;



@RestController
//toutes les requetes sont préfixés par l'URL de base suivante:
//http://localhost:3300/categorieapi/...
@RequestMapping("/categorieapi")
public class CategorieControlleur 
{
	@Autowired
	private CategorieDAO categorieDAO;
	
	
	//http://localhost:3300/categorieapi/categories
		@PostMapping("/categories")
		public ResponseEntity<Categorie> ajouterCategorie(@RequestBody Categorie categorie)
		{
			Categorie newCategorie = categorieDAO.creerCategorie(categorie);
			return new ResponseEntity<Categorie> (newCategorie,HttpStatus.CREATED);
		}
		//http://localhost:3300/categorieapi/categories
		@GetMapping("/categories")
		public ResponseEntity<List<Categorie>> afficherCategorie()
		{
			List<Categorie> liste = categorieDAO.listerCategories();
			return new ResponseEntity <List<Categorie>> (liste,HttpStatus.OK);
		}
		// Rechercher categorie par id
		@GetMapping("/categories/codecategorie/{codeCategorie}")
		public ResponseEntity<Categorie> trouverUneCategorie(@PathVariable("codeCategorie") Integer codeCategorie)
		{
			Categorie categorie = categorieDAO.rechercherCategorie(codeCategorie);
			return new ResponseEntity<Categorie>(categorie,HttpStatus.OK);
		}
		//Modifier categorie
		//http://localhost:3300/categorieapi/categories
		@PutMapping("/categories")
	    public ResponseEntity<Categorie> modifierCategorie(@RequestBody Categorie categorie) throws Exception 
	    {
	        Categorie existingCategorie = categorieDAO.rechercherCategorie(categorie.getCodeCategorie());
	        if (existingCategorie == null) 
	        {
	            throw new Exception("Categorie non trouvé avec le code : " + categorie.getCodeCategorie());
	        }
	        categorieDAO.modifierCategorie(existingCategorie);
	        return new ResponseEntity<>(categorie, HttpStatus.OK);
	    }
		


}
