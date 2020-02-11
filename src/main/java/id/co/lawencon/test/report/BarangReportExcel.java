package id.co.lawencon.test.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import id.co.lawencon.test.entity.Barang;

public class BarangReportExcel {
	public static ByteArrayInputStream BarangReportingExcel(List<Barang> listBarang) throws IOException {
		
		String[] Columns = { "NAMA BARANG", "KODE BARANG", "KODE SUPPLIER", "HARGA SATUAN",
				"STOCK BARANG", "NAMA SUPPLIER", "PHONE NO", "ALAMAT" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("Laporan Stock Barang");
			CellStyle headerCellStyale = workbook.createCellStyle();
			headerCellStyale.setAlignment(HorizontalAlignment.CENTER);
			headerCellStyale.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			Row headerTitle = sheet.createRow(0);

			Cell cells = headerTitle.createCell(0); // Creating a cell
			cells.setCellValue("Laporan Stock Barang");

			Date date1 = listBarang.get(0).getModifiedDate();
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
			String strDate = sdf.format(date1);

			Row headerTitle1 = sheet.createRow(1);
			Cell cells1 = headerTitle1.createCell(0); // Creating a cell
			cells1.setCellValue("REPORTING PERIODE : " + strDate);

			Row headerRow = sheet.createRow(2);

			for (int col = 0; col < Columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(Columns[col]);

			}

			int rowIdx = 3;
			for (Barang trx : listBarang) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(trx.getNamaBarang());
				row.createCell(1).setCellValue(trx.getIdBarang());
				row.createCell(2).setCellValue(trx.getSupplier().getIdSupplier());
				row.createCell(3).setCellValue(trx.getHarga().getHargaSatuan());
				row.createCell(4).setCellValue(trx.getStockBarang());
				row.createCell(5).setCellValue(trx.getSupplier().getNamaSupplier());
				row.createCell(6).setCellValue(trx.getSupplier().getPhoneNo());
				row.createCell(7).setCellValue(trx.getSupplier().getAlamat());

			}

			for (int i = 0; i < Columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

}
