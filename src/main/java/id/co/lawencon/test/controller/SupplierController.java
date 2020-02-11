package id.co.lawencon.test.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public SpesifikResponseDto getKodeSupplier() {
		List<Object[]> response = null;
		List<KodeSupplier> listCode = new ArrayList<>();

		try {

			response = supplierInterface.getSupplierCode();
			if (response.size() > 0) {
				for (Object[] supplier : response) {
									
					KodeSupplier code = new KodeSupplier();
					code.setIdSuppiler(((BigInteger) supplier[0]).longValue());
					code.setNamaSuppiler((String) supplier[1]);
					listCode.add(code);

				}

				spesRsep.setRespCode("200");
				spesRsep.setDescription("Success !");
				spesRsep.setResult(listCode);
			} else {
				spesRsep.setRespCode("400");
				spesRsep.setDescription("Not Data Found !");
				spesRsep.setResult(null);
			}
		} catch (Exception e) {

			spesRsep.setRespCode("400");
			spesRsep.setDescription("Error : Invalid Request !");
			e.printStackTrace();

		}

		return spesRsep;

	}
	
	@RequestMapping(value = "/supplier/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SpesifikResponseDto getSupplier() {
		List<Supplier> listSupplier = new ArrayList<>();

		try {

			listSupplier = supplierInterface.findAll();
			if (listSupplier.size() > 0) {
			
				spesRsep.setRespCode("200");
				spesRsep.setDescription("Success !");
				spesRsep.setResult(listSupplier);
			} else {
				spesRsep.setRespCode("400");
				spesRsep.setDescription("Not Data Found !");
				spesRsep.setResult(null);
			}
		} catch (Exception e) {

			spesRsep.setRespCode("400");
			spesRsep.setDescription("Error : Invalid Request !");
			e.printStackTrace();

		}

		return spesRsep;

	}
	

}
