package DemoWebAPI.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import javax.persistence.Column;

import lombok.Data;

@Data
public class ThemGH_DTO {
	private long MaSP;
	private long MaKH;
	private int SL;
	public long getMaSP() {
		return MaSP;
	}
	public void setMaSP(long maSP) {
		MaSP = maSP;
	}
	public long getMaKH() {
		return MaKH;
	}
	public void setMaKH(long maKH) {
		MaKH = maKH;
	}
	public int getSL() {
		return SL;
	}
	public void setSL(int sL) {
		SL = sL;
	}
	public ThemGH_DTO(long maSP, long maKH, int sL) {
		super();
		MaSP = maSP;
		MaKH = maKH;
		SL = sL;
	}
	public ThemGH_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
