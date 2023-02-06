package bf.mfptps.appgestionsconges.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.service.dto.AgentDTO;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    @Mappings({
        @Mapping(target = "matricule", source = "agent.matricule")
    })
    AgentDTO toDto(Agent agent);

    @Mappings({
        @Mapping(target = "matricule", source = "agentDTO.matricule")
    })
    Agent toEntity(AgentDTO agentDTO);
}
