package za.co.kubent.vt.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import za.co.kubent.vt.processor.VehiclePositionEmitterProcessor;
import za.co.kubent.vt.domain.VehiclePosition;

@RestController
public class VehiclePositionRestController {

    @Autowired
    private VehiclePositionEmitterProcessor vehiclePositionEmitterProcessor;

    @GetMapping(value = "/vehicles", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    private Flux<VehiclePosition> getVehiclePosition() {
        return vehiclePositionEmitterProcessor.getVehiclePositions();
    }
}
