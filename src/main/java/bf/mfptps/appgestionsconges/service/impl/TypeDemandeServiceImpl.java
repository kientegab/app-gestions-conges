package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.TypeVisa;
import bf.mfptps.appgestionsconges.entities.Visa;
import bf.mfptps.appgestionsconges.repositories.TypeDemandeRepository;
import bf.mfptps.appgestionsconges.service.TypeDemandeService;
import bf.mfptps.appgestionsconges.service.VisaService;
import bf.mfptps.appgestionsconges.service.dto.TypeDemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.VisaDTO;
import bf.mfptps.appgestionsconges.service.mapper.TypeDemandeMapper;
import bf.mfptps.appgestionsconges.service.mapper.VisaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author TEGUERA
 */

@Service
@Transactional
public class TypeDemandeServiceImpl implements TypeDemandeService {

    private final TypeDemandeRepository typeDemandeRepository;

    @Autowired
    private final VisaService visaService;

    private final TypeDemandeMapper typeDemandeMapper;

    @Autowired
    private final VisaMapper visaMapper;

    public TypeDemandeServiceImpl(TypeDemandeRepository typeDemandeRepository, VisaService visaService, TypeDemandeMapper typeDemandeMapper, VisaMapper visaMapper) {
        this.typeDemandeRepository = typeDemandeRepository;
        this.visaService = visaService;
        this.typeDemandeMapper = typeDemandeMapper;
        this.visaMapper = visaMapper;
    }

    @Override
    public TypeDemandeDTO create(TypeDemandeDTO typeDemandeDTO) {
        TypeDemande typeDemande = typeDemandeMapper.toEntity(typeDemandeDTO);
        TypeDemande finalTypeDemande = typeDemande;
        typeDemande.setTypeVisas(finalTypeDemande.getTypeVisas()
                .stream().map(typeVisa -> {
                    Visa visa = visaService.findVisaById(typeVisa.getVisa().getId());
                    TypeVisa typeVisa1 = new TypeVisa();
                    typeVisa1.setTypeDemande(finalTypeDemande);
                    typeVisa1.setVisa(visa);
                    typeVisa1.setNumeroOrdre(typeVisa.getNumeroOrdre());
                    return typeVisa1;
                })
                .collect(Collectors.toSet())
        );
        typeDemande = typeDemandeRepository.save(typeDemande);
        return typeDemandeMapper.toDto(typeDemande);
    }

    @Override
    public TypeDemandeDTO update(TypeDemandeDTO typeDemandeDTO) {
        TypeDemande typeDemande = typeDemandeMapper.toEntity(typeDemandeDTO);
        typeDemande = typeDemandeRepository.save(typeDemande);
        return typeDemandeMapper.toDto(typeDemande);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeDemandeDTO> findOne(Long id) {
        return typeDemandeRepository.findById(id).map(typeDemandeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeDemandeDTO> findAll(Pageable pageable) {
        return typeDemandeRepository.findAll(pageable).map(typeDemandeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        typeDemandeRepository.deleteById(id);
    }
}
