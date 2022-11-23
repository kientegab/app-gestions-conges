/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Article;
import bf.mfptps.appgestionsconges.repositories.ArticleRepository;
import bf.mfptps.appgestionsconges.service.ArticleService;
import bf.mfptps.appgestionsconges.service.dto.ArticleDTO;
import bf.mfptps.appgestionsconges.service.mapper.ArticleMapper;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HEBIE
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticleDTO create(ArticleDTO articleDTO) {
        Article article = articleMapper.toEntity(articleDTO);
        article = articleRepository.save(article);
        return articleMapper.toDto(article);
    }

    @Override
    public ArticleDTO update(ArticleDTO articleDTO) {
        Article article = articleMapper.toEntity(articleDTO);
        article = articleRepository.save(article);
        return articleMapper.toDto(article);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleDTO> findOne(long id) {
        return articleRepository.findById(id).map(articleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDTO> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(articleMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
