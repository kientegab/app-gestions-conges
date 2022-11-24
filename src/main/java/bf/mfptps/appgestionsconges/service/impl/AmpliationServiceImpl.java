/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Ampliation;
import bf.mfptps.appgestionsconges.repositories.AmpliationRepository;
import bf.mfptps.appgestionsconges.service.AmpliationService;
import bf.mfptps.appgestionsconges.service.dto.AmpliationDTO;
import bf.mfptps.appgestionsconges.service.mapper.AmpliationMapper;
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
public class AmpliationServiceImpl implements AmpliationService {

    private final AmpliationRepository ampliationRepository;

    private final AmpliationMapper ampliationMapper;

    public AmpliationServiceImpl(AmpliationRepository ampliationRepository, AmpliationMapper ampliationMapper) {
        this.ampliationRepository = ampliationRepository;
        this.ampliationMapper = ampliationMapper;
    }

    @Override
    public AmpliationDTO create(AmpliationDTO ampliationDTO) {
        Ampliation ampliation = ampliationMapper.toEntity(ampliationDTO);
        ampliation = ampliationRepository.save(ampliation);
        return ampliationMapper.toDto(ampliation);
    }

    @Override
    public AmpliationDTO update(AmpliationDTO ampliationDTO) {
        Ampliation ampliation = ampliationMapper.toEntity(ampliationDTO);
        ampliation = ampliationRepository.save(ampliation);
        return ampliationMapper.toDto(ampliation);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AmpliationDTO> findOne(long id) {
        return ampliationRepository.findById(id).map(ampliationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AmpliationDTO> findAll(Pageable pageable) {
        return ampliationRepository.findAll(pageable).map(ampliationMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
