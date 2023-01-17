package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.config.ApplicationProperties;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.entities.Utilisateur;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.repositories.DocumentRepository;
import bf.mfptps.appgestionsconges.service.DocumentService;
import bf.mfptps.appgestionsconges.service.dto.DocumentDTO;
import bf.mfptps.appgestionsconges.service.mapper.DocumentMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import java.io.File;
import java.nio.file.Files;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author TEGUERA
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);
    private final DocumentRepository documentRepository;
    private final DemandeRepository demandeRepository;
    private final ApplicationProperties applicationProperties;

    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper,
            DemandeRepository demandeRepository, ApplicationProperties applicationProperties) {
        this.documentRepository = documentRepository;
        this.demandeRepository = demandeRepository;
        this.applicationProperties = applicationProperties;
        this.documentMapper = documentMapper;
    }

    @Override
    public DocumentDTO create(String numeroDemande, MultipartFile file) {

        try {

            Demande demande = demandeRepository.findByNumeroDemande(numeroDemande).orElseThrow(
                    () -> new CustomException("La demande portant le numero [" + numeroDemande + "] est inexistant."));

            Document document = new Document();
            document.setDemande(demande);
            String userStorage = demande.getAgent().getMatricule();

            String fileUri = AppUtil.saveUploadFileToServer(applicationProperties.getAppUploadsStorage(), userStorage,
                    file);
            document.setUrl(fileUri);
            document = documentRepository.save(document);
            return documentMapper.toDto(document);
        } catch (Exception e) {
            log.error("Failed to save file on server", e);
            throw new CustomException("Echec lors de l'enregistrement du fichier sur le server : " + e.getMessage());
        }

    }

    @Override
    public DocumentDTO update(String reference, MultipartFile file) {

        try {

            Document document = documentRepository.findByReference(reference).orElseThrow(
                    () -> new CustomException("Le document portant la reference [" + reference + "] est inexistant."));

            File oldFile = new File(document.getUrl());
            Files.deleteIfExists(oldFile.toPath());

            Demande demande = document.getDemande();
            String userStorage = demande.getAgent().getMatricule();

            String fileUri = AppUtil.saveUploadFileToServer(applicationProperties.getAppUploadsStorage(), userStorage,
                    file);
            document.setUrl(fileUri);
            document = documentRepository.save(document);
            return documentMapper.toDto(document);
        } catch (Exception e) {
            log.error("Failed to save file on server", e);
            throw new CustomException("Echec lors de l'enregistrement du fichier sur le server : " + e.getMessage());
        }
    }

    @Override
    @Transactional()
    public Optional<Document> findByReference(String ref) {
        return documentRepository.findByReference(ref);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DocumentDTO> findAll(Pageable pageable) {
        return documentRepository.findAll(pageable).map(documentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }

    @Override
    public void delete(Long reference) {
        // TODO Auto-generated method stub

    }
}
