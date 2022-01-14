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

import DemoWebAPI.model.*;
import DemoWebAPI.repository.GioHangRepository;
import DemoWebAPI.repository.ChiTietGioHangRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class GioHangController {

	@Autowired
	GioHangRepository repoGH;
	@Autowired
	ChiTietGioHangRepository repoCTGH;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;

	

	@GetMapping("/xemgiohang/{MaKH_input}")

	public ResponseEntity<ReturnDTO_GioHang> XemGioHang(@PathVariable(value = "MaKH_input") long MaKH_input) {
		try {
			ReturnDTO_GioHang dto = new ReturnDTO_GioHang();
			
			GioHang giohang = new GioHang();
			Query q = new Query();
			q.addCriteria(Criteria.where("MaKH").is(MaKH_input));
			giohang = mongoTemplate.findOne(q, GioHang.class);
			dto.setGiohang(giohang);
			
			Query q1 = new Query();
			q1.addCriteria(Criteria.where("MaGH").is(giohang.getID()));
			List<ChiTietGioHang> DSChiTietGioHang = mongoTemplate.find(q1, ChiTietGioHang.class);
			
			List<ReturnChiTietGH_DTO> listDTO = new ArrayList<>();
			
			//Chuyen chitietGH sang dang list DTO voi 2 thuoc tinh them la TenSP va TongTien
			for (ChiTietGioHang i : DSChiTietGioHang) {
				ReturnChiTietGH_DTO chitietGH_DTO = new ReturnChiTietGH_DTO();
				Query q2 = new Query();
				q2.addCriteria(Criteria.where("ID").is(i.getMaSP()));
				SanPham sp = mongoTemplate.findOne(q2, SanPham.class);
				chitietGH_DTO.setChitietgh(i);
				chitietGH_DTO.setTenSP(sp.getTenSP());
				chitietGH_DTO.setTongChiTiet(sp.getGiaTien() * i.getSL());
				
				//Add vao Gio hang DTO
				listDTO.add(chitietGH_DTO);	
			}
			dto.setDSGioHang(listDTO);
			return new ResponseEntity<>(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
	@DeleteMapping("/deletegiohang/{MaChiTiet_input}")
	public ResponseEntity<HttpStatus> XoaSP_GioHang(@PathVariable(value = "MaChiTiet_input") long MaChiTiet_input) {
		try {
			//Lay ra ma chi tiet tuong ung
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(MaChiTiet_input));
			ChiTietGioHang chitiet = mongoTemplate.findOne(q, ChiTietGioHang.class);
			
			//Tinh tong tien cua chi tiet
			Query q1 = new Query();
			q1.addCriteria(Criteria.where("ID").is(chitiet.getMaSP()));
			SanPham sp = mongoTemplate.findOne(q, SanPham.class);
			float TongTienChiTiet = sp.getGiaTien() * chitiet.getSL();
			
			//Cap nhat tong tien o gio hang
			Query q2 = new Query();
			q2.addCriteria(Criteria.where("ID").is(chitiet.getMaGH()));
			GioHang giohang = mongoTemplate.findOne(q2, GioHang.class);
			giohang.setTongTien(giohang.getTongTien() - TongTienChiTiet);
			repoGH.save(giohang);
			
			//Xoa chi tiet
			mongoTemplate.findAndRemove(q, ChiTietGioHang.class);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	
	@PostMapping("/themgiohang")
	public ResponseEntity<ReturnDTO_GioHang> ThemGH(@RequestBody ThemGH_DTO themgiohang) {
		try {
			GioHang giohang = new GioHang();
			Query q = new Query();
			q.addCriteria(Criteria.where("MaKH").is(themgiohang.getMaKH()));
			giohang = mongoTemplate.findOne(q, GioHang.class);
			ReturnDTO_GioHang dto = new ReturnDTO_GioHang();
			if (giohang == null) {
				
				//Tao ID gio hang neu chua ton tai
				long idGioHang = repoGH.count()+1;
				
				//Tao bien TongTienGH
				float TongTien = 0;
				
				//themChiTietGioHang
				long idChiTietGioHang = repoCTGH.count()+1;
				repoCTGH.save(new ChiTietGioHang(idChiTietGioHang, idGioHang, themgiohang.getMaSP(), themgiohang.getSL()));
				
				//danh sach chi tiet gio hang
				Query q1 = new Query();
				q1.addCriteria(Criteria.where("MaGH").is(idGioHang));
				List<ChiTietGioHang> DSChiTietGioHang = mongoTemplate.find(q1, ChiTietGioHang.class);
				
				List<ReturnChiTietGH_DTO> listDTO = new ArrayList<>();
				
				//Chuyen chitietGH sang dang list DTO voi 2 thuoc tinh them la TenSP va TongTien
				for (ChiTietGioHang i : DSChiTietGioHang) {
					ReturnChiTietGH_DTO chitietGH_DTO = new ReturnChiTietGH_DTO();
					Query q2 = new Query();
					q2.addCriteria(Criteria.where("ID").is(i.getMaSP()));
					SanPham sp = mongoTemplate.findOne(q2, SanPham.class);
					chitietGH_DTO.setChitietgh(i);
					chitietGH_DTO.setTenSP(sp.getTenSP());
					chitietGH_DTO.setTongChiTiet(sp.getGiaTien() * i.getSL());
					
					//Add vao Gio hang DTO
					listDTO.add(chitietGH_DTO);	
					
					//Cong TongTien trong tung chi tiet GH vao Tong Tien cua GH
					TongTien += chitietGH_DTO.getTongChiTiet();
				}
				GioHang _giohang = repoGH.save(new GioHang(idGioHang, themgiohang.getMaKH(), TongTien));
				dto.setGiohang(_giohang);
				dto.setDSGioHang(listDTO);		
				return new ResponseEntity<>(dto, HttpStatus.CREATED);
			}
			else {
				float TongTien = 0;

				//themChiTietGioHang
				//Kiem tra san pham vua them da ton tai trong gio hang chua
				Query q1 = new Query();
				q1.addCriteria(Criteria.where("MaGH").is(giohang.getID()).and("MaSP").is(themgiohang.getMaSP()));
				ChiTietGioHang chitiet = mongoTemplate.findOne(q1, ChiTietGioHang.class);
				
				//neu chua ton tai thi tao moi
				if (chitiet == null) {
					long idChiTietGioHang = repoCTGH.count()+1;
					repoCTGH.save(new ChiTietGioHang(idChiTietGioHang, giohang.getID(), themgiohang.getMaSP(), themgiohang.getSL()));
				}
				//neu da ton tai thi chi cap nhat sl
				else {
					chitiet.setSL(chitiet.getSL() + themgiohang.getSL());
					repoCTGH.save(chitiet);
				}
				
				//danh sach chi tiet gio hang
				Query q2 = new Query();
				q2.addCriteria(Criteria.where("MaGH").is(giohang.getID()));
				List<ChiTietGioHang> DSChiTietGioHang = mongoTemplate.find(q2, ChiTietGioHang.class);
				
				List<ReturnChiTietGH_DTO> listDTO = new ArrayList<>();
				
				//Chuyen chitietGH sang dang list DTO voi 2 thuoc tinh them la TenSP va TongTien
				for (ChiTietGioHang i : DSChiTietGioHang) {
					ReturnChiTietGH_DTO chitietGH_DTO = new ReturnChiTietGH_DTO();
					Query q3 = new Query();
					q3.addCriteria(Criteria.where("ID").is(i.getMaSP()));
					SanPham sp = mongoTemplate.findOne(q3, SanPham.class);
					chitietGH_DTO.setChitietgh(i);
					chitietGH_DTO.setTenSP(sp.getTenSP());
					chitietGH_DTO.setTongChiTiet(sp.getGiaTien() * i.getSL());
					
					//Add vao Gio hang DTO
					listDTO.add(chitietGH_DTO);	
					
					TongTien += chitietGH_DTO.getTongChiTiet();
				}
				giohang.setTongTien(TongTien);
				repoGH.save(giohang);
				dto.setDSGioHang(listDTO);
				dto.setGiohang(giohang);
				return new ResponseEntity<>(dto, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
