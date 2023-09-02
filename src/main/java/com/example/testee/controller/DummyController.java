package com.example.testee.controller;


import com.example.testee.MockUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DummyController {

    private static final int MB= 1024*1024;
    /**
     * Used to simulate a memory leak
     */
    private static final List<String> MEMORY_LEAK= new ArrayList<>();


    /**
     * A dummy API to <br />
     *  - Produce dummy CPU usage
     *  - Produce very obvious memory usage
     *  - Produce hidden memory leak
     * @throws InterruptedException
     */
    @GetMapping("/dummy")
    public void doDummy() throws InterruptedException {

        //Produce CPU loading
        MockUtils.busy(100, 500);

        //Produce obvious memory usage
        RandomStringUtils.randomAlphabetic(20*MB);

        //Produce hidden memory leak
        MEMORY_LEAK.add(RandomStringUtils.randomAlphabetic(1*MB));

    }

}
