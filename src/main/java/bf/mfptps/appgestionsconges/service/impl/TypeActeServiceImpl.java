package bf.mfptps.appgestionsconges.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bf.mfptps.appgestionsconges.entities.TypeActe;
import bf.mfptps.appgestionsconges.repositories.TypeActeRepository;
import bf.mfptps.appgestionsconges.service.TypeActeService;

/**
 * Service Implementation for managing {@link TypeActe}.
 */
@Service
@Transactional
public class TypeActeServiceImpl implements TypeActeService {

    private final Logger log = LoggerFactory.getLogger(TypeActeServiceImpl.class);

    private final TypeActeRepository typeActeRepository;

    public TypeActeServiceImpl(TypeActeRepository typeActeRepository) {
        this.typeActeRepository = typeActeRepository;
    }

    @Override
    public TypeActe save(TypeActe typeActe) {
        log.debug("Request to save TypeActe : {}", typeActe);
        return typeActeRepository.save(typeActe);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeActe> findAll(Pageable pageable) {
        log.debug("Request to get all TypeActe");
        return typeActeRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeActe> findOne(Long id) {
        log.debug("Request to get TypeActe : {}", id);
        return typeActeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeActe : {}", id);
        typeActeRepository.deleteById(id);
    }

    @Override
    public List<TypeActe> getList() {
        log.debug("Request to get all TypeActe");
       // return typeActeRepository.streamAll().collect(Collectors.toList());
        return null;
    }

}
