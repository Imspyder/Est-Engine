package com.estimationengine.controller;

import com.estimationengine.entity.AppSize;
import com.estimationengine.repo.CommonRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApplicationControllerTest {
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    protected MockMvc mockMvc;
    @Mock
    private CommonRepo commonRepo;

    @InjectMocks
    private ApplicationController applicationController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(applicationController)
                .setCustomArgumentResolvers(pageableArgumentResolver)
//                .setControllerAdvice(exceptionTranslator)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Test
    void getAppEffort() throws Exception {
        Map<String , Object> map=new HashMap<>();
//        map.put("appSize","S");
//        map.put("appEffort",7);
//        map.put("scope","DevOps");

        Mockito.when(commonRepo.getAppEffortByAppSizeAndScope("XS","DevOps")).thenReturn(map);
        mockMvc.perform(MockMvcRequestBuilders.get("/application/getAllSize")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    void getAllSize() {
        try{
            List<AppSize> listData = new ArrayList<>();
//            listData.add(getApp());
            Mockito.when(commonRepo.getAllSize()).thenReturn(listData);
            mockMvc.perform(MockMvcRequestBuilders.get("/application/getAllSize")
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    AppSize getApp (){
        AppSize app = new AppSize(1,"s");
        return app;
    }
}