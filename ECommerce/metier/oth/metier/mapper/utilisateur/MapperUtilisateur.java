package oth.metier.mapper.utilisateur;

import java.util.ArrayList;
import java.util.List;

import oth.metier.exception.MappingException;
import oth.persistance.bean.utilisateur.UtilisateurDo;
import oth.presentation.dto.utilisateur.EditInfoPersoByAdminDto;
import oth.presentation.dto.utilisateur.EditInfoPersoDto;
import oth.presentation.dto.utilisateur.InscriptionDto;
import oth.presentation.dto.utilisateur.ListeUtilisateurDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;

/**
 * Mapper des DTO liés à l'utilisateur.
 * 
 * @author Phil9175
 * 
 */

public class MapperUtilisateur {

	/**
	 * Créé le DTO utilisateur associé à un DO utilisateur.
	 * 
	 * @param utilisateurBean
	 *            Le DO utilisateur à mapper.
	 * @return Le DTO utilisateur mappé.
	 */
	public static UtilisateurDto createUtilisateurDto(final UtilisateurDo utilisateurBean) {
		if (utilisateurBean == null) {
			return null;
		}

		final UtilisateurDto utilisateur = new UtilisateurDto();

		utilisateur.setPrenom(utilisateurBean.getPrenom());
		utilisateur.setAdresse(utilisateurBean.getAdresse());
		utilisateur.setDateNaissance(utilisateurBean.getDateNaissance());
		utilisateur.setId(utilisateurBean.getId());
		utilisateur.setLogin(utilisateurBean.getLogin());
		utilisateur.setMail(utilisateurBean.getMail());
		utilisateur.setNom(utilisateurBean.getNom());
		utilisateur.setNomProfil(utilisateurBean.getProfil().getNom());
		utilisateur.setActif(utilisateurBean.isActive());

		return utilisateur;

	}

	/**
	 * Créé le DO utilisateur associé à un DTO utilisateur.
	 * 
	 * @param utilisateurDto
	 *            Le DTO utilisateur à mapper.
	 * @return Le DO utilisateur mappé.
	 */
	public static UtilisateurDo createUtilisateurDo(final UtilisateurDto utilisateurDto) {
		if (utilisateurDto == null) {
			return null;
		}
		final UtilisateurDo utilisateur = new UtilisateurDo();

		utilisateur.setPrenom(utilisateurDto.getPrenom());
		utilisateur.setAdresse(utilisateurDto.getAdresse());
		utilisateur.setDateNaissance(utilisateurDto.getDateNaissance());
		utilisateur.setId(utilisateurDto.getId());
		utilisateur.setLogin(utilisateurDto.getLogin());
		utilisateur.setMail(utilisateurDto.getMail());
		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setProfil(null);
		utilisateur.setActive(utilisateurDto.isActif());

		return utilisateur;
	}

	/**
	 * Créé le DO utilisateur associé à un DTO inscription.
	 * 
	 * @param inscriptionDto
	 *            Le DTO inscription à mapper.
	 * @return L'utilisateur DO mappé. si les mots de passe (confirmation + mot
	 *         de passe) sont différents alors une MappingException est
	 *         déclenchée
	 * @throws MappingException
	 */
	public static UtilisateurDo createUtilisateurDo(final InscriptionDto inscriptionDto) throws MappingException {
		if (inscriptionDto == null) {
			return null;
		}

		final UtilisateurDo utilisateur = new UtilisateurDo();
		if (inscriptionDto.getMdp() != null && inscriptionDto.getConfMotDePasse().equals(inscriptionDto.getMdp())) {
			if (!inscriptionDto.getConfMotDePasse().equals(inscriptionDto.getMdp())) {
				throw new MappingException("validator.password.different");
			}
			utilisateur.setAdresse(inscriptionDto.getAdresse());
			utilisateur.setDateNaissance(inscriptionDto.getDateNaissance());
			utilisateur.setLogin(inscriptionDto.getLogin());
			utilisateur.setMail(inscriptionDto.getMail());

			utilisateur.setMotdepasse(inscriptionDto.getMdp());
			utilisateur.setNom(inscriptionDto.getNom());
			utilisateur.setPrenom(inscriptionDto.getPrenom());

			return utilisateur;
		}
		return null;
	}

	/**
	 * Créé le DTO d'édition des informations par l'administrateur associé à un
	 * DO utilisateur.
	 * 
	 * @param utilisateurDo
	 *            Le DO utilisateur à mapper.
	 * @return Le DTO d'édition des informations par l'administrateur mappé.
	 */
	public static EditInfoPersoByAdminDto createEditInfoPersoByAdminDto(final UtilisateurDo utilisateurDo) {
		if (utilisateurDo == null) {
			return null;
		}

		final EditInfoPersoByAdminDto edit = new EditInfoPersoByAdminDto();
		edit.setAdresse(utilisateurDo.getAdresse());
		edit.setDateNaissance(utilisateurDo.getDateNaissance());
		edit.setLogin(utilisateurDo.getLogin());
		edit.setMail(utilisateurDo.getMail());
		edit.setNom(utilisateurDo.getNom());
		edit.setNomProfil(utilisateurDo.getProfil().getNom());
		edit.setPrenom(utilisateurDo.getPrenom());
		return edit;

	}

	/**
	 * Créé le DO utilisateur associé à un DTO d'édition des informations par
	 * l'administrateur.
	 * 
	 * @param editInfoPersoByAdminDto
	 *            Le DTO d'édition des informations par l'administrateur à
	 *            mapper.
	 * @return L'utilisateur DO mappé.
	 * @throws MappingException
	 */
	public static UtilisateurDo createUtilisateurDo(final EditInfoPersoByAdminDto editInfoPersoByAdminDto)
			throws MappingException {

		if (editInfoPersoByAdminDto == null) {
			return null;
		}
		final UtilisateurDo utilisateur = new UtilisateurDo();
		utilisateur.setDateNaissance(editInfoPersoByAdminDto.getDateNaissance());

		if (!editInfoPersoByAdminDto.getNouveauMdp().equals(editInfoPersoByAdminDto.getNouveauMdpConfirmation())) {
			throw new MappingException("validator.password.different");
		}
		utilisateur.setMotdepasse(editInfoPersoByAdminDto.getNouveauMdp());
		utilisateur.setAdresse(editInfoPersoByAdminDto.getAdresse());
		utilisateur.setLogin(editInfoPersoByAdminDto.getLogin());
		utilisateur.setMail(editInfoPersoByAdminDto.getMail());
		utilisateur.setNom(editInfoPersoByAdminDto.getNom());
		utilisateur.setPrenom(editInfoPersoByAdminDto.getPrenom());
		utilisateur.setProfil(null);

		return utilisateur;
	}

	/**
	 * Créé le DTO d'édition des informations personnelles associé à un DO
	 * utilisateur.
	 * 
	 * @param utilisateurDo
	 *            Le DO utilisateur à mapper.
	 * @return Le DTO d'édition des informations personnelles mappé.
	 */
	public static EditInfoPersoDto createEditInfoPersoDto(final UtilisateurDo utilisateurDo) {
		if (utilisateurDo == null) {
			return null;
		}

		final EditInfoPersoDto edit = new EditInfoPersoDto();

		edit.setAdresse(utilisateurDo.getAdresse());
		edit.setAncienMdp(utilisateurDo.getMotdepasse());
		edit.setDateNaissance(utilisateurDo.getDateNaissance());
		edit.setLogin(utilisateurDo.getLogin());
		edit.setMail(utilisateurDo.getMail());
		edit.setNom(utilisateurDo.getNom());
		edit.setNomProfil(utilisateurDo.getProfil().getNom());
		edit.setPrenom(utilisateurDo.getPrenom());

		return edit;
	}

	/**
	 * Créé le DO utilisateur associé à un DTO d'édition des informations
	 * personnelles.
	 * 
	 * @param editInfoPersoByAdminDto
	 *            Le DTO d'édition des informations par l'administrateur à
	 *            mapper.
	 * @return L'utilisateur DO mappé.
	 */
	public static UtilisateurDo createUtilisateurDo(final EditInfoPersoDto editInfoPersoDto) {
		if (editInfoPersoDto == null) {
			return null;
		}

		final UtilisateurDo utilisateur = new UtilisateurDo();
		utilisateur.setAdresse(editInfoPersoDto.getAdresse());
		utilisateur.setDateNaissance(editInfoPersoDto.getDateNaissance());
		utilisateur.setLogin(editInfoPersoDto.getLogin());
		utilisateur.setMail(editInfoPersoDto.getMail());
		utilisateur.setMotdepasse(editInfoPersoDto.getNouveauMdp());
		utilisateur.setNom(editInfoPersoDto.getNom());
		utilisateur.setPrenom(editInfoPersoDto.getPrenom());

		return utilisateur;

	}

	/**
	 * Créé le DTO liste d'utilisateur associé à la liste des DO Utilisateur.
	 * 
	 * @param listeUtilisateurBean
	 *            La liste de DO utilisateur à mapper.
	 * @return Le DTO liste utilisateur mappé.
	 */
	public static ListeUtilisateurDto createListeUtilisateurDto(final List<UtilisateurDo> listeUtilisateurBean) {
		if (listeUtilisateurBean == null || listeUtilisateurBean.isEmpty()) {
			return new ListeUtilisateurDto();
		}

		final ListeUtilisateurDto listeUtilisateurDto = new ListeUtilisateurDto();
		final List<UtilisateurDto> liste = new ArrayList<UtilisateurDto>();
		for (UtilisateurDo user : listeUtilisateurBean) {
			liste.add(createUtilisateurDto(user));
		}
		listeUtilisateurDto.setListUtilisateur(liste);
		return listeUtilisateurDto;

	}
}
