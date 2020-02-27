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
import id.co.lawencon.test.dto.DetailBarang;
import id.co.lawencon.test.dto.GeneratorFiles;
import id.co.lawencon.test.dto.ListAttachment;
import id.co.lawencon.test.dto.SpesifikResponseDto;
import id.co.lawencon.test.entity.Barang;
import id.co.lawencon.test.entity.HargaBarang;
import id.co.lawencon.test.entity.TipeBarang;
import id.co.lawencon.test.report.BarangReportExcel;
import id.co.lawencon.test.repository.BarangInterface;
import id.co.lawencon.test.repository.HargaBarangInterface;
import id.co.lawencon.test.repository.SupplierInterface;
import id.co.lawencon.test.repository.TipeBarangInterface;
import id.co.lawencon.test.util.DateUtil;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BarangController {

	SpesifikResponseDto spesRsep = new SpesifikResponseDto();
	List<ListAttachment> listAttachment = new ArrayList<ListAttachment>();
	GeneratorFiles RespGenerateFile = new GeneratorFiles();
	ListAttachment fileResp = new ListAttachment();

	@Autowired
	BarangInterface barangInterface;

	@Autowired
	HargaBarangInterface hargaBarangInterface;

	@Autowired
	SupplierInterface supplierInterface;

	@Autowired
	TipeBarangInterface tipeBarangInterface;

	
	@RequestMapping(value = "/barang/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikResponseDto saveBarang(@RequestBody DetailBarang inputBarang) {
		Barang response = null;

		try {

			Barang newInput = new Barang(); 
			newInput.setIdBarang(inputBarang.getIdBarang());
			newInput.setNamaBarang(inputBarang.getNamaBarang());
			newInput.setStockBarang(inputBarang.getStockBarang());
			newInput.setCreatedBy(inputBarang.getCreatedBy());
			newInput.setModifiedBy(inputBarang.getModifiedBy());
						
			response = barangInterface.save(newInput);
			
			if(response!=null) {
				@SuppressWarnings("unused")
				int updateRelasi = barangInterface.updateIdRelasi(
						inputBarang.getIdHarga(),
						inputBarang.getIdSupplier(),
						inputBarang.getIdType(),
						response.getIdBarang());
				
				DetailBarang resp = new DetailBarang(); 
				resp.setIdBarang(response.getIdBarang());
				resp.setNamaBarang(response.getNamaBarang());
				resp.setIdHarga(inputBarang.getIdHarga());
				resp.setIdSupplier(inputBarang.getIdSupplier());
				resp.setIdType(inputBarang.getIdType());
				resp.setStockBarang(response.getStockBarang());
				resp.setCreatedBy(response.getCreatedBy());
				resp.setCreatedDate(response.getCreatedDate());
				resp.setModifiedBy(response.getModifiedBy());
				resp.setModifiedDate(response.getModifiedDate());
				
				spesRsep.setRespCode("200");
				spesRsep.setDescription("Success !");
				spesRsep.setResult(resp);
				
			}
		} catch (Exception e) {

			spesRsep.setRespCode("400");
			spesRsep.setDescription("Error : Invalid Request !");
			e.printStackTrace();

		}

		return spesRsep;

	}

	
	@RequestMapping(value = "/barang/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikResponseDto listBarangAndSupplier(@RequestParam String startDate, @RequestParam String endDate) {

		List<Barang> list = new ArrayList<Barang>();
		try {
			
			if(startDate!=null & startDate!="") {				
				list = barangInterface.findBymodifiedDateBetween(DateUtil.startDate(startDate), DateUtil.endDate(endDate));				
			} else {
				list = barangInterface.findAll();
			}
			
			spesRsep.setRespCode("200");
			spesRsep.setDescription("Success !");
			spesRsep.setResult(list);

		} catch (Exception e) {

			spesRsep.setRespCode("400");
			spesRsep.setDescription("Error : Invalid Request !");
			e.printStackTrace();

		}
		return spesRsep;

	}
	
	
	@RequestMapping(value = "/barang/list/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Barang> listBarangdetails(@RequestParam String startDate, @RequestParam String endDate) {

		List<Barang> list = new ArrayList<Barang>();
		try {
			
			if(startDate!=null & startDate!="") {				
				list = barangInterface.findBymodifiedDateBetween(DateUtil.startDate(startDate), DateUtil.endDate(endDate));				
			} else {
				list = barangInterface.findAllByOrderByNamaBarangAsc();
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return list;

	}
	
	
	@RequestMapping(value = "/barang/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DetailBarang BarangSerachId(@RequestParam long id) {

		Barang result = new Barang();
		DetailBarang barang = new DetailBarang();
		try {		
			result = barangInterface.findOne(id);	
			barang.setIdBarang(result.getIdBarang());
			barang.setNamaBarang(result.getNamaBarang());
			barang.setIdHarga(result.getHarga().getIdHarga());
			barang.setIdType(result.getTipeBarang().getIdType());
			barang.setIdSupplier(result.getSupplier().getIdSupplier());
			barang.setStockBarang(result.getStockBarang());
			barang.setCreatedBy(result.getCreatedBy());
			barang.setCreatedDate(result.getCreatedDate());
			barang.setModifiedBy(result.getModifiedBy());
			barang.setModifiedDate(result.getModifiedDate());				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return barang;

	}
	
	
	
	
	@RequestMapping(value = "download/barang/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InputStreamResource> downloadBarang(@RequestParam String startDate, @RequestParam String endDate) throws IOException {

		List<Barang> list = new ArrayList<Barang>();
		

			if(startDate!=null & startDate!="") {				
				list = barangInterface.findBymodifiedDateBetween(DateUtil.startDate(startDate), DateUtil.endDate(endDate));				
			} else {
				list = barangInterface.findAll();
			}
			
				ByteArrayInputStream in = BarangReportExcel.BarangReportingExcel(list);
				HttpHeaders headers = new HttpHeaders();
				
				DateFormat tanggal = new SimpleDateFormat("dd-MMM-yyyy");
				headers.add("Content-Disposition",
					"attachment; filename=\"Laporan Barang " + tanggal.format(new Date()) + ".xlsx\" ");
				return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));


	}
	
	@RequestMapping(value = "/harga/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikResponseDto saveHarga(@RequestBody HargaBarang inputHarga) {

		try {

			HargaBarang response = hargaBarangInterface.save(inputHarga);

			if (response != null) {

				spesRsep.setRespCode("200");
				spesRsep.setDescription(" Success ! ");
				spesRsep.setResult(response);

			}
		} catch (Exception e) {

			e.printStackTrace();
			spesRsep.setRespCode("400");
			spesRsep.setDescription("Error : Invalid Request !");

		}

		return spesRsep;

	}
	
	
	@RequestMapping(value = "/harga/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HargaBarang> getHarga() {
		List<HargaBarang> listHarga = new ArrayList<>();

		try {
			listHarga = hargaBarangInterface.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listHarga;

	}

	
	@RequestMapping(value = "/type-barang/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikResponseDto saveTipeBarang(@RequestBody TipeBarang inputType) {

		try {

			TipeBarang response = tipeBarangInterface.save(inputType);

			if (response != null) {

				spesRsep.setRespCode("200");
				spesRsep.setDescription(" Success ! ");
				spesRsep.setResult(response);

			}
		} catch (Exception e) {

			e.printStackTrace();
			spesRsep.setRespCode("400");
			spesRsep.setDescription("Error : Invalid Request !");

		}

		return spesRsep;

	}
	
	
	@RequestMapping(value = "/type-barang/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipeBarang> getTipeBarang() {
		List<TipeBarang> listHarga = new ArrayList<>();

		try {
			listHarga = tipeBarangInterface.findAll();			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listHarga;

	}
	
	
	

}
