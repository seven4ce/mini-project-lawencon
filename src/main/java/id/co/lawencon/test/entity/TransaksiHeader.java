package id.co.lawencon.test.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "transaction_date", "modified_date" }, allowGetters = true)
@Table(name = "transaksi")
public class TransaksiHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaksi")
	private long idTransaksi;

	@Column(name = "nama_transaksi")
	private String namaTransaksi;

	@Column(name = "kode_transaksi")
	private String kodeTransaksi;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_transaksi")
	private Set<DetailTransaksi> detailTransaksi;

	@Column(name = "grand_total")
	private long grandTotal;

	@Column(name = "jumlah_bayar")
	private long jumlahBayar;

	@Column(name = "kembalian")
	private long kembalian;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
	@Column(name = "transaction_date")
	@CreatedDate
	private Date transactionDate;

	@Column(name = "transaction_by")
	private String transactionBy;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
	@Column(name = "modified_date")
	@LastModifiedDate
	private Date modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	public TransaksiHeader() {
		super();
	}

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

	public Set<DetailTransaksi> getDetailTransaksi() {
		return detailTransaksi;
	}

	public void setDetailTransaksi(Set<DetailTransaksi> detailTransaksi) {
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
