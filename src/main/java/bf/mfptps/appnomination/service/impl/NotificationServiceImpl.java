/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.mfptps.appnomination.service.impl;

import bf.mfptps.appnomination.entities.Agent;
import bf.mfptps.appnomination.entities.Notification;
import bf.mfptps.appnomination.entities.NotificationAgent;
import bf.mfptps.appnomination.repositories.AgentRepository;
import bf.mfptps.appnomination.repositories.NotificationAgentRepository;
import bf.mfptps.appnomination.repositories.NotificationRepository;
import bf.mfptps.appnomination.service.CustomException;
import bf.mfptps.appnomination.service.NotificationService;
import bf.mfptps.appnomination.service.dto.NotificationDTO;
import bf.mfptps.appnomination.service.mapper.NotificationAgentMapper;
import bf.mfptps.appnomination.service.mapper.NotificationMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
@Slf4j
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    
    private final NotificationAgentRepository notificationAgentRepository;
    private final NotificationAgentMapper notificationAgentMapper;
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final AgentRepository agentRepository;
    private final EmailServiceImpl emailServiceImpl;

    public NotificationServiceImpl(NotificationAgentRepository notificationAgentRepository, NotificationAgentMapper notificationAgentMapper, 
            NotificationRepository notificationRepository,
            NotificationMapper notificationMapper,
            EmailServiceImpl emailServiceImpl,
            AgentRepository agentRepository) {
        this.notificationAgentRepository = notificationAgentRepository;
        this.notificationAgentMapper = notificationAgentMapper;
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.agentRepository = agentRepository;
        this.emailServiceImpl = emailServiceImpl;
    }
    
    @Override
    public Notification create(NotificationDTO notificationDTO) {
        Notification notification = notificationMapper.toEntity(notificationDTO);
        List<Agent> agents = agentRepository.findAllAgentByProfiles();
        if(agents.size() == 0){
                throw new CustomException("Il n'y a pas de points focaux ou de responsable structues");
        } else {
        Notification saveNotification = notificationRepository.save(notification);
        for (Agent agent : agents) {
            NotificationAgent notificationAgent = new NotificationAgent();
            notificationAgent.setAgent(agent);
            notificationAgent.setNotification(saveNotification);
            notificationAgent.setLu(false);
            notificationAgentRepository.save(notificationAgent);
            emailServiceImpl.sendEmail(notificationAgent.getNotification(), notificationAgent.getAgent());
        }
        
        return saveNotification;
        }
        
    }
    
    @Override
    public Notification update(Notification notification) {    
        return notificationRepository.save(notification);
    }
    
    @Override
    public Page<NotificationDTO> findAll(Pageable pageable) {
        Page<NotificationDTO> responseMapped = notificationRepository.findAll(pageable).map(notificationMapper::toDto);
        return responseMapped;
    }
    
    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
        
    }

    @Override
    public Page<Notification> get(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<NotificationDTO> get(Long id) {
        Optional<NotificationDTO> responseMapped = notificationRepository.findById(id).map(notificationMapper::toDto);
        return responseMapped;
        
    }
}
