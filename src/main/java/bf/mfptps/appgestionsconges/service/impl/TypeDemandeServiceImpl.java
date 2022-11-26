package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.repositories.TypeDemandeRepository;
import bf.mfptps.appgestionsconges.service.TypeDemandeService;
import bf.mfptps.appgestionsconges.service.dto.TypeDemandeDTO;
import bf.mfptps.appgestionsconges.service.mapper.TypeDemandeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author TEGUERA
 */

@Service
@Transactional
public class TypeDemandeServiceImpl implements TypeDemandeService {

    private final TypeDemandeRepository typeDemandeRepository;

    private final TypeDemandeMapper typeDemandeMapper;

    public TypeDemandeServiceImpl(TypeDemandeRepository typeDemandeRepository, TypeDemandeMapper typeDemandeMapper) {
        this.typeDemandeRepository = typeDemandeRepository;
        this.typeDemandeMapper = typeDemandeMapper;
    }

    @Override
    public TypeDemandeDTO create(TypeDemandeDTO typeDemandeDTO) {
        TypeDemande typeDemande = typeDemandeMapper.toEntity(typeDemandeDTO);
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
