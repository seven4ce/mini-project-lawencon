package id.co.lawencon.test.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.lawencon.test.entity.Barang;

@Repository
public interface BarangInterface extends JpaRepository<Barang, Long> {

	
	@Transactional
	@Modifying		
	@Query(value= "UPDATE {h-schema}barang SET stock_barang=?1 where id_barang = ?2 ", nativeQuery=true)
	int updateStockBarang(Integer stockUptoDate, Long id_barang);

	@Transactional
	@Modifying		
	@Query(value= "UPDATE {h-schema}barang SET id_harga=?1, kode_supplier=?2, id_type=?3 where id_barang = ?4 ", nativeQuery=true)
	int updateIdRelasi(long idHarga, long idSupplier, long idType, long idBarang);

	List<Barang> findBymodifiedDateBetween(Date startDate, Date endDate);

}
