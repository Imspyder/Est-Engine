package com.estimationengine.controller;

import com.estimationengine.entity.Application;
import com.estimationengine.repo.EstimationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/v1/")
public class DemoController {

    @Autowired
    private EstimationRepository estimationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void getAllSize() {
        List<Application> applications = new ArrayList<>();
        for (int i = 3; i < 100; i++) {

            applications.add(new Application(new Random().nextInt(1000)+i, "Sample" + new Random().nextInt(1000), new Random().nextInt(1000), "i" + new Random().nextInt(1000), new Random().nextInt(1000)));
        }

        for (Application a : applications
        ) {

            estimationRepository.save(a);
        }
    }

    @GetMapping("/{field}")
    public List<Application> getListOfApplication(@PathVariable("field") String field) {


        return estimationRepository.findAll(Sort.by(Sort.Direction.ASC, field));

    }

    @GetMapping("/pagination/{offset}/{pagesize}")
    public Page<Application> getListOfApplication(@PathVariable("offset") int offset,@PathVariable("pagesize") int pageSize) {
        return estimationRepository.findAll(PageRequest.of(offset,pageSize));

    }
}
