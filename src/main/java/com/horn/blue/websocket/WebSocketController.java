package com.horn.blue.websocket;
import com.horn.blue.entities.VehicleTrip;
import com.horn.blue.repositories.VehicleTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private VehicleTripRepository vehicleTripRepository; // Necesitas un repositorio para acceder a los datos de VehicleTrip

    @Scheduled(fixedRate = 10000) // Esto ejecutará el método cada 10 segundos, puedes ajustar según tus necesidades
    public void sendDrivingActiveUpdates() {
        List<VehicleTrip> activeTrips = vehicleTripRepository.findByDrivingActiveTrue();
        for (VehicleTrip trip : activeTrips) {
            messagingTemplate.convertAndSend("/topic/active-trips", trip);
        }
    }
}
