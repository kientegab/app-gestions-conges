/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appnomination.service.impl;

import bf.mfptps.appnomination.entities.TypeStructure;
import bf.mfptps.appnomination.repositories.TypeStructureRepository;
import bf.mfptps.appnomination.service.TypeStructureService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
public class TypeStructureServiceImpl implements TypeStructureService {

    private final TypeStructureRepository typeStructureRepository;

    public TypeStructureServiceImpl(TypeStructureRepository typeStructureRepository) {
        this.typeStructureRepository = typeStructureRepository;
    }

    @Override
    public TypeStructure create(TypeStructure typeStructure) {
        return typeStructureRepository.save(typeStructure);
    }

    @Override
    public TypeStructure update(TypeStructure typeStructure) {
        return typeStructureRepository.save(typeStructure);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeStructure> get(long id) {
        return typeStructureRepository.findById(id);
    }

    @Override
    public Page<TypeStructure> findAll(Pageable pageable) {
        return typeStructureRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
