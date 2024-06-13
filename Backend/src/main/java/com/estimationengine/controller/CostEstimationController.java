package com.estimationengine.controller;

import com.estimationengine.dto.EstimateCostDTO;
import com.estimationengine.entity.Customer;
import com.estimationengine.repo.CommonRepo;
import com.estimationengine.service.ApplicationService;
import com.estimationengine.util.CalculateDefineTimeUtil;
import com.estimationengine.util.PDFUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("/cost")
public class CostEstimationController {

	private static final Logger logger = LogManager.getLogger(CostEstimationController.class);
    @Autowired
    private CalculateDefineTimeUtil calculateDefineTimeUtil;

    @Autowired
    PDFUtil pdfUtil;
    @Autowired
    private ApplicationService applicationService;

    Map<String, Object> responseMap = new HashMap<>();

    @Autowired
    private CommonRepo commonRepo;


    @PostMapping(value = "/calculateFinalCost", produces = MediaType.APPLICATION_PDF_VALUE)
    protected ResponseEntity<byte[]> download(@RequestBody EstimateCostDTO estimateCostDTO, HttpServletResponse response)
            throws DocumentException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            applicationService.getFinalData(estimateCostDTO, outputStream);

            org.springframework.http.HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "example.pdf");
            logger.info("PDF generated successfully for estimateCostDTO: {}", estimateCostDTO);
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        }
        catch (IOException e) {
        	logger.error("Error while generating PDF", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}




