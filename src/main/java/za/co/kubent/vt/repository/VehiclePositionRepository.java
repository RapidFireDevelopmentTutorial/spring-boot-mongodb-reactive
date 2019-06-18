package za.co.kubent.vt.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import za.co.kubent.vt.domain.VehiclePosition;

@Repository
public interface VehiclePositionRepository extends ReactiveMongoRepository<VehiclePosition,String> {
}
