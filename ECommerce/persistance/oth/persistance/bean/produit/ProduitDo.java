package oth.persistance.bean.produit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DO associé à la table produit.
 * 
 * @author Phil9175
 *
 */
@Entity(name = "ProduitDo")
@Table(name = "produit")
public class ProduitDo implements Serializable {

	private static final long serialVersionUID = 4874559697594902703L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produit_id")
	private Integer id;

	@NotNull
	@Column(unique = true, name = "produit_reference")
	@Size(min = 0, max = 255)
	private String reference;

	@NotNull
	@Column(name = "produit_description")
	@Size(min = 0, max = 255)

	private String description;

	@NotNull
	@Column(columnDefinition = "bit(1) default 1", name = "produit_envente")
	private boolean enVente;

	@NotNull
	@Column(name = "produit_prix")
	private Double prix;

	@NotNull
	@Lob
	@Column(name = "produit_photo")
	private byte[] photo;

	/**
	 * Constructeur par défaut
	 */
	public ProduitDo() {
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(final String reference) {
		this.reference = reference;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the enVente
	 */
	public boolean isEnVente() {
		return enVente;
	}

	/**
	 * @param enVente
	 *            the enVente to set
	 */
	public void setEnVente(final boolean enVente) {
		this.enVente = enVente;
	}

	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(final Double prix) {
		this.prix = prix;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(final byte[] photo) {
		this.photo = photo;
	}
}
