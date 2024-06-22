package sn.pad.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categories")
public class Categorie 
{
	@Id
	private Integer codeCategorie;
	private String nomCategorie;
	
	public Categorie()
	{
		
	}

	public Integer getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(Integer codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	
	

}
