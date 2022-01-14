package DemoWebAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Column;

@Document(collection = "SHIPPER")
public class Shipper {
	@Id
	private long ID;
	private String Tendangnhap;
	private String Matkhau;
	private String Hoten;
	private String Sdt;
	private String Email;
	private String Diachi;
	private int SoMuiTiem;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getTendangnhap() {
		return Tendangnhap;
	}
	public void setTendangnhap(String tendangnhap) {
		Tendangnhap = tendangnhap;
	}
	public String getMatkhau() {
		return Matkhau;
	}
	public void setMatkhau(String matkhau) {
		Matkhau = matkhau;
	}
	public String getHoten() {
		return Hoten;
	}
	public void setHoten(String hoten) {
		Hoten = hoten;
	}
	public String getSdt() {
		return Sdt;
	}
	public void setSdt(String sdt) {
		Sdt = sdt;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}
	public int getSoMuiTiem() {
		return SoMuiTiem;
	}
	public void setSoMuiTiem(int soMuiTiem) {
		SoMuiTiem = soMuiTiem;
	}
	public Shipper(long iD, String tendangnhap, String matkhau, String hoten, String sdt, String email, String diachi,
			int soMuiTiem) {
		super();
		ID = iD;
		Tendangnhap = tendangnhap;
		Matkhau = matkhau;
		Hoten = hoten;
		Sdt = sdt;
		Email = email;
		Diachi = diachi;
		SoMuiTiem = soMuiTiem;
	}
	public Shipper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
}
