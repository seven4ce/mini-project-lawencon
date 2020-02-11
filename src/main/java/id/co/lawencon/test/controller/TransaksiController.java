package id.co.lawencon.test.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import id.co.lawencon.test.dto.DetailTransaksiDto;
import id.co.lawencon.test.dto.GeneratorFiles;
import id.co.lawencon.test.dto.ListAttachment;
import id.co.lawencon.test.dto.ListTransaksi;
import id.co.lawencon.test.dto.SpesifikResponseDto;
import id.co.lawencon.test.dto.SpesifikTransaksiDto;
import id.co.lawencon.test.entity.Barang;
import id.co.lawencon.test.entity.DetailTransaksi;
import id.co.lawencon.test.entity.TransaksiHeader;
import id.co.lawencon.test.report.TransaksiReportExcel;
import id.co.lawencon.test.repository.BarangInterface;
import id.co.lawencon.test.repository.DetailTransaksiInterface;
import id.co.lawencon.test.repository.TransaksiHeaderInterface;
import id.co.lawencon.test.util.DateUtil;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TransaksiController {

	SpesifikResponseDto spesRsep = new SpesifikResponseDto();
	
	List<ListAttachment> listAttachment = new ArrayList<ListAttachment>();
	GeneratorFiles RespGenerateFile = new GeneratorFiles();
	ListAttachment fileResp = new ListAttachment();

	@Autowired
	BarangInterface barangInterface;

	@Autowired
	BarangController barangController;

	@Autowired
	TransaksiHeaderInterface transaksiInterface;

	@Autowired
	DetailTransaksiInterface detailTransaksiInterface;
		
	@RequestMapping(value = "/transaksi/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikResponseDto saveTransaksi(@RequestBody TransaksiHeader inputTransaksi) {

		try {

			TransaksiHeader saveHeader = transaksiInterface.save(inputTransaksi);

			if (saveHeader != null) {

				Long id_trx = null;
				Long id_barang = null;
				Integer jumlahBarang = null;
				Integer CurrentStock = null;
				Integer stockUptoDate = null;
				List<Long> listIdTrx = new ArrayList<Long>();
				for (DetailTransaksi DetailBarang : saveHeader.getDetailTransaksi()) {

					id_trx = DetailBarang.getIdTrxDetail();
					listIdTrx.add(id_trx);

					id_barang = DetailBarang.getIdBarang();
					jumlahBarang = DetailBarang.getJumlahBarang();

					Barang currentStockBarang = barangInterface.getOne(id_barang);
					if (currentStockBarang != null) {

						CurrentStock = currentStockBarang.getStockBarang();
						if(saveHeader.getKodeTransaksi().equalsIgnoreCase("TRX-OUT")) {
							stockUptoDate = CurrentStock - jumlahBarang;
						}else if(saveHeader.getKodeTransaksi().equalsIgnoreCase("TRX-IN")){
							stockUptoDate = CurrentStock - jumlahBarang;
						}else {
							stockUptoDate = CurrentStock;
						}															
						@SuppressWarnings("unused")
						int updateStockBarang = barangInterface.updateStockBarang(stockUptoDate, id_barang);

					}
				}

				@SuppressWarnings("unused")
				int updatetrx = detailTransaksiInterface.updateTrxDetail(saveHeader.getTransactionBy(),
						saveHeader.getTransactionDate(), listIdTrx);

				spesRsep.setRespCode("200");
				spesRsep.setDescription(" Success ! ");
				spesRsep.setResult(saveHeader);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return spesRsep;

	}

	@RequestMapping(value = "/transaksi/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikTransaksiDto getTransaksi(@RequestParam String startDate, @RequestParam String endDate) {

		List<TransaksiHeader> response = new ArrayList<>();
		List<DetailTransaksiDto> result = new ArrayList<DetailTransaksiDto>();
		Barang barang = null;
		SpesifikTransaksiDto spesTrx = new SpesifikTransaksiDto();
		try {

			if(startDate!=null & startDate!="") {				
				response = transaksiInterface.findBytransactionDateBetween(DateUtil.startDate(startDate), DateUtil.endDate(endDate));				
			} else {
				response = transaksiInterface.findAll();
			}
			
			if (response != null) {
				
				for (TransaksiHeader transaksiHeader : response) {
					DetailTransaksiDto newTrx = new DetailTransaksiDto();	
					List<ListTransaksi> listtransaksi = new ArrayList<ListTransaksi>();
					for (DetailTransaksi trxDetails : transaksiHeader.getDetailTransaksi()) {	
						try {
							barang = barangInterface.findOne(trxDetails.getIdBarang());
						} catch (Exception e) {
							System.out.println("===== null =====");
						}						
						ListTransaksi newListTrx = new ListTransaksi();
						newListTrx.setBarang(barang);
						newListTrx.setIdTrxDetail(trxDetails.getIdTrxDetail());
						newListTrx.setJumlahBarang(trxDetails.getJumlahBarang());
						newListTrx.setTotalHarga(trxDetails.getTotalHarga());
						newListTrx.setTransactionBy(trxDetails.getTransactionBy());
						newListTrx.setTransactionDate(trxDetails.getTransactionDate());
						newListTrx.setModifiedBy(trxDetails.getModifiedBy());
						newListTrx.setModifiedDate(trxDetails.getModifiedDate());
						listtransaksi.add(newListTrx);						
					}
					
					newTrx.setDetailTransaksi(listtransaksi);
					newTrx.setIdTransaksi(transaksiHeader.getIdTransaksi());
					newTrx.setKodeTransaksi(transaksiHeader.getKodeTransaksi());
					newTrx.setNamaTransaksi(transaksiHeader.getNamaTransaksi());
					newTrx.setGrandTotal(transaksiHeader.getGrandTotal());
					newTrx.setJumlahBayar(transaksiHeader.getJumlahBayar());
					newTrx.setKembalian(transaksiHeader.getKembalian());
					newTrx.setTransactionBy(transaksiHeader.getTransactionBy());
					newTrx.setTransactionDate(transaksiHeader.getTransactionDate());
					newTrx.setModifiedBy(transaksiHeader.getModifiedBy());
					newTrx.setModifiedDate(transaksiHeader.getModifiedDate());
					result.add(newTrx);
					
				}
				spesTrx.setRespCode("200");
				spesTrx.setDescription(" Success ! ");
				spesTrx.setResult(result);

			}
		} catch (Exception e) {

			e.printStackTrace();
			spesTrx.setRespCode("400");
			spesTrx.setDescription("Error : Invalid Request !");

		}

		return spesTrx;

	}
	
	
	@CrossOrigin
	@RequestMapping(value = "download/transaksi/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InputStreamResource> downloadTransaksi(@RequestParam String startDate, @RequestParam String endDate) throws IOException {

					
			List<DetailTransaksiDto> list = getTransaksi(startDate,endDate).getResult();
			
			ByteArrayInputStream in = TransaksiReportExcel.TransaksiReportingExcel(list);
		
			HttpHeaders headers = new HttpHeaders();
			
			DateFormat tanggal = new SimpleDateFormat("dd-MMM-yyyy");
			headers.add("Content-Disposition",
				"attachment; filename=\"Laporan Transaksi " + tanggal.format(new Date()) + ".xlsx\" ");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}
	

}
