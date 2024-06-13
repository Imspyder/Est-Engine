package com.estimationengine.service;

import com.estimationengine.dto.EstimateCostDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public interface ApplicationService {
    public Document getFinalData(EstimateCostDTO estimateCostDTO, ByteArrayOutputStream outputStream) throws DocumentException, IOException ;

    }
