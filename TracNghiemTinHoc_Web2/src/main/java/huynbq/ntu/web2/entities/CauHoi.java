package huynbq.ntu.web2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cauhoi")
public class CauHoi {
	@Id
	@Column(name = "ID")
	private int cauhoiID;
	
	@Column(name="chude")
	private int chudeID;
	
	@Column(name = "noidung")
	private String noidung;
	
	@Column(name = "hinhanh")
	private String hinhanh;
	
	@Column(name = "phuongana")
	private String phuongana;
	
	@Column(name = "phuonganb")
	private String phuonganb;
	
	@Column(name = "phuonganc")
	private String phuonganc;
	
	@Column(name = "phuongand")
	private String phuongand;
	
	@Column(name = "cautraloi")
	private String dapan;

	public int getCauhoiID() {
		return cauhoiID;
	}

	public void setCauhoiID(int cauhoiID) {
		this.cauhoiID = cauhoiID;
	}

	public int getChudeID() {
		return chudeID;
	}

	public void setChudeID(int chudeID) {
		this.chudeID = chudeID;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	public String getPhuongana() {
		return phuongana;
	}

	public void setPhuongana(String phuongana) {
		this.phuongana = phuongana;
	}

	public String getPhuonganb() {
		return phuonganb;
	}

	public void setPhuonganb(String phuonganb) {
		this.phuonganb = phuonganb;
	}

	public String getPhuonganc() {
		return phuonganc;
	}

	public void setPhuonganc(String phuonganc) {
		this.phuonganc = phuonganc;
	}

	public String getPhuongand() {
		return phuongand;
	}

	public void setPhuongand(String phuongand) {
		this.phuongand = phuongand;
	}

	public String getDapan() {
		return dapan;
	}

	public void setDapan(String dapan) {
		this.dapan = dapan;
	}
	
	
}
