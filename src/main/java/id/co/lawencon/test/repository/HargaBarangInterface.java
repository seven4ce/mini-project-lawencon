package id.co.lawencon.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.co.lawencon.test.entity.HargaBarang;

@Repository
public interface HargaBarangInterface extends JpaRepository<HargaBarang, Long> {

}
