package com.estimationengine.service.impl;

import com.estimationengine.dto.EstimateCostDTO;
import com.estimationengine.dto.TotalCost;
import com.estimationengine.service.ApplicationService;
import com.estimationengine.util.CalculateDefineTimeUtil;
import com.estimationengine.util.CalculateNoDefineTimeUtil;
import com.estimationengine.util.PDFUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	private static final Logger logger = LogManager.getLogger(ApplicationServiceImpl.class);

    @Autowired
    private CalculateDefineTimeUtil calculateDefineTimeUtil;

    @Autowired
    private CalculateNoDefineTimeUtil calculateNoDefineTimeUtil;
    @Autowired
    private PDFUtil pdfUtil;

    @Override
    public Document getFinalData(EstimateCostDTO estimateCostDTO, ByteArrayOutputStream outputStream) throws DocumentException, IOException {




        TotalCost totalCost = null;
        if (estimateCostDTO.getDefinedDurationInYears() != 0) {
            totalCost = calculateDefineTimeUtil.getCostEstimationDefinedTime(estimateCostDTO);
        } else {
            totalCost = calculateNoDefineTimeUtil.getCostEstimationNoDefinedTime(estimateCostDTO);
        }
        logger.info("Generated total cost: {}", totalCost);
        return pdfUtil.fileGenerate(totalCost,outputStream);
//        return totalCost;
    }
}