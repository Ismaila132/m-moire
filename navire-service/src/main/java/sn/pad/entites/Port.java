package sn.pad.entites;

import java.io.Serializable;

//DTO (data access objet) objet de port
public class Port implements Serializable 
{
	
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
