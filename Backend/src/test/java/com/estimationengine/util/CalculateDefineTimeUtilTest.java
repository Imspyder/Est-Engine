package com.estimationengine.util;

import com.estimationengine.constants.BasicConstants;
import com.estimationengine.dto.AppEffortCountDTO;
import com.estimationengine.dto.AppSizeEffortDTO;
import com.estimationengine.dto.EstimateCostDTO;
import com.estimationengine.dto.TotalCost;
import com.estimationengine.entity.Application;
import com.estimationengine.entity.Resource;
import com.estimationengine.repo.EstimationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class CalculateDefineTimeUtilTest {


    @InjectMocks
    private CalculateDefineTimeUtil calculateDefineTimeUtil;

    @Mock
    private EstimationRepository estimationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public static List<AppEffortCountDTO> getList() {
        AppEffortCountDTO appEffortCount1 = new AppEffortCountDTO("S", 2, 3);
        AppEffortCountDTO appEffortCount2 = new AppEffortCountDTO("S", 9, 4);
        AppEffortCountDTO appEffortCount3 = new AppEffortCountDTO("S", 6, 5);
        AppEffortCountDTO appEffortCount4 = new AppEffortCountDTO("S", 8, 7);
        return List.of(appEffortCount1, appEffortCount2, appEffortCount3, appEffortCount4);

    }

    @Test
    void getCostPerScrum() {
        Application application1 = new Application();
        Resource resource1 = new Resource(1, "DBAs", "G6", 1, 85, 1);
        Resource resource2 = new Resource(2, "Front End - Java Developers", "G6", 2, 85, 1);
        Resource resource3 = new Resource(3, "Backend Developers", "G6", 2, 85, 1);
        Resource resource4 = new Resource(4, "Application Architect", "G6", 1, 85, 1);
        Resource resource5 = new Resource(5, "Cloud Architect", "G6", 1, 85, 1);
        Resource resource6 = new Resource(6, "DevOps Engineer", "G6", 1, 85, 1);
        Resource resource7 = new Resource(7, "Scrum Master", "G6", 1, 85, 1);
        Resource resource8 = new Resource(8, "Application Tester", "G6", 1, 85, 1);
        Resource resource9 = new Resource(9, "Cloud Security", "G6", 1, 85, 1);
        application1.setResourceList(Arrays.asList(resource1, resource2, resource3, resource4, resource5, resource6, resource7, resource8, resource9));

        List<Application> applications = List.of(application1);

        when(estimationRepository.findAll()).thenReturn(applications);

        double result = calculateDefineTimeUtil.getCostPerScrum();
        double expected = ((2) + (1) + (2) + (1) + (1) + (1) + (1) + (1) + (1)) * BasicConstants.CalculationConstants.WORKING_DAYS_IN_WEEK * BasicConstants.CalculationConstants.WORKING_HOURS * BasicConstants.CalculationConstants.NO_OF_WEEKS_IN_A_SCRUM;
        assertEquals(expected, result, 0.001);
    }

    @Test
    void getApplicationModernizationTime() {
        List<AppEffortCountDTO> appEffortCountDTOS = getList();
        int result = calculateDefineTimeUtil.getApplicationModernizationTime(appEffortCountDTOS);
        int expected = (2 * 3) + (9 * 4) + (6 * 5) + (8 * 7);
        assertEquals(expected, result);
    }

    @Test
    void getDefinedDurationInWeeks() {

        int a = 4;
        assertEquals(a * 52, calculateDefineTimeUtil.getDefinedDurationInWeeks(a));
    }

    @Test
    void getNumberOfParallelScrum() {
        List<AppEffortCountDTO> appEffortCountDTOS = getList();

        int a = 4;
        int expected = Math.round((((float) calculateDefineTimeUtil.getApplicationModernizationTime(appEffortCountDTOS) / calculateDefineTimeUtil.getDefinedDurationInWeeks(a))) + 1);
        int result = calculateDefineTimeUtil.getNumberOfParallelScrum(a, appEffortCountDTOS);
        assertEquals(expected, result);

    }

    @Test
    void getNumberOfWeeksRequired() {
        List<AppEffortCountDTO> appEffortCountDTOS = getList();

        int a = 4;
        int expected = Math.round((float) calculateDefineTimeUtil.getApplicationModernizationTime(appEffortCountDTOS) / Math.round((((float) calculateDefineTimeUtil.getApplicationModernizationTime(appEffortCountDTOS) / calculateDefineTimeUtil.getDefinedDurationInWeeks(a))) + 1));
        int result = calculateDefineTimeUtil.getNumberOfWeeksRequired(a, appEffortCountDTOS);
        assertEquals(expected, result);
    }

    @Test
    void getTotalCost() {
        List<AppEffortCountDTO> appEffortCountDTOS = getList();
        int a = 4;
        int parallelScrum = calculateDefineTimeUtil.getNumberOfParallelScrum(a, appEffortCountDTOS);
        double costPerScrum = calculateDefineTimeUtil.getCostPerScrum();
        int numberOfWeeks = calculateDefineTimeUtil.getNumberOfWeeksRequired(a, appEffortCountDTOS);

        double result = costPerScrum * parallelScrum * ((float) numberOfWeeks / 2);
        double expected = calculateDefineTimeUtil.getTotalCost(4, appEffortCountDTOS);
        assertEquals(expected, result);
    }

    @Test
    void getCostEstimationDefinedTime() throws JsonProcessingException {
        List<AppEffortCountDTO> appEffortCountDTOS = getList();


        List<AppSizeEffortDTO> appSizeEffortDTOS=List.of(new AppSizeEffortDTO("DB Migration",appEffortCountDTOS,0,2));

        EstimateCostDTO estimateCostDTO = new EstimateCostDTO("sachin",appSizeEffortDTOS,1);
        estimateCostDTO.getScopes().get(0).setAppEffortCounts(appEffortCountDTOS);
        estimateCostDTO.setCustomerName("sachin");
        estimateCostDTO.setDefinedDurationInYears(5);
        estimateCostDTO.getScopes().get(0).setScope("DB Migration");

        TotalCost Result = calculateDefineTimeUtil.getCostEstimationDefinedTime(estimateCostDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String json="{\n" +
                "    \"customerName\": \"sachin\",\n" +
                "    \"definedDurationInYears\": 5,\n" +
                "    \"scopes\": [\n" +
                "        {\n" +
                "            \"scope\": \"DB Migration\",\n" +
                "            \"appEffortCounts\": [\n" +
                "                {\n" +
                "                    \"appCount\": 3,\n" +
                "                    \"appSizeEffort\": \"2\",\n" +
                "                    \"appSize\": \"S\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"appCount\": 4,\n" +
                "                    \"appSizeEffort\": \"9\",\n" +
                "                    \"appSize\": \"S\"\n" +
                "                }, {\n" +
                "                    \"appCount\": 5,\n" +
                "                    \"appSizeEffort\": \"6\",\n" +
                "                    \"appSize\": \"S\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"appCount\": 7,\n" +
                "                    \"appSizeEffort\": \"8\",\n" +
                "                    \"appSize\": \"S\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        EstimateCostDTO estimateCostDTO1 = objectMapper.readValue(json, EstimateCostDTO.class);


        TotalCost expected = calculateDefineTimeUtil.getCostEstimationDefinedTime(estimateCostDTO1);

        assertNotNull(expected);

    }
}