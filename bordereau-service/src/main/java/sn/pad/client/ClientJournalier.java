package sn.pad.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sn.pad.entites.*;


@FeignClient(name="JOURNALIER-SERVICE")
public interface ClientJournalier 
{
	@GetMapping("/journalierapi/journaliers")
	public ResponseEntity<List<Journalier>> afficherJournaliers();
	
	@GetMapping("/journalierapi/journaliers/matricule/{matriculeJournalier}")
    ResponseEntity<Journalier> trouverJournalierParMatricule(@PathVariable("matriculeJournalier") Integer matriculeJournalier);
}
