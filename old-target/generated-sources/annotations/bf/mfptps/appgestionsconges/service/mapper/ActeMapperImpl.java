package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.MotifAbsence;
import bf.mfptps.appgestionsconges.entities.Profile;
import bf.mfptps.appgestionsconges.entities.TypeActe;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.TypeVisa;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;
import bf.mfptps.appgestionsconges.service.dto.AgentDTO;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.MotifAbsenceDTO;
import bf.mfptps.appgestionsconges.service.dto.ProfileDTO;
import bf.mfptps.appgestionsconges.service.dto.TypeActeDTO;
import bf.mfptps.appgestionsconges.service.dto.TypeDemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.TypeVisaDTO;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:05+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class ActeMapperImpl implements ActeMapper {

    @Override
    public ActeDTO toDto(Acte acte) {
        if ( acte == null ) {
            return null;
        }

        ActeDTO acteDTO = new ActeDTO();

        acteDTO.setReference( acte.getReference() );
        acteDTO.setId( acte.getId() );
        acteDTO.setEnteteMinistere( acte.getEnteteMinistere() );
        acteDTO.setStatus( acte.getStatus() );
        acteDTO.setAmpliation( acte.getAmpliation() );
        acteDTO.setTypeActe( typeActeToTypeActeDTO( acte.getTypeActe() ) );
        acteDTO.setAnnee( acte.getAnnee() );
        acteDTO.setDemandes( demandeSetToDemandeDTOSet( acte.getDemandes() ) );
        acteDTO.setNomPrenomCreator( acte.getNomPrenomCreator() );
        acteDTO.setTitreCreator( acte.getTitreCreator() );

        return acteDTO;
    }

    @Override
    public Acte toEntity(ActeDTO acteDTO) {
        if ( acteDTO == null ) {
            return null;
        }

        Acte acte = new Acte();

        acte.setReference( acteDTO.getReference() );
        acte.setId( acteDTO.getId() );
        acte.setStatus( acteDTO.getStatus() );
        acte.setAmpliation( acteDTO.getAmpliation() );
        acte.setEnteteMinistere( acteDTO.getEnteteMinistere() );
        acte.setTypeActe( typeActeDTOToTypeActe( acteDTO.getTypeActe() ) );
        acte.setAnnee( acteDTO.getAnnee() );
        acte.setDemandes( demandeDTOSetToDemandeSet( acteDTO.getDemandes() ) );
        acte.setNomPrenomCreator( acteDTO.getNomPrenomCreator() );
        acte.setTitreCreator( acteDTO.getTitreCreator() );

        return acte;
    }

    protected TypeActeDTO typeActeToTypeActeDTO(TypeActe typeActe) {
        if ( typeActe == null ) {
            return null;
        }

        TypeActeDTO typeActeDTO = new TypeActeDTO();

        typeActeDTO.setId( typeActe.getId() );
        typeActeDTO.setReference( typeActe.getReference() );
        typeActeDTO.setLibelle( typeActe.getLibelle() );
        typeActeDTO.setTemplateUri( typeActe.getTemplateUri() );
        typeActeDTO.setPorteActe( typeActe.getPorteActe() );

        return typeActeDTO;
    }

    protected MotifAbsenceDTO motifAbsenceToMotifAbsenceDTO(MotifAbsence motifAbsence) {
        if ( motifAbsence == null ) {
            return null;
        }

        MotifAbsenceDTO motifAbsenceDTO = new MotifAbsenceDTO();

        motifAbsenceDTO.setId( motifAbsence.getId() );
        motifAbsenceDTO.setLibelle( motifAbsence.getLibelle() );

        return motifAbsenceDTO;
    }

    protected TypeVisaDTO typeVisaToTypeVisaDTO(TypeVisa typeVisa) {
        if ( typeVisa == null ) {
            return null;
        }

        TypeVisaDTO typeVisaDTO = new TypeVisaDTO();

        typeVisaDTO.setCreatedBy( typeVisa.getCreatedBy() );
        typeVisaDTO.setCreatedDate( typeVisa.getCreatedDate() );
        typeVisaDTO.setLastModifiedBy( typeVisa.getLastModifiedBy() );
        typeVisaDTO.setLastModifiedDate( typeVisa.getLastModifiedDate() );
        typeVisaDTO.setDeleted( typeVisa.isDeleted() );
        typeVisaDTO.setId( typeVisa.getId() );
        typeVisaDTO.setVisa( typeVisa.getVisa() );
        typeVisaDTO.setTypeDemande( typeVisa.getTypeDemande() );
        typeVisaDTO.setNumeroOrdre( typeVisa.getNumeroOrdre() );

        return typeVisaDTO;
    }

    protected Set<TypeVisaDTO> typeVisaSetToTypeVisaDTOSet(Set<TypeVisa> set) {
        if ( set == null ) {
            return null;
        }

        Set<TypeVisaDTO> set1 = new HashSet<TypeVisaDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TypeVisa typeVisa : set ) {
            set1.add( typeVisaToTypeVisaDTO( typeVisa ) );
        }

        return set1;
    }

    protected TypeDemandeDTO typeDemandeToTypeDemandeDTO(TypeDemande typeDemande) {
        if ( typeDemande == null ) {
            return null;
        }

        TypeDemandeDTO typeDemandeDTO = new TypeDemandeDTO();

        typeDemandeDTO.setId( typeDemande.getId() );
        typeDemandeDTO.setLibelle( typeDemande.getLibelle() );
        typeDemandeDTO.setModePaie( typeDemande.getModePaie() );
        typeDemandeDTO.setDescription( typeDemande.getDescription() );
        typeDemandeDTO.setTypeVisas( typeVisaSetToTypeVisaDTOSet( typeDemande.getTypeVisas() ) );
        typeDemandeDTO.setSoldeAnnuel( typeDemande.getSoldeAnnuel() );
        typeDemandeDTO.setCode( typeDemande.getCode() );
        typeDemandeDTO.setRemoteValue( typeDemande.getRemoteValue() );

        return typeDemandeDTO;
    }

    protected ProfileDTO profileToProfileDTO(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setId( profile.getId() );
        profileDTO.setName( profile.getName() );
        profileDTO.setNativeProfile( profile.isNativeProfile() );

        return profileDTO;
    }

    protected Set<ProfileDTO> profileSetToProfileDTOSet(Set<Profile> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProfileDTO> set1 = new HashSet<ProfileDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Profile profile : set ) {
            set1.add( profileToProfileDTO( profile ) );
        }

        return set1;
    }

    protected AgentDTO agentToAgentDTO(Agent agent) {
        if ( agent == null ) {
            return null;
        }

        AgentDTO agentDTO = new AgentDTO();

        agentDTO.setId( agent.getId() );
        agentDTO.setMatricule( agent.getMatricule() );
        agentDTO.setNom( agent.getNom() );
        agentDTO.setPrenom( agent.getPrenom() );
        agentDTO.setActif( agent.isActif() );
        agentDTO.setCreatedBy( agent.getCreatedBy() );
        agentDTO.setCreatedDate( agent.getCreatedDate() );
        agentDTO.setLastModifiedBy( agent.getLastModifiedBy() );
        agentDTO.setLastModifiedDate( agent.getLastModifiedDate() );
        agentDTO.setTelephone( agent.getTelephone() );
        agentDTO.setProfiles( profileSetToProfileDTOSet( agent.getProfiles() ) );
        agentDTO.setEmail( agent.getEmail() );
        agentDTO.setMatriculeResp( agent.getMatriculeResp() );

        return agentDTO;
    }

    protected DemandeDTO demandeToDemandeDTO(Demande demande) {
        if ( demande == null ) {
            return null;
        }

        DemandeDTO demandeDTO = new DemandeDTO();

        demandeDTO.setMotifRejet( demande.getMotifRejet() );
        demandeDTO.setId( demande.getId() );
        demandeDTO.setNumeroDemande( demande.getNumeroDemande() );
        demandeDTO.setLieuJouissanceBF( demande.getLieuJouissanceBF() );
        demandeDTO.setLieuJouissanceEtrang( demande.getLieuJouissanceEtrang() );
        demandeDTO.setRefLastDecision( demande.getRefLastDecision() );
        demandeDTO.setSituationSND( demande.getSituationSND() );
        demandeDTO.setDureeAbsence( demande.getDureeAbsence() );
        demandeDTO.setPeriodeDebut( demande.getPeriodeDebut() );
        demandeDTO.setPeriodeFin( demande.getPeriodeFin() );
        demandeDTO.setMotifAbsence( motifAbsenceToMotifAbsenceDTO( demande.getMotifAbsence() ) );
        demandeDTO.setTypeDemande( typeDemandeToTypeDemandeDTO( demande.getTypeDemande() ) );
        demandeDTO.setAgent( agentToAgentDTO( demande.getAgent() ) );
        demandeDTO.setStatusDemande( demande.getStatusDemande() );
        demandeDTO.setPositionDemande( demande.getPositionDemande() );
        demandeDTO.setTrancheDemande( demande.getTrancheDemande() );

        return demandeDTO;
    }

    protected Set<DemandeDTO> demandeSetToDemandeDTOSet(Set<Demande> set) {
        if ( set == null ) {
            return null;
        }

        Set<DemandeDTO> set1 = new HashSet<DemandeDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Demande demande : set ) {
            set1.add( demandeToDemandeDTO( demande ) );
        }

        return set1;
    }

    protected TypeActe typeActeDTOToTypeActe(TypeActeDTO typeActeDTO) {
        if ( typeActeDTO == null ) {
            return null;
        }

        TypeActe typeActe = new TypeActe();

        typeActe.setId( typeActeDTO.getId() );
        typeActe.setReference( typeActeDTO.getReference() );
        typeActe.setLibelle( typeActeDTO.getLibelle() );
        typeActe.setTemplateUri( typeActeDTO.getTemplateUri() );
        typeActe.setPorteActe( typeActeDTO.getPorteActe() );

        return typeActe;
    }

    protected MotifAbsence motifAbsenceDTOToMotifAbsence(MotifAbsenceDTO motifAbsenceDTO) {
        if ( motifAbsenceDTO == null ) {
            return null;
        }

        MotifAbsence motifAbsence = new MotifAbsence();

        motifAbsence.setId( motifAbsenceDTO.getId() );
        motifAbsence.setLibelle( motifAbsenceDTO.getLibelle() );

        return motifAbsence;
    }

    protected TypeVisa typeVisaDTOToTypeVisa(TypeVisaDTO typeVisaDTO) {
        if ( typeVisaDTO == null ) {
            return null;
        }

        TypeVisa typeVisa = new TypeVisa();

        typeVisa.setCreatedBy( typeVisaDTO.getCreatedBy() );
        typeVisa.setCreatedDate( typeVisaDTO.getCreatedDate() );
        typeVisa.setLastModifiedBy( typeVisaDTO.getLastModifiedBy() );
        typeVisa.setLastModifiedDate( typeVisaDTO.getLastModifiedDate() );
        typeVisa.setDeleted( typeVisaDTO.isDeleted() );
        typeVisa.setId( typeVisaDTO.getId() );
        typeVisa.setVisa( typeVisaDTO.getVisa() );
        typeVisa.setTypeDemande( typeVisaDTO.getTypeDemande() );
        typeVisa.setNumeroOrdre( typeVisaDTO.getNumeroOrdre() );

        return typeVisa;
    }

    protected Set<TypeVisa> typeVisaDTOSetToTypeVisaSet(Set<TypeVisaDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<TypeVisa> set1 = new HashSet<TypeVisa>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TypeVisaDTO typeVisaDTO : set ) {
            set1.add( typeVisaDTOToTypeVisa( typeVisaDTO ) );
        }

        return set1;
    }

    protected TypeDemande typeDemandeDTOToTypeDemande(TypeDemandeDTO typeDemandeDTO) {
        if ( typeDemandeDTO == null ) {
            return null;
        }

        TypeDemande typeDemande = new TypeDemande();

        typeDemande.setId( typeDemandeDTO.getId() );
        typeDemande.setLibelle( typeDemandeDTO.getLibelle() );
        typeDemande.setModePaie( typeDemandeDTO.getModePaie() );
        typeDemande.setDescription( typeDemandeDTO.getDescription() );
        typeDemande.setTypeVisas( typeVisaDTOSetToTypeVisaSet( typeDemandeDTO.getTypeVisas() ) );
        typeDemande.setSoldeAnnuel( typeDemandeDTO.getSoldeAnnuel() );
        typeDemande.setCode( typeDemandeDTO.getCode() );
        typeDemande.setRemoteValue( typeDemandeDTO.getRemoteValue() );

        return typeDemande;
    }

    protected Profile profileDTOToProfile(ProfileDTO profileDTO) {
        if ( profileDTO == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setId( profileDTO.getId() );
        profile.setName( profileDTO.getName() );

        return profile;
    }

    protected Set<Profile> profileDTOSetToProfileSet(Set<ProfileDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Profile> set1 = new HashSet<Profile>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProfileDTO profileDTO : set ) {
            set1.add( profileDTOToProfile( profileDTO ) );
        }

        return set1;
    }

    protected Agent agentDTOToAgent(AgentDTO agentDTO) {
        if ( agentDTO == null ) {
            return null;
        }

        Agent agent = new Agent();

        agent.setCreatedBy( agentDTO.getCreatedBy() );
        agent.setCreatedDate( agentDTO.getCreatedDate() );
        agent.setLastModifiedBy( agentDTO.getLastModifiedBy() );
        agent.setLastModifiedDate( agentDTO.getLastModifiedDate() );
        agent.setId( agentDTO.getId() );
        agent.setMatricule( agentDTO.getMatricule() );
        agent.setEmail( agentDTO.getEmail() );
        agent.setNom( agentDTO.getNom() );
        agent.setPrenom( agentDTO.getPrenom() );
        agent.setActif( agentDTO.isActif() );
        agent.setTelephone( agentDTO.getTelephone() );
        agent.setProfiles( profileDTOSetToProfileSet( agentDTO.getProfiles() ) );
        agent.setMatriculeResp( agentDTO.getMatriculeResp() );

        return agent;
    }

    protected Demande demandeDTOToDemande(DemandeDTO demandeDTO) {
        if ( demandeDTO == null ) {
            return null;
        }

        Demande demande = new Demande();

        demande.setId( demandeDTO.getId() );
        demande.setNumeroDemande( demandeDTO.getNumeroDemande() );
        demande.setLieuJouissanceBF( demandeDTO.getLieuJouissanceBF() );
        demande.setLieuJouissanceEtrang( demandeDTO.getLieuJouissanceEtrang() );
        demande.setRefLastDecision( demandeDTO.getRefLastDecision() );
        demande.setSituationSND( demandeDTO.getSituationSND() );
        demande.setDureeAbsence( demandeDTO.getDureeAbsence() );
        demande.setPeriodeDebut( demandeDTO.getPeriodeDebut() );
        demande.setPeriodeFin( demandeDTO.getPeriodeFin() );
        demande.setMotifAbsence( motifAbsenceDTOToMotifAbsence( demandeDTO.getMotifAbsence() ) );
        demande.setTypeDemande( typeDemandeDTOToTypeDemande( demandeDTO.getTypeDemande() ) );
        demande.setAgent( agentDTOToAgent( demandeDTO.getAgent() ) );
        demande.setStatusDemande( demandeDTO.getStatusDemande() );
        demande.setPositionDemande( demandeDTO.getPositionDemande() );
        demande.setTrancheDemande( demandeDTO.getTrancheDemande() );
        demande.setMotifRejet( demandeDTO.getMotifRejet() );

        return demande;
    }

    protected Set<Demande> demandeDTOSetToDemandeSet(Set<DemandeDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Demande> set1 = new HashSet<Demande>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( DemandeDTO demandeDTO : set ) {
            set1.add( demandeDTOToDemande( demandeDTO ) );
        }

        return set1;
    }
}
