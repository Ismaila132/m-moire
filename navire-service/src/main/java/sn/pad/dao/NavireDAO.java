package sn.pad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sn.pad.entites.Navire;
import sn.pad.repository.NavireRepository;

@Service
public class NavireDAO 
{
	@Autowired
	private NavireRepository navireRepository;
	
	public List<Navire> listerNavires()
	{
		return navireRepository.findAll();
	}
	@Transactional
	public Navire creerNavire(Navire navire)
	{
		return navireRepository.save(navire);
	}
	@Transactional
	public void modifierNavire(Navire navire)
	{
		navireRepository.save(navire);
	}
	
	//méthode pour rechercher journalier
	public Navire rechercherNavire(Integer codeNavire)
	{
		return navireRepository.findById(codeNavire).orElse(null);
	}
	
	//méthode pour supprimer journalier
	@Transactional
	public void supprimerNavire(Integer codeNavire)
	{
		Navire navire =rechercherNavire(codeNavire);
		if (navire != null)
		{
			navireRepository.delete(navire);
		}
		
	}

}
