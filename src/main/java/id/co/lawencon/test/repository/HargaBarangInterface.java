package id.co.lawencon.test.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import id.co.lawencon.test.entity.HargaBarang;

@Repository
public interface HargaBarangInterface extends JpaRepository<HargaBarang, Long> {

	@Transactional
	@Modifying		
	@Query(value= "Delete From {h-schema}harga where id_harga = ?1 ", nativeQuery=true)
	int deleteHarga(long id);

}
