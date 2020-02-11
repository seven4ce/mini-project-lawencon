package id.co.lawencon.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.co.lawencon.test.entity.TransaksiHeader;


@Repository
public interface TransaksiHeaderInterface extends JpaRepository<TransaksiHeader, Long> {

	List<TransaksiHeader> findBytransactionDateBetween(Date startDate, Date endDate);

	
	
}
