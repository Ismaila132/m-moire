package sn.pad.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sn.pad.entites.*;
import sn.pad.repository.*;

@Service
public class PortDAO 
{
	@Autowired
	private PortRepository portRepository;
	
	public List<Port> listerPorts()
	{
		return portRepository.findAll();
	}
	
	@Transactional
	public Port creerPort(Port port)
	{
		return portRepository.save(port);
	}
	@Transactional
	public void modifierPort(Port port)
	{
		portRepository.save(port);
	}
	public Port rechercherPort(Integer codePort)
	{
		return portRepository.findById(codePort).orElse(null);
	}
	//méthode pour supprimer Port
		@Transactional
		public void supprimerPort(Integer codeNavire)
		{
			Port port = rechercherPort(codeNavire);
			if (port != null)
			{
				portRepository.delete(port);
			}
			
		}

}
