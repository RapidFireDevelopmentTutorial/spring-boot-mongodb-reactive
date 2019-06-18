package za.co.kubent.vt.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import za.co.kubent.vt.VehiclePositionEmitter;

@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    @Autowired
    private VehiclePositionEmitter queueService;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(queueService.getVehiclePositions().map(vehicle -> vehicle.getId())
                .map(webSocketSession::textMessage))
                .and(webSocketSession.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .log());
    }
}