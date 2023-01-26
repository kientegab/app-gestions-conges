package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Profile;
import bf.mfptps.appgestionsconges.service.dto.ProfileDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileDTO toDTO(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setId( profile.getId() );
        profileDTO.setName( profile.getName() );
        profileDTO.setNativeProfile( profile.isNativeProfile() );

        return profileDTO;
    }

    @Override
    public Profile toEntity(ProfileDTO profileDTO) {
        if ( profileDTO == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setId( profileDTO.getId() );
        profile.setName( profileDTO.getName() );

        return profile;
    }
}
