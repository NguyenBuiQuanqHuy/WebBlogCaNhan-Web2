package huynbq.ntu.web2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chude")
public class ChuDe {
	@Id
	@Column(name = "ID")
	private int chudeID;
	
	@Column(name = "tenchude")
	private String tenchude;
	
	@Column(name = "hinhanh")
	private String hinhanh;

	public int getChudeID() {
		return chudeID;
	}

	public void setChudeID(int chudeID) {
		this.chudeID = chudeID;
	}

	public String getTenchude() {
		return tenchude;
	}

	public void setTenchude(String tenchude) {
		this.tenchude = tenchude;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}
	
	
}
