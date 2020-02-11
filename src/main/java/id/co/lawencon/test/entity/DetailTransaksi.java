package id.co.lawencon.test.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "transaction_date", "modified_date" }, allowGetters = true)
@Table(name = "detail_transaksi")
public class DetailTransaksi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trx_detail")
	private long idTrxDetail;

	@Column(name = "id_barang")
	private long idBarang;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_transaksi", insertable = false, updatable = false)
	@JsonIgnore
	private TransaksiHeader transaksi;

	@Column(name = "jumlah_barang")
	private int jumlahBarang;

	@Column(name = "total_harga")
	private long totalHarga;

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

	public DetailTransaksi() {
		super();
	}

	public long getIdTrxDetail() {
		return idTrxDetail;
	}

	public void setIdTrxDetail(long idTrxDetail) {
		this.idTrxDetail = idTrxDetail;
	}

	public TransaksiHeader getTransaksi() {
		return transaksi;
	}

	public void setTransaksi(TransaksiHeader transaksi) {
		this.transaksi = transaksi;
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

	public long getIdBarang() {
		return idBarang;
	}

	public void setIdBarang(long idBarang) {
		this.idBarang = idBarang;
	}


	
	

}
