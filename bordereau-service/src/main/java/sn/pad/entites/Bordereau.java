package sn.pad.entites;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="bordereau")
public class Bordereau implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codeBordereau;
	@Transient
	private Integer matriculeJournalier;
	@Transient
	private String prenomJournalier;
	@Transient
	private String nomJournalier;
	@Transient
	private Integer codePort;
	@Transient
	private Integer codeNavire;
	@Transient
	private Integer codeCategorie;
	private LocalDate dateEmbauche;
	private LocalTime heureEmbauche;
	
	public Bordereau() {}

	public Integer getCodeBordereau() {
		return codeBordereau;
	}

	public void setCodeBordereau(Integer codeBordereau) {
		this.codeBordereau = codeBordereau;
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

	public void setPrenomJournalier(String prenomJournalier) {
		this.prenomJournalier = prenomJournalier;
	}

	public String getNomJournalier() {
		return nomJournalier;
	}

	public void setNomJournalier(String nomJournalier) {
		this.nomJournalier = nomJournalier;
	}

	public Integer getCodePort() {
		return codePort;
	}

	public void setCodePort(Integer codePort) {
		this.codePort = codePort;
	}

	public Integer getCodeNavire() {
		return codeNavire;
	}

	public void setCodeNavire(Integer codeNavire) {
		this.codeNavire = codeNavire;
	}

	public Integer getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(Integer codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public LocalTime getHeureEmbauche() {
		return heureEmbauche;
	}

	public void setHeureEmbauche(LocalTime heureEmbauche) {
		this.heureEmbauche = heureEmbauche;
	}
	
}
