package sn.pad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sn.pad.entites.Categorie;
import sn.pad.repository.CategorieRepository;

@Service
public class CategorieDAO 
{
	@Autowired
	private CategorieRepository categorieRepository;
	
	public List<Categorie> listerCategories()
	{
		return categorieRepository.findAll();
	}
	@Transactional
	public Categorie creerCategorie(Categorie categorie)
	{
		return categorieRepository.save(categorie);
	}
	@Transactional
	public void modifierCategorie(Categorie categorie)
	{
		categorieRepository.save(categorie);
	}
	//rechercher categorie par id
	public Categorie rechercherCategorie(Integer codeCategorie)
	{
		return categorieRepository.findById(codeCategorie).orElse(null);
	}
	//méthode pour supprimer categorie
			@Transactional
			public void supprimerCategorie(Integer codeCategorie)
			{
				Categorie categorie = rechercherCategorie(codeCategorie);
				if (categorie != null)
				{
					categorieRepository.delete(categorie);
				}
				
			}
	

}
