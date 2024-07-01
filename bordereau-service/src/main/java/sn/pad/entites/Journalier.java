package sn.pad.entites;

import java.io.Serializable;





//DTO de journalier
public class Journalier implements Serializable
{
	 
	private Integer matriculeJournalier;
	private String prenomJournalier;
	private String nomJournalier;
	private Integer telJournalier;
	private Integer codeCategorie;
	
	
	public Integer getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(Integer codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public Journalier() 
	{
		
	}

	public Integer getMatriculeJournalier() {
		return matriculeJournalier;
	}

	public void setMatriculeJournalier(Integer matriculeJournalier) {
		this.matriculeJournalier = matriculeJournalier;
	}

	public String getPrenomJournalier() {
		return prenomJournalier;
	}

	public void setPrenomJournalier(String prenomJournalier) 
	{
		this.prenomJournalier = prenomJournalier;
	}

	public String getNomJournalier() 
	{
		return nomJournalier;
	}

	public void setNomJournalier(String nomJournalier) 
	{
		this.nomJournalier = nomJournalier;
	}

	public Integer getTelJournalier() 
	{
		return telJournalier;
	}

	public void setTelJournalier(Integer telJournalier)
	{
		this.telJournalier = telJournalier;
	}


}
