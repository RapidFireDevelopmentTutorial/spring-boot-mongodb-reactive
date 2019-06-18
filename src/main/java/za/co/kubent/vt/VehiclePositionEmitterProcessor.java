package za.co.kubent.vt;

import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import za.co.kubent.vt.domain.VehiclePosition;

@Component
public class VehiclePositionEmitterProcessor {

    private EmitterProcessor<VehiclePosition> emitterProcessor;

    private Flux<VehiclePosition> vehiclePositions;

    public VehiclePositionEmitterProcessor() {
        emitterProcessor = EmitterProcessor.create();
        vehiclePositions = emitterProcessor.publish().autoConnect();
    }

    public void addVehiclePosition(VehiclePosition vehiclePosition) {
        emitterProcessor.onNext(vehiclePosition);

    }

    public Flux<VehiclePosition> getVehiclePositions() {
        return vehiclePositions;
    }
}
