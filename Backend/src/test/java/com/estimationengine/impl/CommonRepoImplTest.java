package com.estimationengine.impl;

import com.estimationengine.entity.AppSize;
import com.estimationengine.repo.impl.CommonRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

class CommonRepoImplTest {
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Mock
    private EntityManager entityManager;
    protected MockMvc mockMvc;

    @Mock
    private TypedQuery<AppSize> query;

    @InjectMocks
    private CommonRepoImpl commonRepo;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(commonRepo)
                .setCustomArgumentResolvers(pageableArgumentResolver)
//                .setControllerAdvice(exceptionTranslator)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Test
    void getAllSize() {
        List<AppSize> listData = new ArrayList<>();
        listData.add(getApp());
        Mockito.when(commonRepo.getAllSize()).thenReturn(listData);

//        mockMvc.perform(MockMvcRequestBuildersuestBuilders.get("/application/getAllSize")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
    }

    @Test
    void getAppEffortByAppSizeAndScope() {
    }

    @Test
    void saveCustomerScopeDetails() {
    }

    AppSize getApp (){
        AppSize app = new AppSize();
        return app;
    }
}