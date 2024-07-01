package sn.pad.Client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sn.pad.entites.Port;

@FeignClient(name="PORT-SERVICE")
public interface ClientPort 
{
	@GetMapping("/portapi/ports")
	public ResponseEntity<List<Port>> afficherPort();
	@GetMapping("/portapi/ports/codeport/{codePort}")
	public ResponseEntity<Port> trouverUnPort(@PathVariable("codePort") Integer codePort);
}
