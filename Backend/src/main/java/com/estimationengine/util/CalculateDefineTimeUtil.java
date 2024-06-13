package com.estimationengine.util;

import com.estimationengine.constants.BasicConstants;
import com.estimationengine.dto.AppEffortCountDTO;
import com.estimationengine.dto.AppSizeEffortDTO;
import com.estimationengine.dto.EstimateCostDTO;
import com.estimationengine.dto.TotalCost;
import com.estimationengine.entity.Application;
import com.estimationengine.repo.EstimationRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CalculateDefineTimeUtil {
	
	private static final Logger logger = LogManager.getLogger(CalculateDefineTimeUtil.class);

    @Autowired
    private EstimationRepository estimationRepository;

    public double getCostPerScrum() {
        List<Application> applications = estimationRepository.findAll();
        return applications.stream()
                .flatMap(f -> f.getResourceList().stream())
                .map(m -> m.getCount() * m.getRatePerHour() * BasicConstants.CalculationConstants.WORKING_DAYS_IN_WEEK * BasicConstants.CalculationConstants.WORKING_HOURS * BasicConstants.CalculationConstants.NO_OF_WEEKS_IN_A_SCRUM)
                .reduce((double) 0, Double::sum);
    }

    public int getApplicationModernizationTime(List<AppEffortCountDTO> appEffortCounts) {
        return appEffortCounts.stream().map(d -> d.getAppSizeEffort() * d.getAppCount()).reduce(0, Integer::sum);
    }

    public int getDefinedDurationInWeeks(int definedDurationInYears) {
        return definedDurationInYears * BasicConstants.CalculationConstants.WEEKS_IN_A_YEAR;
    }

    public int getNumberOfParallelScrum(int definedDurationInYears, List<AppEffortCountDTO> appEffortCounts) {

        int applicationModernizationTime = getApplicationModernizationTime(appEffortCounts);
        int numberOfParallelScrum = getDefinedDurationInWeeks(definedDurationInYears);

        return Math.round(((float) applicationModernizationTime / numberOfParallelScrum)) + 1;
    }

    public int getNumberOfWeeksRequired(int definedDurationInYears, List<AppEffortCountDTO> appEffortCounts) {

        int applicationModernizationTime = getApplicationModernizationTime(appEffortCounts);
        int numberOfParallelScrum = getNumberOfParallelScrum(definedDurationInYears, appEffortCounts);

        return Math.round((float) applicationModernizationTime / numberOfParallelScrum);
    }

    public double getTotalCost(int definedDurationInYears, List<AppEffortCountDTO> appEffortCounts) {

        double costPerScrum = getCostPerScrum();
        int numberOfParallelScrum = getNumberOfParallelScrum(definedDurationInYears, appEffortCounts);
        int numberOfWeeksRequired = getNumberOfWeeksRequired(definedDurationInYears, appEffortCounts);

        logger.info("Total cost calculated successfully");
        return costPerScrum * numberOfParallelScrum * ((float) numberOfWeeksRequired / 2);
    }

    public TotalCost getCostEstimationDefinedTime(EstimateCostDTO estimateCostDTO) {
        TotalCost totalCost = new TotalCost();

        double cost=0;
        int totalNumberOfApps=0;
        for (AppSizeEffortDTO appSizeEffortDTO : estimateCostDTO.getScopes()) {
            appSizeEffortDTO.setDuration(estimateCostDTO.getDefinedDurationInYears());
            appSizeEffortDTO.setNumberOfApplications(appSizeEffortDTO.getAppEffortCounts().stream().map(AppEffortCountDTO::getAppCount).reduce(0,Integer::sum));
            cost +=getTotalCost(estimateCostDTO.getDefinedDurationInYears(), appSizeEffortDTO.getAppEffortCounts());
            totalNumberOfApps+=appSizeEffortDTO.getAppEffortCounts().stream().map(AppEffortCountDTO::getAppCount).reduce(0,Integer::sum);

        }
        totalCost.setCost(cost);
        totalCost.setTotalNumberOfApplicatinos(totalNumberOfApps);
        totalCost.setCustomerName(estimateCostDTO.getCustomerName());
        totalCost.setScopes(estimateCostDTO.getScopes());
        totalCost.setDuration(estimateCostDTO.getDefinedDurationInYears()*52);

        logger.info("Generated total cost: {}", totalCost);
        return totalCost;
    }


}
