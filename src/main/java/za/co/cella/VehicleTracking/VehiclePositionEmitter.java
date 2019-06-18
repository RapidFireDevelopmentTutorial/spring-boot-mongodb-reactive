package za.co.cella.VehicleTracking;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Component
public class VehiclePositionEmitter {

    private EmitterProcessor<VehiclePosition> emitterProcessor;

    private Flux<VehiclePosition> vehiclePositions;

    public VehiclePositionEmitter() {
        emitterProcessor = EmitterProcessor.create();
        vehiclePositions = emitterProcessor.publish().autoConnect();;
    }

    public void addVehiclePosition(VehiclePosition vehiclePosition) {
        emitterProcessor.onNext(vehiclePosition);

    }

    public Flux<VehiclePosition> getVehiclePositions() {
        return vehiclePositions;
    }
}
