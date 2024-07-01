package sn.pad.entites;

import java.io.Serializable;

//DTO navire
public class Navire implements Serializable
{
	
	private Integer codeNavire;
	private String nomNavire;
	private String compagnieNavire;
	private Integer codePort;
	
	public Navire()
	{
		
	}

	public Integer getCodeNavire() {
		return codeNavire;
	}

	public void setCodeNavire(Integer codeNavire) {
		this.codeNavire = codeNavire;
	}

	public String getNomNavire() {
		return nomNavire;
	}

	public void setNomNavire(String nomNavire) {
		this.nomNavire = nomNavire;
	}

	public String getCompagnieNavire() {
		return compagnieNavire;
	}

	public void setCompagnieNavire(String compagnieNavire) {
		this.compagnieNavire = compagnieNavire;
	}

	public Integer getCodePort() {
		return codePort;
	}

	public void setCodePort(Integer codePort) {
		this.codePort = codePort;
	}

}
