/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Corps;
import bf.mfptps.appgestionsconges.repositories.CorpsRepository;
import bf.mfptps.appgestionsconges.service.CorpsService;
import bf.mfptps.appgestionsconges.service.dto.CorpsDTO;
import bf.mfptps.appgestionsconges.service.mapper.CorpsMapper;
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
public class CorpsServiceImpl implements CorpsService {

    private final CorpsRepository corpsRepository;

    private final CorpsMapper corpsMapper;

    public CorpsServiceImpl(CorpsRepository corpsRepository, CorpsMapper corpsMapper) {
        this.corpsRepository = corpsRepository;
        this.corpsMapper = corpsMapper;
    }

    @Override
    public CorpsDTO create(CorpsDTO corpsDTO) {
        Corps corps = corpsMapper.toEntity(corpsDTO);
        corps = corpsRepository.save(corps);
        return corpsMapper.toDto(corps);
    }

    @Override
    public CorpsDTO update(CorpsDTO corpsDTO) {
        Corps corps = corpsMapper.toEntity(corpsDTO);
        corps = corpsRepository.save(corps);
        return corpsMapper.toDto(corps);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CorpsDTO> findOne(long id) {
        return corpsRepository.findById(id).map(corpsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CorpsDTO> findAll(Pageable pageable) {
        return corpsRepository.findAll(pageable).map(corpsMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
