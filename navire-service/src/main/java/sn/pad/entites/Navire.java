package sn.pad.entites;

import java.io.*;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="navires")
public class Navire implements Serializable
{
	@Id
	private Integer codeNavire;
	private String nomNavire;
	private String compagnieNavire;
	@Transient
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
