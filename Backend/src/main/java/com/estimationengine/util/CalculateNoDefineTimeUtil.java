package com.estimationengine.util;


import com.estimationengine.constants.BasicConstants;
import com.estimationengine.dto.AppEffortCountDTO;
import com.estimationengine.dto.AppSizeEffortDTO;
import com.estimationengine.dto.EstimateCostDTO;
import com.estimationengine.dto.TotalCost;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculateNoDefineTimeUtil {
	
	private static final Logger logger = LogManager.getLogger(CalculateNoDefineTimeUtil.class);

    @Autowired
    CalculateDefineTimeUtil calculateDefineTimeUtil;

    public double getCostPerScrum() {
    	logger.info("Cost per scrum retrieved successfully");
        return calculateDefineTimeUtil.getCostPerScrum();
    }

    public int getApplicationModernizationTime(List<AppEffortCountDTO> appEffortCounts) {
    	logger.info("Application modernization time calculated successfully");
        return appEffortCounts.stream().map(d -> d.getAppSizeEffort() * d.getAppCount()).reduce((int) 0, Integer::sum);
    }

    public int getNumberOfScrum(List<AppEffortCountDTO> appEffortCounts) {

        int applicationModernizationTime = getApplicationModernizationTime(appEffortCounts);
        logger.info("Number of scrum calculated successfully");
        return Math.round((float) applicationModernizationTime / BasicConstants.CalculationConstants.NO_OF_WEEKS_IN_A_SCRUM);
    }

    public double getTotalCost(List<AppEffortCountDTO> appEffortCounts) {

        double costPerScrum = getCostPerScrum();
        int numberOfScrum = getNumberOfScrum(appEffortCounts);
        logger.info("Total cost calculated successfully");
        return costPerScrum * numberOfScrum;

    }

    public TotalCost getCostEstimationNoDefinedTime(EstimateCostDTO estimateCostDTO) {
        TotalCost totalCost = new TotalCost();
        double cost = 0;
        int totalNumberOfApps = 0;
        int duration = 0;

        for (AppSizeEffortDTO appSizeEffortDTO : estimateCostDTO.getScopes()) {
            duration += getApplicationModernizationTime(appSizeEffortDTO.getAppEffortCounts());
            appSizeEffortDTO.setNumberOfApplications(appSizeEffortDTO.getAppEffortCounts().stream().map(AppEffortCountDTO::getAppCount).reduce(0, Integer::sum));
            cost += getTotalCost(appSizeEffortDTO.getAppEffortCounts());
            totalNumberOfApps += appSizeEffortDTO.getAppEffortCounts().stream().map(AppEffortCountDTO::getAppCount).reduce(0, Integer::sum);

        }
        totalCost.setCost(cost);
        totalCost.setTotalNumberOfApplicatinos(totalNumberOfApps);
        totalCost.setCustomerName(estimateCostDTO.getCustomerName());
        totalCost.setScopes(estimateCostDTO.getScopes());
        totalCost.setDuration(duration);
        logger.info("Generated total cost: {}", totalCost);
        return totalCost;
    }


}
