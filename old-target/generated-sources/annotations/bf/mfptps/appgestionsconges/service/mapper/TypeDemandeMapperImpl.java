package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.TypeVisa;
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
public class TypeDemandeMapperImpl implements TypeDemandeMapper {

    @Override
    public List<TypeDemande> toEntity(List<TypeDemandeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TypeDemande> list = new ArrayList<TypeDemande>( dtoList.size() );
        for ( TypeDemandeDTO typeDemandeDTO : dtoList ) {
            list.add( toEntity( typeDemandeDTO ) );
        }

        return list;
    }

    @Override
    public List<TypeDemandeDTO> toDto(List<TypeDemande> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TypeDemandeDTO> list = new ArrayList<TypeDemandeDTO>( entityList.size() );
        for ( TypeDemande typeDemande : entityList ) {
            list.add( toDto( typeDemande ) );
        }

        return list;
    }

    @Override
    public TypeDemandeDTO toDto(TypeDemande typeDemande) {
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

    @Override
    public TypeDemande toEntity(TypeDemandeDTO typeDemandeDTO) {
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
}
