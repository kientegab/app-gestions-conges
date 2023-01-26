package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.ModalitePaie;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.TypeVisa;
import bf.mfptps.appgestionsconges.service.dto.ModalitePaieDTO;
import bf.mfptps.appgestionsconges.service.dto.TypeDemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.TypeVisaDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class ModalitePaieMapperImpl implements ModalitePaieMapper {

    @Override
    public List<ModalitePaie> toEntity(List<ModalitePaieDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ModalitePaie> list = new ArrayList<ModalitePaie>( dtoList.size() );
        for ( ModalitePaieDTO modalitePaieDTO : dtoList ) {
            list.add( toEntity( modalitePaieDTO ) );
        }

        return list;
    }

    @Override
    public List<ModalitePaieDTO> toDto(List<ModalitePaie> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ModalitePaieDTO> list = new ArrayList<ModalitePaieDTO>( entityList.size() );
        for ( ModalitePaie modalitePaie : entityList ) {
            list.add( toDto( modalitePaie ) );
        }

        return list;
    }

    @Override
    public ModalitePaieDTO toDto(ModalitePaie modalitePaie) {
        if ( modalitePaie == null ) {
            return null;
        }

        ModalitePaieDTO modalitePaieDTO = new ModalitePaieDTO();

        modalitePaieDTO.setCreatedBy( modalitePaie.getCreatedBy() );
        modalitePaieDTO.setCreatedDate( modalitePaie.getCreatedDate() );
        modalitePaieDTO.setLastModifiedBy( modalitePaie.getLastModifiedBy() );
        modalitePaieDTO.setLastModifiedDate( modalitePaie.getLastModifiedDate() );
        modalitePaieDTO.setDeleted( modalitePaie.isDeleted() );
        modalitePaieDTO.setId( modalitePaie.getId() );
        modalitePaieDTO.setCode( modalitePaie.getCode() );
        modalitePaieDTO.setLibelle( modalitePaie.getLibelle() );
        modalitePaieDTO.setRemoteID( modalitePaie.getRemoteID() );
        modalitePaieDTO.setDefaultPRMID( modalitePaie.getDefaultPRMID() );
        modalitePaieDTO.setTypeDemande( typeDemandeToTypeDemandeDTO( modalitePaie.getTypeDemande() ) );
        modalitePaieDTO.setDesactiver( modalitePaie.isDesactiver() );

        return modalitePaieDTO;
    }

    @Override
    public ModalitePaie toEntity(ModalitePaieDTO modalitePaieDTO) {
        if ( modalitePaieDTO == null ) {
            return null;
        }

        ModalitePaie modalitePaie = new ModalitePaie();

        modalitePaie.setCreatedBy( modalitePaieDTO.getCreatedBy() );
        modalitePaie.setCreatedDate( modalitePaieDTO.getCreatedDate() );
        modalitePaie.setLastModifiedBy( modalitePaieDTO.getLastModifiedBy() );
        modalitePaie.setLastModifiedDate( modalitePaieDTO.getLastModifiedDate() );
        modalitePaie.setDeleted( modalitePaieDTO.isDeleted() );
        modalitePaie.setId( modalitePaieDTO.getId() );
        modalitePaie.setLibelle( modalitePaieDTO.getLibelle() );
        modalitePaie.setCode( modalitePaieDTO.getCode() );
        modalitePaie.setRemoteID( modalitePaieDTO.getRemoteID() );
        modalitePaie.setDefaultPRMID( modalitePaieDTO.getDefaultPRMID() );
        modalitePaie.setTypeDemande( typeDemandeDTOToTypeDemande( modalitePaieDTO.getTypeDemande() ) );
        modalitePaie.setDesactiver( modalitePaieDTO.isDesactiver() );

        return modalitePaie;
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
}
