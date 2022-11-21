/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appnomination.service.mapper;

import bf.mfptps.appnomination.entities.Profile;
import bf.mfptps.appnomination.service.dto.ProfileDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDTO toDTO(Profile profile);

    Profile toEntity(ProfileDTO profileDTO);
}
