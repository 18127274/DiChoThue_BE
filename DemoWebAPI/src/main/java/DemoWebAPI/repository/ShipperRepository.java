package DemoWebAPI.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import DemoWebAPI.model.Shipper;

@Repository
public interface ShipperRepository extends MongoRepository<Shipper, String>{
	
}
