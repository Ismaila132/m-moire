package sn.pad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import sn.pad.entites.*;

@Service
public interface NavireRepository extends JpaRepository<Navire,Integer>
{
	

}
