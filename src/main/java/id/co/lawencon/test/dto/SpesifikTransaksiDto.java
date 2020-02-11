package id.co.lawencon.test.dto;

import java.util.List;

import id.co.lawencon.test.dto.DetailTransaksiDto;

public class SpesifikTransaksiDto {
	
	private String respCode;	
	private String description;
	private List<DetailTransaksiDto> result;
	
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<DetailTransaksiDto> getResult() {
		return result;
	}
	public void setResult(List<DetailTransaksiDto> result) {
		this.result = result;
	}
	
	
	
	
	

}
