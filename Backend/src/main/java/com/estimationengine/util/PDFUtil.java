package com.estimationengine.util;


import com.estimationengine.constants.BasicConstants;
import com.estimationengine.dto.AppEffortCountDTO;
import com.estimationengine.dto.AppSizeEffortDTO;
import com.estimationengine.dto.TotalCost;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class PDFUtil {

	private static final Logger logger = LogManager.getLogger(String.valueOf(PDFUtil.class)) ;

    public XMLSlideShow generateCostPPT(TotalCost totalCost) throws IOException {


        File file = new File(BasicConstants.FileConstants.INPUT_FILE_TEMPLATE);
        FileInputStream inputStream = new FileInputStream(file);
        XMLSlideShow ppt = new XMLSlideShow(inputStream);
        XSLFSlide slide;

        String totalCounts = String.valueOf(totalCost.getTotalNumberOfApplicatinos());
        String duration = String.valueOf(totalCost.getDuration());
        String cost = String.valueOf(totalCost.getCost());

        slide = ppt.getSlides().get(0);

        getRectangle(slide, totalCost.getCustomerName());

        Optional<String> scopes = totalCost.getScopes().stream().map(AppSizeEffortDTO::getScope).reduce((s1, s2) -> s1 + " , " + s2);
        scopes.ifPresent(s -> getScope(slide, s));

        getAppCount(slide, totalCost.getScopes(), totalCounts);
        getCostEstimate(slide);
        getTableData(slide, totalCounts, duration, cost);
        getNote(slide);

        inputStream.close();
        logger.info("Total cost estimated: {}", cost);
        return ppt;
    }


    public void getRectangle(XSLFSlide slide, String customerName) {
        double x = 30;       // X-coordinate
        double y = 30;       // Y-coordinate
        double width = 700;   // Width
        double height = 110;

        XSLFAutoShape rectangle = slide.createAutoShape();
        rectangle.setShapeType(ShapeType.ROUND_RECT);
        rectangle.setAnchor(new Rectangle2D.Double(x, y, width, height));
        Color mexicanPink = new Color(0xE2, 0x00, 0x74);
        rectangle.setFillColor(mexicanPink);

        XSLFTextParagraph p = rectangle.addNewTextParagraph();
        XSLFTextRun r = p.addNewTextRun();
        r.setText("Cost Estimation\n\n");
        r.setFontSize(30.0);
        r.setFontColor(Color.WHITE);
        r.setBold(true);
        r.setFontFamily("TeleGrotesk Next Ultra");
        XSLFTextRun r2 = p.addNewTextRun();
        r2.setText("\n\n");
        XSLFTextRun r1 = p.addNewTextRun();
        r1.setText(customerName);
        r1.setFontSize(21.0);
        r1.setFontColor(Color.WHITE);
        r1.setBold(true);
        r.setFontFamily("TeleGrotesk Next Ultra");
    }

    public static void getScope(XSLFSlide slide, String scope) {


        XSLFTextBox textBox = slide.createTextBox();
        textBox.setAnchor(new Rectangle2D.Double(30, 120, 100, 30)); // Position and size of the text box

        XSLFTextShape textShape = textBox.addNewTextParagraph().getParentShape();
        XSLFTextParagraph paragraph = textShape.addNewTextParagraph();
        XSLFTextRun textRun = paragraph.addNewTextRun();

        textRun.setText("Scopes:");
        textRun.setFontSize(18.0);
        textRun.setBold(true);
        textRun.setFontFamily("Söhne");

        XSLFTextBox textBoxForScopeList = slide.createTextBox();
        textBoxForScopeList.setAnchor(new Rectangle2D.Double(130, 130, 500, 30)); // Position and size of the text box

        XSLFTextShape textShapeForScopeList = textBoxForScopeList.addNewTextParagraph().getParentShape();
        XSLFTextParagraph paragraphForScopeList = textShapeForScopeList.addNewTextParagraph();
        XSLFTextRun textRunForScopeList = paragraphForScopeList.addNewTextRun();

        textRunForScopeList.setText(scope);
        textRunForScopeList.setFontSize(14.0);
        textRunForScopeList.setBold(true);
        textRunForScopeList.setFontFamily("Calibri");
        logger.info("Scope information added to the slide: {}", scope);
    }

    public static void getAppCount(XSLFSlide slide, List<AppSizeEffortDTO> appSizeEffortDTOS, String numberOfApplications) {
        AtomicInteger count = new AtomicInteger(1);
        XSLFTextBox textBox = slide.createTextBox();
        textBox.setAnchor(new Rectangle2D.Double(130, 175, 900, 100)); // Position and size of the text box

        XSLFTextRun text = textBox.addNewTextParagraph().addNewTextRun();
        text.setText("Total " + numberOfApplications + " Applications considered in this estimate. \n\n" + "\nEstimation Breakdown\n\n");
        text.setFontSize(14.0);
        text.setFontFamily("Calibri");

        Map<String, Integer> map = getStringIntegerMap(appSizeEffortDTOS);

        XSLFTextRun textRun = textBox.addNewTextParagraph().addNewTextRun();
        Optional<String> mapString = map.entrySet().stream().map(m -> (count.getAndIncrement()) + ")  " + m.getKey() + " : " + m.getValue()).reduce((m1, m2) -> m1 + "\n" + m2);
        mapString.ifPresent(s -> {
            textRun.setText(s);
            textRun.setFontSize(14.0);
            textRun.setFontFamily("Calibri");
        });
        
        XSLFTextRun text3 = textBox.addNewTextParagraph().addNewTextRun();
        text3.setText("\n* Kindly note that the classification of application sizes are subject to change as we conduct further due diligence and gather more information");
        text3.setFontSize(14.0);
        text3.setFontFamily("Calibri");
        logger.info("Application count and breakdown information added to the slide");
    }

    private static Map<String, Integer> getStringIntegerMap(List<AppSizeEffortDTO> appSizeEffortDTOS) {
        Map<String, Integer> map = new TreeMap<>();

        for (AppSizeEffortDTO sizeEffortDTO : appSizeEffortDTOS) {
            for (AppEffortCountDTO listItem : sizeEffortDTO.getAppEffortCounts()) {
                if (map.isEmpty()) {
                    map.put(listItem.getAppSize(), listItem.getAppCount());
                } else if (map.containsKey(listItem.getAppSize())) {
                    map.put(listItem.getAppSize(), map.get(listItem.getAppSize()) + listItem.getAppCount());
                } else {
                    map.put(listItem.getAppSize(), listItem.getAppCount());
                }
            }
        }
        logger.info("Created a map with application sizes and their counts");
        return map;
    }

    public static void getCostEstimate(XSLFSlide slide) {
        XSLFTextBox textBox = slide.createTextBox();
        textBox.setAnchor(new Rectangle2D.Double(30, 290, 200, 30)); // Position and size of the text box

        XSLFTextShape textShape = textBox.addNewTextParagraph().getParentShape();
        XSLFTextParagraph paragraph = textShape.addNewTextParagraph();
        XSLFTextRun textRun = paragraph.addNewTextRun();

        textRun.setText("Top-Level Estimate\n");
        textRun.setFontSize(18.0);
        textRun.setBold(true);
        textRun.setFontFamily("Söhne");
        logger.info("Added top-level estimate text to the slide");
    }


    public static void getTableData(XSLFSlide slide, String numberOfApplications, String duration, String totalCost) {
        
        List<String> cols = new ArrayList<>();
        cols.add("Number of Applications");
        cols.add("Duration");
        cols.add("Total Project Cost Estimate");

        // Define the table dimensions (e.g., 4 rows x 3 columns).
        int numRows = 4;
        int numCols = 3;

        // Create the table.
        XSLFTable table = slide.createTable(numRows, numCols);
        table.setAnchor(new Rectangle2D.Double(220, 340, 600, 150));

        // Format the table cells.
        for (int row = 1; row < numRows; row++) {
            table.setRowHeight(row, 30);
            for (int col = 0; col < numCols; col++) {
                XSLFTableCell cell = table.getCell(row, col);
                XSLFTextParagraph paragraph = cell.addNewTextParagraph();

                XSLFTextRun textRun = paragraph.addNewTextRun();
                if (col == 0) {
                    textRun.setText("" + row);
                    table.setColumnWidth(col, 100);
                } else if (col == 1) {
                    textRun.setText(cols.get(row - 1));
                    table.setColumnWidth(col, 300);
                } else {
                    textRun.setText(cols.get(row - 1));
                    table.setColumnWidth(col, 120);
                    if (row == 1)
                        textRun.setText(numberOfApplications);
                    else if (row == 2)
                        textRun.setText(duration + " (Weeks)");
                    else
                        textRun.setText("€ " + totalCost);
                }
                textRun.setFontSize(18.0);
                textRun.setFontFamily("Calibri");

                if (row == 3) {
                    textRun.setBold(true);
                }


                cell.setBorderColor(TableCell.BorderEdge.top, Color.BLACK);
                if (numRows - 1 == row)
                    cell.setBorderColor(TableCell.BorderEdge.bottom, Color.BLACK);
                if (row % 2 == 0)
                    cell.setFillColor(Color.WHITE);
                else
                    cell.setFillColor(Color.LIGHT_GRAY);

                cell.getTextAutofit();

            }
        }

        // Add a heading row at the top of the table.
        XSLFTableRow headingRow = table.getRows().get(0);
        headingRow.setHeight(30);

        List<String> headings = new ArrayList<>();
        headingRow.setHeight(30);
        headings.add("S.No.");
        headings.add("Description");
        headings.add("Estimates");

        // Format the cells in the heading row.
        for (int col = 0; col < numCols; col++) {
            XSLFTableCell cell = headingRow.getCells().get(col);
            XSLFTextParagraph paragraph = cell.addNewTextParagraph();

            XSLFTextRun textRun = paragraph.addNewTextRun();
            textRun.setText(headings.get(col));
            textRun.setFontSize(18.0);
            textRun.setFontFamily("Calibri");
            textRun.setBold(true);
            textRun.setFontColor(Color.WHITE);

            cell.setBorderColor(TableCell.BorderEdge.top, Color.BLACK);
            cell.setBorderColor(TableCell.BorderEdge.bottom, Color.BLACK);
            cell.setFillColor(Color.BLACK);
        }

        logger.info("Table data added to the slide");
    }

    public static void getNote(XSLFSlide slide) {

        XSLFTextBox textBox = slide.createTextBox();
        textBox.setAnchor(new Rectangle2D.Double(30, 440, 900, 30)); // Position and size of the text box

        XSLFTextShape textShape = textBox.addNewTextParagraph().getParentShape();
        XSLFTextParagraph paragraph = textShape.addNewTextParagraph();
        XSLFTextRun textRun = paragraph.addNewTextRun();

        textRun.setText("** Please take note that the mentioned cost estimate does not encompass licensing fees for any of the tools utilized throughout the process, including code assessment tools like CAST\n");
        textRun.setFontSize(14.0);
        textRun.setFontFamily("Söhne");
    }


    public Document fileGenerate(TotalCost totalCost, ByteArrayOutputStream outputStream) throws IOException {
    	
    	logger.info("ppt file generated");
        return generatePDF(totalCost, outputStream, ".pptx");
    }

    public Document generatePDF(TotalCost totalCost, ByteArrayOutputStream outputStream, String fileType) throws IOException {
        Document pdfDocument = new Document();

        try {
            double zoom = 2;
            AffineTransform at = new AffineTransform();
            at.setToScale(zoom, zoom);
            PdfWriter pdfWriter = PdfWriter.getInstance(pdfDocument, outputStream);
            PdfPTable table = new PdfPTable(1);
            pdfWriter.open();
            pdfDocument.open();
            Dimension pgsize = null;
            Image slideImage = null;
            BufferedImage img = null;
            if (fileType.equalsIgnoreCase(".ppt")) {
                XMLSlideShow ppt = generateCostPPT(totalCost);
                pgsize = ppt.getPageSize();
                List<XSLFSlide> slide = ppt.getSlides();
                pdfDocument.setPageSize(new com.itextpdf.text.Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
                pdfWriter.open();
                pdfDocument.open();
                for (XSLFSlide xslShapes : slide) {
                    img = new BufferedImage((int) Math.ceil(pgsize.width * zoom), (int) Math.ceil(pgsize.height * zoom), BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = img.createGraphics();
                    graphics.setTransform(at);

                    graphics.setPaint(Color.white);
                    graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                    xslShapes.draw(graphics);
                    graphics.getPaint();
                    slideImage = Image.getInstance(img, null);
                    table.addCell(new PdfPCell(slideImage, true));
                }
            }
            if (fileType.equalsIgnoreCase(".pptx")) {
                XMLSlideShow ppt = generateCostPPT(totalCost);//new XMLSlideShow(inputStream);
                pgsize = ppt.getPageSize();
                XSLFSlide[] slide = ppt.getSlides().toArray(new XSLFSlide[0]);
                pdfDocument.setPageSize(new com.itextpdf.text.Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
                pdfWriter.open();
                pdfDocument.open();
                for (XSLFSlide xslShapes : slide) {
                    img = new BufferedImage((int) Math.ceil(pgsize.width * zoom), (int) Math.ceil(pgsize.height * zoom), BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = img.createGraphics();
                    graphics.setTransform(at);

                    graphics.setPaint(Color.white);
                    graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                    xslShapes.draw(graphics);
                    graphics.getPaint();
                    slideImage = Image.getInstance(img, null);
                    table.addCell(new PdfPCell(slideImage, true));
                }
            }
            pdfDocument.add(table);
            pdfDocument.close();
            pdfWriter.close();
        } catch (DocumentException e) {
            logger.warn(BasicConstants.ErrorMessages.DOCUMENT_ERROR);
        }catch (Exception e){
            logger.warn(BasicConstants.ErrorMessages.SOMETHING_WENT_WRONG);
        }
        logger.info("Pdf file generated");
        return pdfDocument;
    }
}

