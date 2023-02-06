package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.config.ApplicationProperties;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.Utilisateur;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.repositories.TypeDemandeRepository;
import bf.mfptps.appgestionsconges.repositories.UtilisateurRepository;
import bf.mfptps.appgestionsconges.service.DemandeService;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;
import bf.mfptps.appgestionsconges.service.mapper.DemandeMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
@Transactional
public class DemandeServiceImpl implements DemandeService {

    private final Logger log = LoggerFactory.getLogger(DemandeServiceImpl.class);

    private final DemandeRepository demandeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final TypeDemandeRepository typeDemandeRepository;
    private final DemandeMapper demandeMapper;
    private final ApplicationProperties applicationProperties;

    public DemandeServiceImpl(DemandeRepository demandeRepository, DemandeMapper demandeMapper,
            UtilisateurRepository utilisateurRepository, TypeDemandeRepository typeDemandeRepository, ApplicationProperties applicationProperties) {
        this.demandeRepository = demandeRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.typeDemandeRepository = typeDemandeRepository;
        this.demandeMapper = demandeMapper;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public DemandeDTO create(DemandeDTO demandeDTO, MultipartFile[] fichiersJoint) {
        Demande demande = demandeMapper.toEntity(demandeDTO);
        String userStorage = "";
        if (null != demande.getUtilisateur()) {
            Utilisateur user = utilisateurRepository.findById(demande.getId())
                    .orElseThrow(() -> new CustomException("L'utilisateur qui effectue la demande est inexistant."));
            demande.setUtilisateur(user);
            userStorage = "TESTE";
            if (null != demande.getTypeDemande()) {
                // Gerer la regle de gestion solde demande
                TypeDemande typeDemande = typeDemandeRepository.findById(demande.getTypeDemande().getId())
                        .orElseThrow(() -> new CustomException("Le type de demande est inexistant."));
                demande.setTypeDemande(typeDemande);

                //TODO: comparer le solde  de conge utilisateur au solde global du type de demande
            }

        }

        //TODO: Gerer la regle de gestion document joint;
        if (fichiersJoint != null && fichiersJoint.length > 0) {
            Set<Document> documents = new HashSet<Document>();
            for (MultipartFile file : fichiersJoint) {
                if (!file.isEmpty()) {
                    try {
                        String fileUri = AppUtil.saveUploadFileToServer(applicationProperties.getAppUploadsStorage(), userStorage, file);
                        Document document = new Document();
                        document.setDemande(demande);
                        document.setUrl(fileUri);
                        //TODO : Agenerer automatiquement
                        document.setReference("" + System.currentTimeMillis());
                        documents.add(document);
                    } catch (Exception e) {
                        log.error("Failed to save file on server", e);
                        throw new CustomException("Echec lors de l'enregistrement du fichier sur le server : " + e.getMessage());
                    }
                }
            }
            demande.setDocuments(documents);
        }

        return demandeMapper.toDto(demandeRepository.save(demande));
    }

    @Override
    public DemandeDTO update(DemandeDTO demandeDTO) {
        Demande demande = demandeMapper.toEntity(demandeDTO);
        return demandeMapper.toDto(demandeRepository.save(demande));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Demande> get(String numeroDemande) {
        return demandeRepository.findByNumeroDemande(numeroDemande);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<DemandeDTO> findAll(Pageable pageable) {
        return demandeRepository.findAll(pageable).map(demandeMapper::toDto);
    }

    @Override
    public void delete(Long code) {
        demandeRepository.deleteById(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Demande> get(Long id) {
        return demandeRepository.findById(id);
    }

}
