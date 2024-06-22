package sn.pad.metier;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sn.pad.dao.NavireDAO;
import sn.pad.entites.Navire;

@Service
public class NavireProcessing 
{
    private static final Logger logger = LoggerFactory.getLogger(NavireProcessing.class);

    @Autowired
    private NavireDAO navireDAO;
    
    public Integer compter()
    {
        try {
            List<Navire> liste = navireDAO.listerNavires();
            return liste.size();
        } catch (Exception e) {
            logger.error("Erreur lors du comptage des navires", e);
            return 0;
        }
    }
}

