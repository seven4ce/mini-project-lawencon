package id.co.lawencon.test.dto;

import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DetailTransaksiDto {

	private long idTransaksi;
	private String namaTransaksi;
	private String kodeTransaksi;
	private List<ListTransaksi> detailTransaksi;
	private long grandTotal;
	private long jumlahBayar;
	private long kembalian;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
	private Date transactionDate;

	private String transactionBy;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
	private Date modifiedDate;

	private String modifiedBy;

	public long getIdTransaksi() {
		return idTransaksi;
	}

	public void setIdTransaksi(long idTransaksi) {
		this.idTransaksi = idTransaksi;
	}

	public String getNamaTransaksi() {
		return namaTransaksi;
	}

	public void setNamaTransaksi(String namaTransaksi) {
		this.namaTransaksi = namaTransaksi;
	}

	public String getKodeTransaksi() {
		return kodeTransaksi;
	}

	public void setKodeTransaksi(String kodeTransaksi) {
		this.kodeTransaksi = kodeTransaksi;
	}

	public List<ListTransaksi> getDetailTransaksi() {
		return detailTransaksi;
	}

	public void setDetailTransaksi(List<ListTransaksi> detailTransaksi) {
		this.detailTransaksi = detailTransaksi;
	}

	public long getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(long grandTotal) {
		this.grandTotal = grandTotal;
	}

	public long getJumlahBayar() {
		return jumlahBayar;
	}

	public void setJumlahBayar(long jumlahBayar) {
		this.jumlahBayar = jumlahBayar;
	}

	public long getKembalian() {
		return kembalian;
	}

	public void setKembalian(long kembalian) {
		this.kembalian = kembalian;
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
