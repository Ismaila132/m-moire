package sn.pad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.pad.entites.*;

public interface BordereauRepository extends JpaRepository<Bordereau,Integer>
{

	Bordereau findBymatriculeJournalier(Integer matriculeJournalier);

}
