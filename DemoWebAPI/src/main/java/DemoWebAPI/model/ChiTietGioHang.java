package DemoWebAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Column;

@Document(collection = "CHITIETGIOHANG")
public class ChiTietGioHang {
	@Id
	private long ID;
	private long MaGH;
	private long MaSP;
	private float SL;
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
	public long getMaSP() {
		return MaSP;
	}
	public void setMaSP(long maSP) {
		MaSP = maSP;
	}
	public float getSL() {
		return SL;
	}
	public void setSL(float sL) {
		SL = sL;
	}
	public ChiTietGioHang(long iD, long maGH, long maSP, float sL) {
		super();
		ID = iD;
		MaGH = maGH;
		MaSP = maSP;
		SL = sL;
	}
	public ChiTietGioHang() {
		super();
		// TODO Auto-generated constructor stub
	}
}
