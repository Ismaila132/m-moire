package sn.pad.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import sn.pad.entites.Navire;


@FeignClient(url="http://localhost:3100",value="NAVIRE-SERVICE")
public interface ClientNavire 
{
	@GetMapping("/navireapi/navires")
	public ResponseEntity<List<Navire>> afficherNavires();

}
