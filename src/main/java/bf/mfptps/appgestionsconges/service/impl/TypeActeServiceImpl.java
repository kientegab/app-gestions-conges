package bf.mfptps.appgestionsconges.service.impl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import bf.mfptps.appgestionsconges.config.ApplicationProperties;
import bf.mfptps.appgestionsconges.entities.TypeActe;
import bf.mfptps.appgestionsconges.repositories.TypeActeRepository;
import bf.mfptps.appgestionsconges.service.TypeActeService;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;

/**
 * Service Implementation for managing {@link TypeActe}.
 */
@Service
@Transactional
public class TypeActeServiceImpl implements TypeActeService {

    private final Logger log = LoggerFactory.getLogger(TypeActeServiceImpl.class);

    private final TypeActeRepository typeActeRepository;
    private final ApplicationProperties applicationProperties;
    public TypeActeServiceImpl(TypeActeRepository typeActeRepository, ApplicationProperties applicationProperties) {
        this.typeActeRepository = typeActeRepository;
		this.applicationProperties = applicationProperties;
    }

    @Override
    public TypeActe save(TypeActe typeActe, MultipartFile fileMultipart) {
        log.debug("Request to save TypeActe : {}", typeActe);
        
		try {
			String fileUri = AppUtil.saveUploadFileToServer(applicationProperties.getAppTemplatesStorage(), "", fileMultipart);
			 typeActe.setTemplateUri(fileUri);
		        return typeActeRepository.save(typeActe);
		} catch (Exception e) {
			log.error("Failed to save typeActe", e);
			 throw new CustomException("Echec lors de l'enregistrement du type d'acte : " + e.getMessage());
		}
       
    }
    
    @Override
    public TypeActe update(TypeActe typeActe, MultipartFile fileMultipart ) {
    	  log.debug("Request to update TypeActe : {}", typeActe);
    	  
          if(null==typeActe.getId() || typeActeRepository.existsById(typeActe.getId())) {
        	  throw new CustomException("Le type d'acte en cours de mise a jour n'existe pas ou a ete supprime.");
          }
          
          try {
        	  if(null!= fileMultipart) {
        		  File oldFile = new File(typeActe.getTemplateUri());
        		  if(oldFile.exists() && oldFile.isFile()) {
        			  oldFile.delete();
        		  }
        		  
        		  String fileUri = AppUtil.saveUploadFileToServer(applicationProperties.getAppTemplatesStorage(), "", fileMultipart);
       			  typeActe.setTemplateUri(fileUri);
        	  }
  		        return typeActeRepository.save(typeActe);
  		} catch (Exception e) {
  			log.error("Failed to save typeActe", e);
  			 throw new CustomException("Echec lors de l'enregistrement du type d'acte : " + e.getMessage());
  		}
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
