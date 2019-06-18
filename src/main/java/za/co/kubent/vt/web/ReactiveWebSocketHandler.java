package za.co.kubent.vt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import za.co.kubent.vt.processor.VehiclePositionEmitterProcessor;

@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    @Autowired
    private VehiclePositionEmitterProcessor vehiclePositionEmitterProcessor;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(vehiclePositionEmitterProcessor.getVehiclePositions().map(vehicle -> vehicle.getId())
                .map(webSocketSession::textMessage))
                .and(webSocketSession.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .log());
    }
}