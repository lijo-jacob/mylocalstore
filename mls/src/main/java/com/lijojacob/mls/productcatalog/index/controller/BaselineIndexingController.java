package com.lijojacob.mls.productcatalog.index.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lijojacob.mls.productcatalog.index.BaselineIndexingService;

@Controller
public class BaselineIndexingController {
	
	private static Logger LOGGER = Logger.getLogger(BaselineIndexingController.class);
	
	@Autowired
	private BaselineIndexingService baselineIndexingService;
	
	@RequestMapping(value = "/baselineIndex", method = RequestMethod.POST)
	public @ResponseBody void baselineIndex() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {
		    public void run() {
		    	LOGGER.debug("Triggering baseline index");
		        baselineIndexingService.doBaselineIndex();
		    }
		});
		executorService.shutdown();
	}

}
