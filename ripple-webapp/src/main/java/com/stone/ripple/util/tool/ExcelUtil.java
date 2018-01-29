package com.stone.ripple.util.tool;

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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public class ExcelUtil {

    private static Logger      logger = LoggerFactory.getLogger(ExcelUtil.class);

    private final HSSFWorkbook workbook;
    private HSSFSheet          sheet;
    private HSSFRow            titleRow;
    private HSSFRow            headRow;

    /**
     * 构造器
     *
     * @param file
     * @throws IOException
     */
    public ExcelUtil(FileInputStream file) throws IOException{
        logger.debug("");
        // 创建对Excel工作簿文件的引用
        workbook = new HSSFWorkbook(file);

    }

    public ExcelUtil(String filePath){
        POIFSFileSystem fs;
        try {
            fs = new POIFSFileSystem(new FileInputStream(filePath));
            workbook = new HSSFWorkbook(fs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ExcelUtil(FileItem fileInput){
        InputStream isInputStream = null;
        try {
            isInputStream = fileInput.getInputStream();
            workbook = new HSSFWorkbook(isInputStream);
        } catch (Exception e) {
            throw new RuntimeException("Excel版本不对");
        } finally {
            if (isInputStream != null) {
                try {
                    isInputStream.close();
                } catch (Exception e) {

                }
            }
        }
    }
    public ExcelUtil(InputStream isInputStream){
        try {
            workbook = new HSSFWorkbook(isInputStream);
        } catch (Exception e) {
            throw new RuntimeException("Excel版本不对");
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
    public ExcelUtil() throws IOException{
        logger.debug("");
        // 创建对Excel工作簿文件的引用
        workbook = new HSSFWorkbook();
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
    public ExcelUtil(FileInputStream file, int sheetId) throws IOException{
        // 创建对Excel工作簿文件的引用
        workbook = new HSSFWorkbook(file);
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
        HSSFSheet sheet = this.workbook.getSheetAt(sheetNo);
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
        HSSFSheet sheet = this.workbook.getSheetAt(sheetNo);
        List rowMapList = new ArrayList();
        String[] keyList = new String[200];
        for (int i = keyRowNoFrom; i <= keyRowNoTo; i++) {
            HSSFRow mapKeyRow = sheet.getRow(i);
            String lstKey = null;
            for (int j = mapKeyRow.getFirstCellNum(); j < mapKeyRow.getLastCellNum(); j++) {
                HSSFCell col = mapKeyRow.getCell(j);
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
            HSSFRow dataRow = sheet.getRow(i);
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
    public Object getCellValue(HSSFCell cell) {
        Object value = null;
        if (cell != null) {
            int cellType = cell.getCellType();
            HSSFCellStyle style = cell.getCellStyle();
            short format = style.getDataFormat();
            switch (cellType) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    double numTxt = cell.getNumericCellValue();
                    if (format == 22 || format == 14 || format == 176) value = HSSFDateUtil.getJavaDate(numTxt);
                    else value = numTxt;
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    boolean booleanTxt = cell.getBooleanCellValue();
                    value = booleanTxt;
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    value = null;
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    HSSFFormulaEvaluator eval = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
                    eval.evaluateInCell(cell);
                    value = getCellValue(cell);
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    HSSFRichTextString rtxt = cell.getRichStringCellValue();
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
        HSSFCell cell = titleRow.createCell((short) 0);
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
            HSSFCell cell = headRow.createCell(i);
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
            HSSFCell cell = headRow.createCell(i);
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
    public void setStyle(int rowNum, int colNum, HSSFCellStyle style) {
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
            HSSFRow row = sheet.createRow(i++);
            // 创建单元格
            Iterator cellIt = ((List) it.next()).iterator();
            int j = 0;
            while (cellIt.hasNext()) {
                HSSFCell cell = row.createCell((short) j++);
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
        HSSFRow row = sheet.createRow(rownum);
        // 创建单元格
        int j = 0;
        while (it.hasNext()) {
            HSSFCell cell = row.createCell((short) j++);
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

        HSSFRow row = sheet.getRow(rownum) == null ? sheet.createRow(rownum) : sheet.getRow(rownum);

        HSSFCell cell = row.getCell(cellnum) == null ? row.createCell(cellnum) : row.getCell(cellnum);

        setStringCell(cell, (String) value);
    }

    /**
     * 填充字符串
     *
     * @param cell
     * @param value
     */
    private void setStringCell(HSSFCell cell, String value) {
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
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
        String filename = new String((fileName + ".xls").getBytes("gb2312"), "ISO8859_1");
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        response.setHeader("Content-disposition", "attachment; filename=" + filename);

        write(out);
        out.close();
    }

    public static void main(String[] args) {
        String path = "/Users/xguo/Downloads/mix_download/";

        ExcelUtil excel = new ExcelUtil("d:/2007.xls");
        List<Map> listMap = (List<Map>) excel.excelToMapList(0);
        for (Map<String, Object> valueMap : listMap) {
            Double resourceId = (Double) valueMap.get("律师");
            String name = (String) valueMap.get("律所");
            System.out.println(resourceId + name);
        }
    }

}
