package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Notification;
import bf.mfptps.appgestionsconges.service.dto.NotificationDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification toEntity(NotificationDTO notificationDTO) {
        if ( notificationDTO == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setId( notificationDTO.getId() );
        notification.setObjet( notificationDTO.getObjet() );
        notification.setContenu( notificationDTO.getContenu() );

        return notification;
    }

    @Override
    public NotificationDTO toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setId( notification.getId() );
        notificationDTO.setObjet( notification.getObjet() );
        notificationDTO.setContenu( notification.getContenu() );

        return notificationDTO;
    }
}
