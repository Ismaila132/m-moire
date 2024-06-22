package sn.pad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.pad.entites.*;
import sn.pad.repository.BordereauRepository;


@Service
public class BordereauDAO 
{
	@Autowired
	private BordereauRepository bordereauRepository;
	
	public List<Bordereau> listerBordereaux()
	{
		return bordereauRepository.findAll();
	}
	@Transactional
	public Bordereau creerBordereau(Bordereau bordereau)
	{
		return bordereauRepository.save(bordereau);
	}
	@Transactional
	public void modifierBordereau(Bordereau bordereau)
	{
		bordereauRepository.save(bordereau);
	}
	//rechercher categorie par id
		public Bordereau rechercherBordereau(Integer codeBordereau)
		{
			return bordereauRepository.findById(codeBordereau).orElse(null);
		}
	// rechercher bordereau par matricule journalier
		public Bordereau rechercherParMatricule(Integer matriculeJournalier)
		{
			return bordereauRepository.findBymatriculeJournalier(matriculeJournalier);
		}
		//méthode pour supprimer bordereau à partir du matricule
		@Transactional
		public void supprimerBordereauParMatricule(Integer matriculeJournalier)
		{
			Bordereau bordereau = rechercherParMatricule(matriculeJournalier);
			if (bordereau != null)
			{
				bordereauRepository.delete(bordereau);
			}
			
		}
		// méthode pour supprimer à partir de codebordereau
		public void supprimerBordereau(Integer codeBordereau)
		{
			Bordereau bordereau = rechercherBordereau(codeBordereau);
			if (bordereau != null)
			{
				bordereauRepository.delete(bordereau);
			}
			
		}
		
}
