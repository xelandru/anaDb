package spreadsheet;


import domain.Candidate;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CandidateSpreadSheet {


    private static final String TITLE = "Candidates";


    public static void export(List<Candidate> candidates, String path) {


        try (FileOutputStream fos = new FileOutputStream(path);
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet(TITLE);

            CreationHelper createHelper = workbook.getCreationHelper();
            Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            int rowNumber = 0;
            Row headerRow = sheet.createRow(rowNumber++);
            headerRow.setRowStyle(headerCellStyle);


            for (Columns col : Columns.values()) {
                Cell cell = headerRow.createCell(col.ordinal());
                cell.setCellValue(col.toString());
                cell.setCellStyle(headerCellStyle);
            }

            for (Candidate candidate : candidates) {
                Row row = sheet.createRow(rowNumber++);
                row.createCell(Columns.NAME.ordinal()).setCellValue(candidate.getName());
                row.createCell(Columns.JOB.ordinal()).setCellValue(candidate.getJob());
                row.createCell(Columns.EMAIL.ordinal()).setCellValue(candidate.getEmail());
                row.createCell(Columns.PHONE.ordinal()).setCellValue(candidate.getPhone());
                row.createCell(Columns.LINK.ordinal()).setCellValue(candidate.getLink());
                row.getCell(Columns.LINK.ordinal()).setHyperlink(link);
                row.createCell(Columns.INFO.ordinal()).setCellValue(candidate.getInfo());
            }

            for (Columns columns : Columns.values())
                sheet.autoSizeColumn(columns.ordinal());

            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException("Could not export excel file", e);
        }
    }

}