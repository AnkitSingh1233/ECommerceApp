package in.nareshit.ankit.util;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.entity.CustomerExcelFile;

public class ExcelHelper {

	
    public static boolean hasExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            

    	
    }

    public static List<CustomerExcelFile> excelToCustomerList(InputStream is) {
        List<CustomerExcelFile> customerList = new ArrayList();

        try (Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0); // first sheet
            Iterator<Row> rows = sheet.iterator();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                CustomerExcelFile customer = new CustomerExcelFile();

                // assuming column order in Excel: firstName, lastName, gender, country, age, date
                Cell firstNameCell = currentRow.getCell(0);
                Cell lastNameCell = currentRow.getCell(1);
                Cell genderCell = currentRow.getCell(2);
                Cell countryCell = currentRow.getCell(3);
                Cell ageCell = currentRow.getCell(4);
                Cell dateCell = currentRow.getCell(5);

                customer.setFirstName(firstNameCell.getStringCellValue());
                customer.setLastName(lastNameCell.getStringCellValue());
                customer.setGender(genderCell.getStringCellValue());
                customer.setCountry(countryCell.getStringCellValue());
                customer.setAge((int) ageCell.getNumericCellValue());
                 java.util.Date utilDate = dateCell.getDateCellValue();
                customer.setDate(new Date(utilDate.getTime()));

                customerList.add(customer);
            }

        } catch (Exception e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }

        return customerList;
    }
	
	
}
