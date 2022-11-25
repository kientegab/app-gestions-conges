/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.ModalitePaie;
import bf.mfptps.appgestionsconges.repositories.ModalitePaieRepository;
import bf.mfptps.appgestionsconges.service.ModalitePaieService;
import bf.mfptps.appgestionsconges.service.dto.ModalitePaieDTO;
import bf.mfptps.appgestionsconges.service.mapper.ModalitePaieMapper;
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
public class ModalitePaieServiceImpl implements ModalitePaieService {

    private final ModalitePaieRepository modalitePaieRepository;

    private final ModalitePaieMapper modalitePaieMapper;

    public ModalitePaieServiceImpl(ModalitePaieRepository modalitePaieRepository, ModalitePaieMapper modalitePaieMapper) {
        this.modalitePaieRepository = modalitePaieRepository;
        this.modalitePaieMapper = modalitePaieMapper;
    }

    @Override
    public ModalitePaieDTO create(ModalitePaieDTO modalitePaieDTO) {
        ModalitePaie modalitePaie = modalitePaieMapper.toEntity(modalitePaieDTO);
        modalitePaie = modalitePaieRepository.save(modalitePaie);
        return modalitePaieMapper.toDto(modalitePaie);
    }

    @Override
    public ModalitePaieDTO update(ModalitePaieDTO modalitePaieDTO) {
        ModalitePaie modalitePaie = modalitePaieMapper.toEntity(modalitePaieDTO);
        modalitePaie = modalitePaieRepository.save(modalitePaie);
        return modalitePaieMapper.toDto(modalitePaie);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ModalitePaieDTO> findOne(long id) {
        return modalitePaieRepository.findById(id).map(modalitePaieMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ModalitePaieDTO> findAll(Pageable pageable) {
        return modalitePaieRepository.findAll(pageable).map(modalitePaieMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
