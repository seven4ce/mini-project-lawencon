package id.co.lawencon.test.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.lawencon.test.dto.KodeSupplier;
import id.co.lawencon.test.dto.SpesifikResponseDto;
import id.co.lawencon.test.entity.Supplier;
import id.co.lawencon.test.repository.SupplierInterface;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SupplierController {

	SpesifikResponseDto spesRsep = new SpesifikResponseDto();

	@Autowired
	SupplierInterface supplierInterface;

	@RequestMapping(value = "/supplier/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikResponseDto saveSupplier(@RequestBody Supplier inputTransaksi) {

		try {

			Supplier response = supplierInterface.save(inputTransaksi);

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

	@RequestMapping(value = "/supplier/listCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KodeSupplier> getKodeSupplier() {
		List<Object[]> response = null;
		List<KodeSupplier> listCode = new ArrayList<>();

		try {

			response = supplierInterface.getSupplierCode();
				for (Object[] supplier : response) {
									
					KodeSupplier code = new KodeSupplier();
					code.setIdSuppiler(((BigInteger) supplier[0]).longValue());
					code.setNamaSuppiler((String) supplier[1]);
					listCode.add(code);

				}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return listCode;

	}
	

	
	@RequestMapping(value = "/supplier/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Supplier> getSupplier() {
		List<Supplier> listSupplier = new ArrayList<>();

		try {

			listSupplier = supplierInterface.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listSupplier;

	}
	
	@RequestMapping(value = "/supplier/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Supplier getSupplierById(@RequestParam long id) {
		Supplier supplier = new Supplier();
		try {
			supplier = supplierInterface.findOne(id);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplier;
	}
	

	@RequestMapping(value = "/supplier/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikResponseDto deleteSupplier(@PathVariable long id) {

		try {

			int response = supplierInterface.deleteSupplier(id);

			if (response > 0) {
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
	
	

}
