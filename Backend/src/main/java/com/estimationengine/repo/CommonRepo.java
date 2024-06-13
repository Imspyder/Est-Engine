package com.estimationengine.repo;

import com.estimationengine.dto.AppEffortCountDTO;
import com.estimationengine.dto.AppSizeEffortDTO;
import com.estimationengine.dto.CustomerScopesDTO;
import com.estimationengine.entity.AppSize;
import com.estimationengine.entity.AppSizeScopeTBL;
import com.estimationengine.entity.Customer;
import com.estimationengine.entity.Scope;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;

public interface CommonRepo {
    public List<AppSize> getAllSize();

    public Map<String , Object> getAppEffortByAppSizeAndScope(@Param("size") String size, @Param("scope") String scope);
    public boolean saveCustomerScopeDetails(CustomerScopesDTO customerScopes);
    public Scope getScopeByName(String scope);

    }
