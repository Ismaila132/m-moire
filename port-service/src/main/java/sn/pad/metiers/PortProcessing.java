package sn.pad.metiers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sn.pad.dao.*;
import sn.pad.entites.Port;


@Service
public class PortProcessing 
{
	private static final Logger logger = LoggerFactory.getLogger(PortProcessing.class);
	private PortDAO portDAO;
	
	public Integer compter()
	{
		 try {
	            List<Port> liste = portDAO.listerPorts();
	            return liste.size();
	        } catch (Exception e) {
	            logger.error("Erreur lors du comptage des navires", e);
	            return 0;
	        }
	}

}
