package id.co.lawencon.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import id.co.lawencon.test.entity.Supplier;

@Repository
public interface SupplierInterface extends JpaRepository<Supplier, Long> {

	@Query(value= "select kode_supplier, nama_supplier from {h-schema}supplier order by kode_supplier asc ", nativeQuery=true)
	List<Object[]> getSupplierCode();

}
