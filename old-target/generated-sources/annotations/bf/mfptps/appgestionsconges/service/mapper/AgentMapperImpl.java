package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.Profile;
import bf.mfptps.appgestionsconges.service.dto.AgentDTO;
import bf.mfptps.appgestionsconges.service.dto.ProfileDTO;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class AgentMapperImpl implements AgentMapper {

    @Override
    public AgentDTO toDto(Agent agent) {
        if ( agent == null ) {
            return null;
        }

        AgentDTO agentDTO = new AgentDTO();

        agentDTO.setMatricule( agent.getMatricule() );
        agentDTO.setId( agent.getId() );
        agentDTO.setNom( agent.getNom() );
        agentDTO.setPrenom( agent.getPrenom() );
        agentDTO.setActif( agent.isActif() );
        agentDTO.setCreatedBy( agent.getCreatedBy() );
        agentDTO.setCreatedDate( agent.getCreatedDate() );
        agentDTO.setLastModifiedBy( agent.getLastModifiedBy() );
        agentDTO.setLastModifiedDate( agent.getLastModifiedDate() );
        agentDTO.setTelephone( agent.getTelephone() );
        agentDTO.setProfiles( profileSetToProfileDTOSet( agent.getProfiles() ) );
        agentDTO.setEmail( agent.getEmail() );
        agentDTO.setMatriculeResp( agent.getMatriculeResp() );

        return agentDTO;
    }

    @Override
    public Agent toEntity(AgentDTO agentDTO) {
        if ( agentDTO == null ) {
            return null;
        }

        Agent agent = new Agent();

        agent.setMatricule( agentDTO.getMatricule() );
        agent.setCreatedBy( agentDTO.getCreatedBy() );
        agent.setCreatedDate( agentDTO.getCreatedDate() );
        agent.setLastModifiedBy( agentDTO.getLastModifiedBy() );
        agent.setLastModifiedDate( agentDTO.getLastModifiedDate() );
        agent.setId( agentDTO.getId() );
        agent.setEmail( agentDTO.getEmail() );
        agent.setNom( agentDTO.getNom() );
        agent.setPrenom( agentDTO.getPrenom() );
        agent.setActif( agentDTO.isActif() );
        agent.setTelephone( agentDTO.getTelephone() );
        agent.setProfiles( profileDTOSetToProfileSet( agentDTO.getProfiles() ) );
        agent.setMatriculeResp( agentDTO.getMatriculeResp() );

        return agent;
    }

    protected ProfileDTO profileToProfileDTO(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setId( profile.getId() );
        profileDTO.setName( profile.getName() );
        profileDTO.setNativeProfile( profile.isNativeProfile() );

        return profileDTO;
    }

    protected Set<ProfileDTO> profileSetToProfileDTOSet(Set<Profile> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProfileDTO> set1 = new HashSet<ProfileDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Profile profile : set ) {
            set1.add( profileToProfileDTO( profile ) );
        }

        return set1;
    }

    protected Profile profileDTOToProfile(ProfileDTO profileDTO) {
        if ( profileDTO == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setId( profileDTO.getId() );
        profile.setName( profileDTO.getName() );

        return profile;
    }

    protected Set<Profile> profileDTOSetToProfileSet(Set<ProfileDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Profile> set1 = new HashSet<Profile>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProfileDTO profileDTO : set ) {
            set1.add( profileDTOToProfile( profileDTO ) );
        }

        return set1;
    }
}
