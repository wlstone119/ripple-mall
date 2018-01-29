package com.stone.ripple.util.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public class XSSFExcelUtil {

    private static Logger      logger = LoggerFactory.getLogger(XSSFExcelUtil.class);

    private final XSSFWorkbook workbook;
    private XSSFSheet          sheet;
    private XSSFRow            titleRow;
    private XSSFRow            headRow;

    /**
     * 构造器
     *
     * @param file
     * @throws IOException
     */
    public XSSFExcelUtil(FileInputStream file) throws IOException{
        logger.debug("");
        // 创建对Excel工作簿文件的引用
        workbook = new XSSFWorkbook(file);

    }


    public XSSFExcelUtil(FileItem fileInput){
        InputStream isInputStream = null;
        try {
            isInputStream = fileInput.getInputStream();
            workbook = new XSSFWorkbook(isInputStream);
        } catch (Exception e) {
            throw new RuntimeException("Excel版本不对",e);
        } finally {
            if (isInputStream != null) {
                try {
                    isInputStream.close();
                } catch (Exception e) {

                }
            }
        }
    }
    public XSSFExcelUtil(InputStream isInputStream){
        try {
            workbook = new XSSFWorkbook(isInputStream);
        } catch (Exception e) {
            throw new RuntimeException("Excel版本不对",e);
        } finally {
            if (isInputStream != null) {
                try {
                    isInputStream.close();
                } catch (Exception e) {

                }
            }
        }
    }
    /**
     * 构造器
     *
     * @param file
     * @throws IOException
     */
    public XSSFExcelUtil() throws IOException{
        logger.debug("");
        // 创建对Excel工作簿文件的引用
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
    }

    /**
     * 另存为
     *
     * @param out
     * @throws IOException
     */
    public void saveAs(OutputStream out) throws IOException {
        workbook.write(out);
    }

    /**
     * 构造器
     *
     * @param file
     * @param sheetId
     * @throws IOException
     */
    public XSSFExcelUtil(FileInputStream file, int sheetId) throws IOException{
        // 创建对Excel工作簿文件的引用
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheetAt(sheetId);

    }

    /**
     * 设置操作的sheet页数
     *
     * @param id
     */
    public void setSheetId(int id) {
        sheet = workbook.getSheetAt(id);

    }

    /**
     * 把excel转换成List<Map>格式
     *
     * @param sheetNo 需要转换的数据所在的sheet次序号(0,1,2...n)
     * @return
     */
    public List<Map> excelToMapList(int sheetNo) {
        XSSFSheet sheet = this.workbook.getSheetAt(sheetNo);
        int firstRowNum = sheet.getFirstRowNum();
        return excelToMapList(sheetNo, firstRowNum, firstRowNum + 1);
    }

    /**
     * 把excel转换成List<Map>格式
     *
     * @param sheetNo需要转换的数据所在的sheet次序号(0,1,2...n)
     * @param keyRowNo 作为key的行号 （0,1,2...n）
     * @param dataStartRowNo第一行数据的行号 （1,2...n）
     * @return
     */
    public List<Map> excelToMapList(int sheetNo, int keyRowNo, int dataStartRowNo) {
        return excelToMapList(sheetNo, keyRowNo, keyRowNo, dataStartRowNo);
    }

    /**
     * 标题从多行进行合并得到。
     *
     * @param sheetNo
     * @param keyRowNoFrom
     * @param keyRowNoTo
     * @param dataStartRowNo
     * @return
     */
    public List<Map> excelToMapList(int sheetNo, int keyRowNoFrom, int keyRowNoTo, int dataStartRowNo) {
        XSSFSheet sheet = this.workbook.getSheetAt(sheetNo);
        List rowMapList = new ArrayList();
        String[] keyList = new String[200];
        for (int i = keyRowNoFrom; i <= keyRowNoTo; i++) {
            XSSFRow mapKeyRow = sheet.getRow(i);
            String lstKey = null;
            for (int j = mapKeyRow.getFirstCellNum(); j < mapKeyRow.getLastCellNum(); j++) {
                XSSFCell col = mapKeyRow.getCell(j);
                String key = col.getRichStringCellValue().getString();
                String keyx = keyList[j];
                if (key == null) {
                    key = keyx;
                } else if (keyx != null) key = keyx + key;

                if (key == null || "".equals(key)) {
                    key = lstKey;
                }
                // 有时会不小心给表头加上空格，在excel里还不容易看出来。
                if (key != null) key = key.trim();
                lstKey = key;
                keyList[j] = key;
            }
        }
        int lastRowNum = sheet.getLastRowNum();
        for (int i = dataStartRowNo; i <= lastRowNum; ++i) {
            XSSFRow dataRow = sheet.getRow(i);
            if (dataRow == null) continue;
            Map rowMap = new HashMap();
            for (int j = dataRow.getFirstCellNum(); j < dataRow.getLastCellNum(); ++j) {
                String key = keyList[j];
                if (key == null || key.equals("")) {
                    continue;
                }
                Object value = getCellValue(dataRow.getCell(j));
                rowMap.put(key, value);
            }
            rowMapList.add(rowMap);
        }
        return rowMapList;
    }

    /**
     * 获取某个单元格的值，并做一定的类型判断。
     *
     * @param cell
     * @return
     */
    public Object getCellValue(XSSFCell cell) {
        Object value = null;
        if (cell != null) {
            int cellType = cell.getCellType();
            XSSFCellStyle style = cell.getCellStyle();
            short format = style.getDataFormat();
            switch (cellType) {
                case XSSFCell.CELL_TYPE_NUMERIC:
                    double numTxt = cell.getNumericCellValue();
                    if (format == 22 || format == 14 || format == 176) value = HSSFDateUtil.getJavaDate(numTxt);
                    else value = numTxt;
                    break;
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    boolean booleanTxt = cell.getBooleanCellValue();
                    value = booleanTxt;
                    break;
                case XSSFCell.CELL_TYPE_BLANK:
                    value = null;
                    break;
                case XSSFCell.CELL_TYPE_FORMULA:
                    XSSFFormulaEvaluator eval = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
                    eval.evaluateInCell(cell);
                    value = getCellValue(cell);
                    break;
                case XSSFCell.CELL_TYPE_STRING:
                    XSSFRichTextString rtxt = cell.getRichStringCellValue();
                    if (rtxt == null) {
                        break;
                    }
                    String txt = rtxt.getString();
                    value = txt;
                    break;
                default:
                    // System.out.println(cell.getColumnIndex()+" col cellType="+cellType);
            }
        }
        return value;

    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleRow = sheet.createRow(0);
        XSSFCell cell = titleRow.createCell((short) 0);
        setStringCell(cell, title);
    }

    /**
     * 设置表头
     *
     * @param head
     */
    public void setHeader(List<String> head) {
        headRow = sheet.createRow(titleRow.getRowNum() + 1);
        for (short i = 0; i < head.size(); i++) {
            XSSFCell cell = headRow.createCell(i);
            setStringCell(cell, head.get(i));
        }
    }

    /**
     * 设置表头,行数作为参数
     *
     * @param head
     */
    public void setHeader(List<String> head, int rowNum) {
        headRow = sheet.createRow(rowNum);
        for (short i = 0; i < head.size(); i++) {
            XSSFCell cell = headRow.createCell(i);
            setStringCell(cell, head.get(i));
        }
    }

    /**
     * 设置Merge的区域 合成一个cell
     *
     * @Method setMergeRange
     * @Function 功能描述：
     * @param rowstart
     * @param rowend
     * @param colstart
     * @param colend
     * @Date 2011-1-14
     */
    public void setMergeRange(int rowstart, int rowend, int colstart, int colend) {
        sheet.addMergedRegion(new CellRangeAddress(rowstart, rowend, colstart, colend));
    }

    /**
     * 设置某个cell的样式
     *
     * @Method setStyle
     * @Function 功能描述： HSSFCellStyle style = workbook.createCellStyle(); style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     * HSSFFont font = workbook.createFont(); font.setColor(HSSFFont.COLOR_NORMAL);
     * font.setBoldweight(font.BOLDWEIGHT_BOLD); font.setFontHeightInPoints((short) 22); style.setFont(font);
     * @param head
     * @param rowNum
     * @Date 2011-1-14
     */
    public void setStyle(int rowNum, int colNum, XSSFCellStyle style) {
        sheet.getRow(rowNum).getCell(colNum).setCellStyle(style);
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setRowData(List list) {
        Iterator it = list.iterator();
        int i = headRow.getRowNum() + 1;
        while (it.hasNext()) {
            // 创建新行
            XSSFRow row = sheet.createRow(i++);
            // 创建单元格
            Iterator cellIt = ((List) it.next()).iterator();
            int j = 0;
            while (cellIt.hasNext()) {
                XSSFCell cell = row.createCell((short) j++);
                setStringCell(cell, (String) cellIt.next());
            }
        }
    }

    /**
     * 设置指定行的数据
     *
     * @param rownum
     * @param list
     */
    public void setRowData(int rownum, List list) {
        Iterator it = list.iterator();
        // 创建新行
        XSSFRow row = sheet.createRow(rownum);
        // 创建单元格
        int j = 0;
        while (it.hasNext()) {
            XSSFCell cell = row.createCell((short) j++);
            setStringCell(cell, (String) it.next());
        }
    }

    /**
     * 根据坐标填充单元格数据
     *
     * @param rownum
     * @param cellnum
     * @param value
     */
    public void setData(int rownum, short cellnum, Object value) {

        XSSFRow row = sheet.getRow(rownum) == null ? sheet.createRow(rownum) : sheet.getRow(rownum);

        XSSFCell cell = row.getCell(cellnum) == null ? row.createCell(cellnum) : row.getCell(cellnum);

        setStringCell(cell, (String) value);
    }

    /**
     * 填充字符串
     *
     * @param cell
     * @param value
     */
    private void setStringCell(XSSFCell cell, String value) {
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        // cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文 TODO JAR包版本更换
        cell.setCellValue(value);
    }

    /**
     * 输出
     *
     * @param out
     * @throws IOException
     */
    public void write(ServletOutputStream out) throws IOException {
        workbook.write(out);
    }

    /**
     * 输出
     *
     * @param response
     * @param fileName
     * @throws IOException
     */
    public void write(HttpServletResponse response, String fileName) throws IOException {

        response.reset();// 清空
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        String filename = new String((fileName + ".xlsx").getBytes("gb2312"), "ISO8859_1");
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        response.setHeader("Content-disposition", "attachment; filename=" + filename);

        write(out);
        out.close();
    }

    public static void main(String[] args) {
        String path = "/Users/xguo/Downloads/mix_download/";
 try {
    InputStream filestream=new FileInputStream(new File("d:/1.xlsx"));
    XSSFExcelUtil excel = new XSSFExcelUtil(filestream);
    List<Map> listMap = (List<Map>) excel.excelToMapList(0);
    for (Map<String, Object> valueMap : listMap) {
        Double resourceId = (Double) valueMap.get("账单月");
        Double name = (Double) valueMap.get("账单日");
        System.out.println(resourceId + name);
    }
} catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}


    }

}
