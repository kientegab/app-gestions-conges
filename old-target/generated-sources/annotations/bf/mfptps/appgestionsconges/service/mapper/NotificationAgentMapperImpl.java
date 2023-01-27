package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.NotificationAgent;
import bf.mfptps.appgestionsconges.service.dto.NotificationAgentDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class NotificationAgentMapperImpl implements NotificationAgentMapper {

    @Override
    public NotificationAgent toEntity(NotificationAgentDTO notificationAgentDTO) {
        if ( notificationAgentDTO == null ) {
            return null;
        }

        NotificationAgent notificationAgent = new NotificationAgent();

        notificationAgent.setId( notificationAgentDTO.getId() );
        notificationAgent.setAgent( notificationAgentDTO.getAgent() );
        notificationAgent.setNotification( notificationAgentDTO.getNotification() );
        notificationAgent.setLu( notificationAgentDTO.isLu() );

        return notificationAgent;
    }

    @Override
    public NotificationAgentDTO toDto(NotificationAgent notificationAgent) {
        if ( notificationAgent == null ) {
            return null;
        }

        NotificationAgentDTO notificationAgentDTO = new NotificationAgentDTO();

        notificationAgentDTO.setId( notificationAgent.getId() );
        notificationAgentDTO.setAgent( notificationAgent.getAgent() );
        notificationAgentDTO.setNotification( notificationAgent.getNotification() );
        notificationAgentDTO.setLu( notificationAgent.isLu() );

        return notificationAgentDTO;
    }
}
