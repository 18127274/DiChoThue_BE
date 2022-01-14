package DemoWebAPI.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Column;

import lombok.Data;

@Data
public class ReturnDTO_GioHang {
	private GioHang giohang;
	private List<ReturnChiTietGH_DTO> DSGioHang;
	public GioHang getGiohang() {
		return giohang;
	}
	public void setGiohang(GioHang giohang) {
		this.giohang = giohang;
	}
	public List<ReturnChiTietGH_DTO> getDSGioHang() {
		return DSGioHang;
	}
	public void setDSGioHang(List<ReturnChiTietGH_DTO> dSGioHang) {
		DSGioHang = dSGioHang;
	}
	
	public ReturnDTO_GioHang(GioHang giohang, List<ReturnChiTietGH_DTO> dSGioHang) {
		super();
		this.giohang = giohang;
		DSGioHang = dSGioHang;
	}
	public ReturnDTO_GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

}
