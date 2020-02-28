package id.co.lawencon.test.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import id.co.lawencon.test.entity.TipeBarang;

@Repository
public interface TipeBarangInterface extends JpaRepository<TipeBarang, Long> {

	@Transactional
	@Modifying		
	@Query(value= "Delete From {h-schema}tipe_barang where id_type = ?1 ", nativeQuery=true)
	int deleteTipeBarang(long id);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
