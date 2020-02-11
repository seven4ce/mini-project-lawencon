package id.co.lawencon.test.dto;

import java.util.List;

public class GeneratorFiles {
	
	private String Code;
	private String Description;
	private List<ListAttachment> file;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public List<ListAttachment> getFile() {
		return file;
	}
	public void setFile(List<ListAttachment> file) {
		this.file = file;
	}
	
	

}
