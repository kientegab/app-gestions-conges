/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.NotificationAgent;
import bf.mfptps.appgestionsconges.service.dto.NotificationAgentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface NotificationAgentMapper {
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "agent", target = "agent")
    @Mapping(source = "notification", target = "notification")
    @Mapping(source = "lu", target = "lu")
    NotificationAgent toEntity(NotificationAgentDTO notificationAgentDTO);
    
   @Mapping(source = "id", target = "id")
   @Mapping(source = "agent", target = "agent")
   @Mapping(source = "notification", target = "notification")
   @Mapping(source = "lu", target = "lu")
   NotificationAgentDTO toDto(NotificationAgent notificationAgent);
}
