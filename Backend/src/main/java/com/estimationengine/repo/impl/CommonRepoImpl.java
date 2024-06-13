package com.estimationengine.repo.impl;


import com.estimationengine.constants.BasicConstants;
import com.estimationengine.dto.AppEffortCountDTO;
import com.estimationengine.dto.AppSizeEffortDTO;
import com.estimationengine.dto.CustomerScopesDTO;
import com.estimationengine.entity.AppSize;
import com.estimationengine.entity.AppSizeScopeTBL;
import com.estimationengine.entity.Customer;
import com.estimationengine.entity.Scope;
import com.estimationengine.repo.CommonRepo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class CommonRepoImpl implements CommonRepo {

   public static final Logger logger = LogManager.getLogger(String.valueOf(CommonRepoImpl.class));
    @PersistenceContext
    private EntityManager entityManager;

    //Getting all application size
    @Override
    public List<AppSize> getAllSize() {
        TypedQuery<AppSize> query = entityManager.createQuery("SELECT e FROM AppSize e", AppSize.class);
        return query.getResultList();
    }

    //Getting application effort by scope and app size
    @Override
    public Map<String , Object> getAppEffortByAppSizeAndScope(@Param("size") String size, @Param("scope") String scope) {
        AppSizeScopeTBL appSizeScopeTBL=new AppSizeScopeTBL();
        Map<String , Object> map=new HashMap<>();
        TypedQuery<AppSizeScopeTBL> query = entityManager.createQuery(BasicConstants.SQLQueries.GET_APP_EFFORT_BY_SIZE_AND_SCOPE, AppSizeScopeTBL.class);
        query.setParameter("scope", scope);
        query.setParameter("size", size);
        appSizeScopeTBL = query.getSingleResult();

        map.put("appSize",appSizeScopeTBL.getAppSize().getAppSize());
        map.put("appEffort",appSizeScopeTBL.getAppSizeEffort());
        map.put("scope",appSizeScopeTBL.getScope().getScope());
        logger.warn("No data found for size: " + size + " and scope: " + scope);
        return map;
    }

    //Mapping details of customer and scopes
    @Override
    @Transactional
    public boolean saveCustomerScopeDetails(CustomerScopesDTO customerScopes) {
        if (customerScopes.getCustomerName()!=null && !customerScopes.getCustomerName().isEmpty() ) {
            Customer customer = new Customer();
            customer.setCustomerName(customerScopes.getCustomerName());
            //Saving the customer data before mapping with the scopes
            entityManager.persist(customer);

            for (Integer scope : customerScopes.getScopes()) {
                Query query = entityManager.createNativeQuery(BasicConstants.SQLQueries.SAVE_CUSTOMER_SCOPE);
                query.setParameter(1, customer.getCustomerId());
                query.setParameter(2, scope);
                query.executeUpdate();
            }
            entityManager.close();
            return true;
        }else {
            logger.warn("Invalid data. Customer name is null or empty.");
            return false;
        }
    }

    public Scope getScopeByName(String scope){
        TypedQuery<Scope> query = entityManager.createQuery("SELECT s FROM Scope s where s.scope=:scope ", Scope.class);
        query.setParameter("scope", scope);

        return query.getSingleResult();
    }
}
