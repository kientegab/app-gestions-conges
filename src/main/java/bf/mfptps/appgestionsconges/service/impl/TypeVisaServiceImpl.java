package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.TypeVisa;
import bf.mfptps.appgestionsconges.repositories.TypeVisaRepository;
import bf.mfptps.appgestionsconges.service.TypeVisaService;
import bf.mfptps.appgestionsconges.service.dto.TypeVisaDTO;
import bf.mfptps.appgestionsconges.service.mapper.TypeVisaMapper;
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
public class TypeVisaServiceImpl implements TypeVisaService {

    private final TypeVisaRepository typeVisaRepository;

    private final TypeVisaMapper typeVisaMapper;

    public TypeVisaServiceImpl(TypeVisaRepository typeVisaRepository, TypeVisaMapper typeVisaMapper) {
        this.typeVisaRepository = typeVisaRepository;
        this.typeVisaMapper = typeVisaMapper;
    }

    @Override
    public TypeVisaDTO create(TypeVisaDTO typeVisaDTO) {
        TypeVisa typeVisa = typeVisaMapper.toEntity(typeVisaDTO);
        typeVisa = typeVisaRepository.save(typeVisa);
        return typeVisaMapper.toDto(typeVisa);
    }

    @Override
    public TypeVisaDTO update(TypeVisaDTO typeVisaDTO) {
        TypeVisa typeVisa = typeVisaMapper.toEntity(typeVisaDTO);
        typeVisa = typeVisaRepository.save(typeVisa);
        return typeVisaMapper.toDto(typeVisa);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeVisaDTO> findOne(Long id) {
        return typeVisaRepository.findById(id).map(typeVisaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeVisaDTO> findAll(Pageable pageable) {
        return typeVisaRepository.findAll(pageable).map(typeVisaMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        typeVisaRepository.deleteById(id);
    }
}
