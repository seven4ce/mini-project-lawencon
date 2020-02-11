package id.co.lawencon.test.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import id.co.lawencon.test.dto.DetailTransaksiDto;
import id.co.lawencon.test.dto.ListTransaksi;

public class TransaksiReportExcel {
	
	
	public static ByteArrayInputStream TransaksiReportingExcel(List<DetailTransaksiDto> listTransaksi) throws IOException {
		
		String[] Columns = { 
				"NAMA TRANSAKSI", 
				"ID TRANSAKSI", 
				"KODE BARANG", 
				"KODE TRANSAKSI", 
				"JUMLAH BARANG", 
				"NAMA BARANG", 
				"HARGA SATUAN", 
				"KODE SUPPLIER", 
				"NAMA SUPPLIER", 
				"PHONE NUMBER", 
				"ALAMAT", 
				"JUMLAH BARANG",
				"TOTAL HARGA", 
				"JUMLAH BAYAR", 
				"KEMBALIAN", 
				"TRANSACTION BY", 
				"TRANSACTION DATE", 
				"MODIFIED BY", 
				"MODIFIED DATE" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper creationHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Transaksi Penjualan Dan Pembelian");
			CellStyle headerCellStyale = workbook.createCellStyle();
			headerCellStyale.setAlignment(HorizontalAlignment.CENTER);
			headerCellStyale.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			Row headerTitle = sheet.createRow(0);
			Cell cells = headerTitle.createCell(0); // Creating a cell
			cells.setCellValue("Transaksi Penjualan Dan Pembelian");

			Date date1 = listTransaksi.get(0).getTransactionDate();
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
			String strDate = sdf.format(date1);

			Row headerTitle1 = sheet.createRow(1);			
			Cell cells1 = headerTitle1.createCell(0); // Creating a cell
			cells1.setCellValue("REPORTING PERIODE : " + strDate);
								
//			Row totalQtyOut1 = sheet.createRow(listTransaksi.size()+5);
//			Cell cells3 = totalQtyOut1.createCell(11);
//			cells3.setCellValue("TOTAL QTY OUT");
//			Cell cells4 = totalQtyOut1.createCell(12);
//			cells4.setCellValue("TOTAL QTY IN");
//			Cell cells5 = totalQtyOut1.createCell(13);
//			cells5.setCellValue("TOTAL HARGA PENJUALAN");
//			Cell cells6 = totalQtyOut1.createCell(14);
//			cells6.setCellValue("TOTAL HARGA PEMBELIAN");
//			
//			Row totalQtyOut2 = sheet.createRow(listTransaksi.size()+6);
//			Cell cells7 = totalQtyOut2.createCell(11);
//			cells7.setCellValue(listTransaksi.get(0).);
//			Cell cells8 = totalQtyOut2.createCell(12);
//			cells8.setCellValue(listTransaksi.getTotalQtyPembelian());
//			Cell cells9 = totalQtyOut2.createCell(13);
//			cells9.setCellValue(listTransaksi.getTotalHargaPenjualan());
//			Cell cells10 = totalQtyOut2.createCell(14);
//			cells10.setCellValue(listTransaksi.getTotalHargaPembelian());

			Row headerRow = sheet.createRow(2);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);

			}

			CellStyle date = workbook.createCellStyle();
			date.setDataFormat(creationHelper.createDataFormat().getFormat("dd MMMM yyyy"));

			int rowIdx = 3;
			for (int i = 0; i < listTransaksi.size(); i++) {		
				for (ListTransaksi details : listTransaksi.get(i).getDetailTransaksi()) {
					
					Row row = sheet.createRow(rowIdx++);					

					row.createCell(0).setCellValue(listTransaksi.get(i).getNamaTransaksi());
					row.createCell(1).setCellValue(listTransaksi.get(i).getIdTransaksi());
					row.createCell(2).setCellValue(details.getBarang().getIdBarang());
					row.createCell(3).setCellValue(listTransaksi.get(i).getKodeTransaksi());
					row.createCell(4).setCellValue(details.getJumlahBarang());				
					row.createCell(5).setCellValue(details.getBarang().getNamaBarang());
					row.createCell(6).setCellValue(details.getBarang().getHarga().getHargaSatuan());
					row.createCell(7).setCellValue(details.getBarang().getSupplier().getIdSupplier());
					row.createCell(8).setCellValue(details.getBarang().getSupplier().getNamaSupplier());
					row.createCell(9).setCellValue(details.getBarang().getSupplier().getPhoneNo());
					row.createCell(10).setCellValue(details.getBarang().getSupplier().getAlamat());
					row.createCell(11).setCellValue(details.getJumlahBarang());
					row.createCell(12).setCellValue(listTransaksi.get(i).getGrandTotal());
					row.createCell(13).setCellValue(listTransaksi.get(i).getJumlahBayar());
					row.createCell(14).setCellValue(listTransaksi.get(i).getKembalian());			
					row.createCell(15).setCellValue(listTransaksi.get(i).getTransactionBy());
					Cell trxDate = row.createCell(16);
					trxDate.setCellValue(listTransaksi.get(i).getTransactionDate());
					trxDate.setCellStyle(date);
					row.createCell(17).setCellValue(listTransaksi.get(i).getModifiedBy());
					Cell modDate = row.createCell(18);
					modDate.setCellValue(listTransaksi.get(i).getModifiedDate());
					modDate.setCellStyle(date);
				
				}
			}

			for (int i = 0; i < Columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());
		
		}
	}

}
