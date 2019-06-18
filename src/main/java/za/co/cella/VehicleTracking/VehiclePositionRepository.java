package za.co.cella.VehicleTracking;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclePositionRepository extends ReactiveMongoRepository<VehiclePosition,String> {
}
