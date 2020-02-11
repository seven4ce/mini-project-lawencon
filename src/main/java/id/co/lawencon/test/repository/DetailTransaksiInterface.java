package id.co.lawencon.test.repository;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import id.co.lawencon.test.entity.DetailTransaksi;


@Repository
public interface DetailTransaksiInterface extends JpaRepository<DetailTransaksi, Long> {

	@Transactional
	@Modifying		
	@Query(value= "UPDATE {h-schema}detail_transaksi SET transaction_by=?1, transaction_date=?2 where id_trx_detail in ?3 ", nativeQuery=true)
	int updateTrxDetail(String transactionBy, Date transactionDate, List<Long> listIdTrx);

	
}
