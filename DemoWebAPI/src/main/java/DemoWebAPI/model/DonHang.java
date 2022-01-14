package DemoWebAPI.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Column;

@Document(collection = "DONHANG")
public class DonHang {
	@Id
	private long ID;
	private long MaGH;
	private long MaShipper;
	private float PhiShip;
	private LocalDate NgayLapDon;
	private String TinhTrangThanhToan;
	private String TinhTrangDonHang;
	private float TongTien;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getMaGH() {
		return MaGH;
	}
	public void setMaGH(long maGH) {
		MaGH = maGH;
	}
	public long getMaShipper() {
		return MaShipper;
	}
	public void setMaShipper(long maShipper) {
		MaShipper = maShipper;
	}
	public float getPhiShip() {
		return PhiShip;
	}
	public void setPhiShip(float phiShip) {
		PhiShip = phiShip;
	}
	public LocalDate getNgayLapDon() {
		return NgayLapDon;
	}
	public void setNgayLapDon(LocalDate ngayLapDon) {
		NgayLapDon = ngayLapDon;
	}
	public String getTinhTrangThanhToan() {
		return TinhTrangThanhToan;
	}
	public void setTinhTrangThanhToan(String tinhTrangThanhToan) {
		TinhTrangThanhToan = tinhTrangThanhToan;
	}
	public String getTinhTrangDonHang() {
		return TinhTrangDonHang;
	}
	public void setTinhTrangDonHang(String tinhTrangDonHang) {
		TinhTrangDonHang = tinhTrangDonHang;
	}
	public float getTongTien() {
		return TongTien;
	}
	public void setTongTien(float tongTien) {
		TongTien = tongTien;
	}
	public DonHang(long iD, long maGH, float phiShip, float tongTien) {
		super();
		ID = iD;
		MaGH = maGH;
		MaShipper = 0;
		PhiShip = phiShip;
		NgayLapDon = java.time.LocalDate.now();
		TinhTrangThanhToan = "Chua thanh toan";
		TinhTrangDonHang = "Cho xac nhan";
		TongTien = tongTien;
	}
	public DonHang() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
	

}
