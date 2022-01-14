package DemoWebAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Column;

@Document(collection = "SANPHAM")
public class SanPham {
	@Id
	private long ID;
	private String TenSP;
	private long MaLoai;
	private long MaNCC;
	private float GiaTien;
	private float SL;
	private int Min;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getTenSP() {
		return TenSP;
	}
	public void setTenSP(String tenSP) {
		TenSP = tenSP;
	}
	public long getMaLoai() {
		return MaLoai;
	}
	public void setMaLoai(long maLoai) {
		MaLoai = maLoai;
	}
	public long getMaNCC() {
		return MaNCC;
	}
	public void setMaNCC(long maNCC) {
		MaNCC = maNCC;
	}
	public float getGiaTien() {
		return GiaTien;
	}
	public void setGiaTien(float giaTien) {
		GiaTien = giaTien;
	}
	public float getSL() {
		return SL;
	}
	public void setSL(float sL) {
		SL = sL;
	}
	public int getMin() {
		return Min;
	}
	public void setMin(int min) {
		Min = min;
	}
	public SanPham(long iD, String tenSP, long maLoai, long maNCC, float giaTien, float sL, int min) {
		super();
		ID = iD;
		TenSP = tenSP;
		MaLoai = maLoai;
		MaNCC = maNCC;
		GiaTien = giaTien;
		SL = sL;
		Min = min;
	}
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
