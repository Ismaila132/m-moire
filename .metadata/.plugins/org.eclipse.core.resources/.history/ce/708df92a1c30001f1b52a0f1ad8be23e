package sn.pad.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sn.pad.dao.BordereauDAO;
import sn.pad.entites.Bordereau;


public class BordereauProcessing 
{
	@Autowired
	private BordereauDAO bordereauDAO;
	
	public Integer compter()
	{
		List<Bordereau> liste = bordereauDAO.listerBordereaux();
		Integer compteur=0;
		for (Bordereau x : liste)
		{
			compteur++;
		}
		return compteur;
	}
}
