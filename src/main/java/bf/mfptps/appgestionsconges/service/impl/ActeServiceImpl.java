package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.entities.Privilege;
import bf.mfptps.appgestionsconges.repositories.ActeRepository;
import bf.mfptps.appgestionsconges.repositories.PrivilegeRepository;
import bf.mfptps.appgestionsconges.service.ActeService;
import bf.mfptps.appgestionsconges.service.PrivilegeService;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;
import bf.mfptps.appgestionsconges.service.mapper.ActeMapper;
import bf.mfptps.appgestionsconges.service.mapper.DemandeMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Privilege}.
 */
@Service
@Transactional
public class ActeServiceImpl implements ActeService {

    private final Logger log = LoggerFactory.getLogger(ActeServiceImpl.class);

    private final ActeRepository acteRepository;
    private final ActeMapper acteMapper;
    
    public ActeServiceImpl(ActeRepository acteRepository, ActeMapper acteMapper) {
        this.acteRepository = acteRepository;
		this.acteMapper = acteMapper;
    }

    @Override
    public ActeDTO create(ActeDTO acteDTO) {
        log.debug("Request to save Privilege : {}", acteDTO);
        Acte acte = acteMapper.toEntity(acteDTO);
        return acteMapper.toDto(acteRepository.save(acte));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Acte> findAll(Pageable pageable) {
        log.debug("Request to get all Privilege");
        return acteRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Acte> findOne(Long id) {
        log.debug("Request to get acte : {}", id);
        return acteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete acte : {}", id);
        acteRepository.deleteById(id);
    }

}
