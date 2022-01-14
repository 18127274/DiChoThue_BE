package DemoWebAPI.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Column;

import lombok.Data;

@Data
public class ReturnChiTietGH_DTO {
	private ChiTietGioHang chitietgh;
	private String TenSP;
	private float TongChiTiet;
	
	public ChiTietGioHang getChitietgh() {
		return chitietgh;
	}
	public void setChitietgh(ChiTietGioHang chitietgh) {
		this.chitietgh = chitietgh;
	}
	public String getTenSP() {
		return TenSP;
	}
	public void setTenSP(String tenSP) {
		TenSP = tenSP;
	}
	public float getTongChiTiet() {
		return TongChiTiet;
	}
	public void setTongChiTiet(float tongChiTiet) {
		TongChiTiet = tongChiTiet;
	}
	public ReturnChiTietGH_DTO(ChiTietGioHang chitietgh, String tenSP, float tongChiTiet) {
		super();
		this.chitietgh = chitietgh;
		TenSP = tenSP;
		TongChiTiet = tongChiTiet;
	}
	public ReturnChiTietGH_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
