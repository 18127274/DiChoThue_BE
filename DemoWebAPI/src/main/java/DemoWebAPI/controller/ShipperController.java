package DemoWebAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DemoWebAPI.model.Shipper;
import DemoWebAPI.repository.ShipperRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ShipperController {

	@Autowired
	ShipperRepository repo;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;

	@GetMapping("/xemshipper")

	public ResponseEntity<List<Shipper>> XemDanhSachShipper() {
		try {
			List<Shipper> shipperlst = new ArrayList<Shipper>();


			repo.findAll().forEach(shipperlst::add);

			if (shipperlst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(shipperlst, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dangnhap_shipper/{username}/{pass}")
    public ResponseEntity<List<Shipper>> Dangnhap_shipper(@PathVariable(value = "username") String username, @PathVariable(value = "pass") String pass) {

        try {
            List<Shipper> khachhanglst = new ArrayList<Shipper>();

            List<Shipper> khachhanglst1 = new ArrayList<Shipper>();

            Query q = new Query();

            Query q1 = new Query();
            q.addCriteria(Criteria.where("Tendangnhap").is(username));

            q1.addCriteria(Criteria.where("Matkhau").is(pass));
            khachhanglst = mongoTemplate.find(q, Shipper.class);

            khachhanglst1 = mongoTemplate.find(q1, Shipper.class);

            if (khachhanglst.isEmpty() || khachhanglst1.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(khachhanglst, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
		
	@PostMapping("/themshipper")
	public ResponseEntity<Shipper> ThemShipper(@RequestBody Shipper shipper) {
		try {
			shipper.setID(repo.count()+1);
			Shipper _shipper = repo.save(new Shipper(shipper.getID(), shipper.getTendangnhap(),
					shipper.getMatkhau(), shipper.getHoten(), shipper.getSdt(), shipper.getEmail(), shipper.getDiachi(), 
					shipper.getSoMuiTiem()));
			return new ResponseEntity<>(_shipper, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
