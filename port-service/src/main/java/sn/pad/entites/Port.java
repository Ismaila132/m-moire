package sn.pad.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="port")
public class Port
{
	@Id
	private Integer codePort;
	private String nomPort;
	
	public Port()
	{
		
	}

	public Integer getCodePort() {
		return codePort;
	}

	public void setCodePort(Integer codePort) {
		this.codePort = codePort;
	}

	public String getNomPort() {
		return nomPort;
	}

	public void setNomPort(String nomPort) {
		this.nomPort = nomPort;
	}
	
	

}
