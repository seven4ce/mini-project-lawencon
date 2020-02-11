package id.co.lawencon.test.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;

import id.co.lawencon.test.entity.Barang;

public class ListTransaksi {

	private long idTrxDetail;
	private Barang barang;
	private int jumlahBarang;
	private long totalHarga;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
	private Date transactionDate;

	private String transactionBy;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
	private Date modifiedDate;

	private String modifiedBy;

	public long getIdTrxDetail() {
		return idTrxDetail;
	}

	public void setIdTrxDetail(long idTrxDetail) {
		this.idTrxDetail = idTrxDetail;
	}

	
	
	public Barang getBarang() {
		return barang;
	}

	public void setBarang(Barang barang) {
		this.barang = barang;
	}

	public int getJumlahBarang() {
		return jumlahBarang;
	}

	public void setJumlahBarang(int jumlahBarang) {
		this.jumlahBarang = jumlahBarang;
	}

	public long getTotalHarga() {
		return totalHarga;
	}

	public void setTotalHarga(long totalHarga) {
		this.totalHarga = totalHarga;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionBy() {
		return transactionBy;
	}

	public void setTransactionBy(String transactionBy) {
		this.transactionBy = transactionBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
