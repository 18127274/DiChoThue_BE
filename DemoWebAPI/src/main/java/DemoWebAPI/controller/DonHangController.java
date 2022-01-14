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

import DemoWebAPI.model.ChiTietGioHang;
import DemoWebAPI.model.DonHang;
import DemoWebAPI.model.GioHang;
import DemoWebAPI.model.SanPham;
import DemoWebAPI.repository.DonHangRepository;
import DemoWebAPI.repository.SanPhamRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class DonHangController {

	@Autowired
	DonHangRepository repoDH;
	@Autowired
	SanPhamRepository repoSP;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;

	@GetMapping("/xemdonhang")

	public ResponseEntity<List<DonHang>> XemDanhSachDonHang() {
		try {
			List<DonHang> donhanglst = new ArrayList<DonHang>();


			repoDH.findAll().forEach(donhanglst::add);

			if (donhanglst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(donhanglst, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/xemdonhang/{MaShipper_input}")
	public ResponseEntity<List<DonHang>> XemDanhSachDonHangShipper(@PathVariable(value = "MaShipper_input") long MaShipper_input) {

		try {
			List<DonHang> donhanglst = new ArrayList<DonHang>();
			Query q = new Query();
			q.addCriteria(Criteria.where("MaShipper").is(MaShipper_input));
			donhanglst = mongoTemplate.find(q, DonHang.class);
			if (donhanglst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
	
			return new ResponseEntity<>(donhanglst, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/donchuanhan")
    public ResponseEntity<List<DonHang>> XemDanhSachDonHangChuaCoShipperNhan() {
        try {
        	List<DonHang> donhanglst = new ArrayList<DonHang>();
            Query q = new Query();
            q.addCriteria(Criteria.where("TinhTrangDonHang").is("Cho xac nhan"));
            donhanglst = mongoTemplate.find(q, DonHang.class);
            if (donhanglst.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(donhanglst, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/tiepnhandonhang")
	public ResponseEntity<DonHang> ShipperTiepNhanDonHang(@RequestParam ("MaShipper_input") long MaShipper_input, 
			@RequestParam ("MaDH_input") long MaDH_input) {
		try {
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(MaDH_input));
			DonHang donhang = mongoTemplate.findOne(q, DonHang.class);
			donhang.setMaShipper(MaShipper_input);
			donhang.setTinhTrangDonHang("Da tiep nhan");
			return new ResponseEntity<>(repoDH.save(donhang), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/tinhtrangdonhang")
	public ResponseEntity<DonHang> CapNhatTinhTrangDonHang(@RequestParam ("MaDH_input") long MaDH_input,
			@RequestParam ("TinhTrangDH_input") String TinhTrangDH_input) {
		try {						
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(MaDH_input));
			DonHang donhang = mongoTemplate.findOne(q, DonHang.class);
			donhang.setTinhTrangDonHang(TinhTrangDH_input);			
			return new ResponseEntity<>(repoDH.save(donhang), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/tinhtrangthanhtoan")
	public ResponseEntity<DonHang> CapNhatTinhTrangThanhToan(@RequestParam ("MaDH_input") long MaDH_input,
			@RequestParam ("TinhTrangTT_input") String TinhTrangTT_input) {
		try {
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(MaDH_input));
			DonHang donhang = mongoTemplate.findOne(q, DonHang.class);
			donhang.setTinhTrangThanhToan(TinhTrangTT_input);
			return new ResponseEntity<>(repoDH.save(donhang), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/themdonhang")
	public ResponseEntity<DonHang> ThemDonHang(@RequestBody DonHang donhang) {
		donhang.setID(repoDH.count()+1);
		try {
			//Lay ra ds chi tiet gio hang
			Query q1 = new Query();
			q1.addCriteria(Criteria.where("MaGH").is(donhang.getMaGH()));
			List<ChiTietGioHang> DSChiTietGioHang = mongoTemplate.find(q1, ChiTietGioHang.class);
			
			//Tru SL sp trong tung chi tiet gio hang
			for (ChiTietGioHang i : DSChiTietGioHang) {
				Query q2 = new Query();
				q2.addCriteria(Criteria.where("ID").is(i.getMaSP()));
				SanPham sanpham = mongoTemplate.findOne(q2, SanPham.class);
				sanpham.setSL(sanpham.getSL()-i.getSL());
				repoSP.save(sanpham);
			}
			
			Query q2 = new Query();
			q2.addCriteria(Criteria.where("ID").is(donhang.getMaGH()));
			GioHang giohang = mongoTemplate.findOne(q2, GioHang.class);
			
			DonHang _donhang = repoDH.save(new DonHang(donhang.getID(), donhang.getMaGH(),
					donhang.getPhiShip(), giohang.getTongTien()));
			return new ResponseEntity<>(_donhang, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
