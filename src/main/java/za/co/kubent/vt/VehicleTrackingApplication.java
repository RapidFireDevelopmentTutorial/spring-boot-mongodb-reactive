package za.co.kubent.vt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;
import za.co.kubent.vt.domain.VehiclePosition;
import za.co.kubent.vt.processor.VehiclePositionEmitterProcessor;

import java.time.Duration;

@SpringBootApplication
@EnableBinding(Source.class)
public class VehicleTrackingApplication {


    @Autowired
    @Qualifier("output")
    private MessageChannel output;

    @Autowired
    private VehiclePositionEmitterProcessor queueService;


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            queueService.getVehiclePositions().subscribe(vehicle -> System.out.println(vehicle));
            Flux.interval(Duration.ofMillis(100)).subscribe(aLong -> output.send(MessageBuilder.withPayload(VehiclePosition.builder().registrationNumber(aLong + "").build()).build()));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VehicleTrackingApplication.class, args);
    }

}
