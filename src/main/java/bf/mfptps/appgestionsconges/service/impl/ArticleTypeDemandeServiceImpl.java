/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.ArticleTypeDemande;
import bf.mfptps.appgestionsconges.repositories.ArticleTypeDemandeRepository;
import bf.mfptps.appgestionsconges.service.ArticleTypeDemandeService;
import bf.mfptps.appgestionsconges.service.dto.ArticleTypeDemandeDTO;
import bf.mfptps.appgestionsconges.service.mapper.ArticleTypeDemandeMapper;
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
public class ArticleTypeDemandeServiceImpl implements ArticleTypeDemandeService {

    private final ArticleTypeDemandeRepository articleTypeDemandeRepository;

    private final ArticleTypeDemandeMapper articleTypeDemandeMapper;

    public ArticleTypeDemandeServiceImpl(ArticleTypeDemandeRepository articleTypeDemandeRepository, ArticleTypeDemandeMapper articleTypeDemandeMapper) {
        this.articleTypeDemandeRepository = articleTypeDemandeRepository;
        this.articleTypeDemandeMapper = articleTypeDemandeMapper;
    }

    @Override
    public ArticleTypeDemandeDTO create(ArticleTypeDemandeDTO articleTypeDemandeDTO) {
        ArticleTypeDemande articleTypeDemande = articleTypeDemandeMapper.toEntity(articleTypeDemandeDTO);
        articleTypeDemande = articleTypeDemandeRepository.save(articleTypeDemande);
        return articleTypeDemandeMapper.toDto(articleTypeDemande);
    }

    @Override
    public ArticleTypeDemandeDTO update(ArticleTypeDemandeDTO articleTypeDemandeDTO) {
        ArticleTypeDemande articleTypeDemande = articleTypeDemandeMapper.toEntity(articleTypeDemandeDTO);
        articleTypeDemande = articleTypeDemandeRepository.save(articleTypeDemande);
        return articleTypeDemandeMapper.toDto(articleTypeDemande);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleTypeDemandeDTO> findOne(Long id) {
        return articleTypeDemandeRepository.findById(id).map(articleTypeDemandeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleTypeDemandeDTO> findAll(Pageable pageable) {
        return articleTypeDemandeRepository.findAll(pageable).map(articleTypeDemandeMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {

        articleTypeDemandeRepository.deleteById(id);
    }

}
