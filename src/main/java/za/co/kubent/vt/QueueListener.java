package za.co.kubent.vt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import reactor.core.publisher.Flux;
import za.co.kubent.vt.domain.VehiclePosition;
import za.co.kubent.vt.repository.VehiclePositionRepository;


@EnableBinding(Sink.class)
public class QueueListener {

    @Autowired
    private VehiclePositionEmitter queueService;

    @Autowired
    private VehiclePositionRepository vehiclePositionRepository;

    @StreamListener
    public void incoming(@Input(Sink.INPUT) Flux<VehiclePosition> messages) {
        messages.
                switchMap(s -> vehiclePositionRepository.save(s))
                .subscribe(s -> queueService.addVehiclePosition(s));
    }
}
