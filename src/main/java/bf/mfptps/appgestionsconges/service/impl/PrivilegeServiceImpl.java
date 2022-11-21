package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Privilege;
import bf.mfptps.appgestionsconges.repositories.PrivilegeRepository;
import bf.mfptps.appgestionsconges.service.PrivilegeService;
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
public class PrivilegeServiceImpl implements PrivilegeService {

    private final Logger log = LoggerFactory.getLogger(PrivilegeServiceImpl.class);

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Privilege save(Privilege privilege) {
        log.debug("Request to save Privilege : {}", privilege);
        return privilegeRepository.save(privilege);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Privilege> findAll(Pageable pageable) {
        log.debug("Request to get all Privilege");
        return privilegeRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Privilege> findOne(Long id) {
        log.debug("Request to get Privilege : {}", id);
        return privilegeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Privilege : {}", id);
        privilegeRepository.deleteById(id);
    }

    @Override
    public List<Privilege> getList() {
        log.debug("Request to get all Privilege");
        return privilegeRepository.streamAll().collect(Collectors.toList());
    }

}
