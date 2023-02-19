package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Profile;
import bf.mfptps.appgestionsconges.repositories.ProfileRepository;
import bf.mfptps.appgestionsconges.security.SecurityUtils;
import bf.mfptps.appgestionsconges.service.ProfileService;
import bf.mfptps.appgestionsconges.service.dto.ProfileDTO;
import bf.mfptps.appgestionsconges.service.mapper.ProfileMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Profile}.
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public Profile save(Profile profile) {
        log.debug("Request to save Profile : {}", profile);
        return profileRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Profiles");
        Page<ProfileDTO> profiles;

        //Retourne tous les profiles au cas ou c'est un ADMIN qui tente la creation de compte
        if (SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN)) {
            profiles = profileRepository.findAll(pageable).map(ProfileDTO::new);
        } else {//Retourne le profile du user qui tente la creation de compte
            //  String matricule = SecurityUtils.getCurrentUserMatricule().get();
            // List<Profile> mesProfiles = profileRepository.findUserProfiles(matricule);
//A revoir
            //   List<ProfileDTO> list = new ArrayList<>();
            //  for (Profile profile : mesProfiles) {
            //      list.add(profileMapper.toDTO(profile));
            //  }
            //   profiles = new PageImpl<ProfileDTO>(list, pageable, mesProfiles.size());
            profiles = profileRepository.findAll(pageable).map(ProfileDTO::new);
        }
        return profiles;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> findOne(Long id) {
        log.debug("Request to get Profile : {}", id);
        return profileRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Profile : {}", id);
        profileRepository.deleteProfileFromAgentAssociation(id);
        profileRepository.deleteAssociatePrivilege(id);
        profileRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> getProfileWithPrivilegesByName(String name) {
        return profileRepository.findOneWithPrivilegesByNameIgnoreCase(name);
    }
}
