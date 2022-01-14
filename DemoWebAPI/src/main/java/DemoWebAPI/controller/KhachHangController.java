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

import DemoWebAPI.model.KhachHang;
import DemoWebAPI.repository.KhachHangRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class KhachHangController {

	@Autowired
	KhachHangRepository repo;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;

	@GetMapping("/dangnhap_khachhang/{username}/{pass}")
    public ResponseEntity<List<KhachHang>> Dangnhap_khachhang(@PathVariable(value = "username") String username, @PathVariable(value = "pass") String pass) {

        try {
            List<KhachHang> khachhanglst = new ArrayList<KhachHang>();

            List<KhachHang> khachhanglst1 = new ArrayList<KhachHang>();

            Query q = new Query();

            Query q1 = new Query();
            q.addCriteria(Criteria.where("Tendangnhap").is(username));

            q1.addCriteria(Criteria.where("Matkhau").is(pass));
            khachhanglst = mongoTemplate.find(q, KhachHang.class);

            khachhanglst1 = mongoTemplate.find(q1, KhachHang.class);

            if (khachhanglst.isEmpty() || khachhanglst1.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(khachhanglst, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@GetMapping("/xemkhachhang")
	public ResponseEntity<List<KhachHang>> XemDanhSachKH() {
		try {
			List<KhachHang> khachhanglst = new ArrayList<KhachHang>();


			repo.findAll().forEach(khachhanglst::add);

			if (khachhanglst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(khachhanglst, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@GetMapping("/xemdonhang/{MaShipper_input}")
//	public ResponseEntity<List<DonHang>> XemDanhSachDonHangShipper(@PathVariable(value = "MaShipper_input") String MaShipper_input) {
//
//		try {
//			List<DonHang> donhanglst = new ArrayList<DonHang>();
//			Query q = new Query();
//			q.addCriteria(Criteria.where("MaShipper").is(MaShipper_input));
//			donhanglst = mongoTemplate.find(q, DonHang.class);
//			if (donhanglst.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//	
//			return new ResponseEntity<>(donhanglst, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//	@PutMapping("/capnhatdonhang")
//	public ResponseEntity<DonHang> CapNhatTinhTrangDonHang(@RequestParam ("MaShipper_input") String MaShipper_input, 
//			@RequestParam ("MaDH_input") String MaDH_input, @RequestParam ("TinhTrang_input") String TinhTrang_input) {
//
//		try {
//			Query q = new Query();
//			q.addCriteria(Criteria.where("MaShipper").is(MaShipper_input).and("MaDH").is(MaDH_input));
//			DonHang donhang = mongoTemplate.findOne(q, DonHang.class);
//			donhang.setTinhTrangDonHang(TinhTrang_input);
//			return new ResponseEntity<>(repo.save(donhang), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
	@PostMapping("/themkhachhang")
	public ResponseEntity<KhachHang> ThemKH(@RequestBody KhachHang khachhang) {
		try {
			khachhang.setID(repo.count()+1);
			KhachHang _khachhang = repo.save(new KhachHang(khachhang.getID(), khachhang.getTendangnhap(),
					khachhang.getMatkhau(), khachhang.getHoten(), khachhang.getSdt(), khachhang.getEmail(), khachhang.getDiachi(), 
					khachhang.getMavung()));
			return new ResponseEntity<>(_khachhang, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
