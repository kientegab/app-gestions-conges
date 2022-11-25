package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.repositories.DocumentRepository;
import bf.mfptps.appgestionsconges.service.DocumentService;
import bf.mfptps.appgestionsconges.service.dto.DocumentDTO;
import bf.mfptps.appgestionsconges.service.mapper.DocumentMapper;
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
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Override
    public DocumentDTO create(DocumentDTO documentDTO) {
        Document document = documentMapper.toEntity(documentDTO);
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    @Override
    public DocumentDTO update(DocumentDTO documentDTO) {
        Document document = documentMapper.toEntity(documentDTO);
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentDTO> findOne(Long id) {
        return documentRepository.findById(id).map(documentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DocumentDTO> findAll(Pageable pageable) {
        return documentRepository.findAll(pageable).map(documentMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        documentRepository.deleteById(id);
    }
}
