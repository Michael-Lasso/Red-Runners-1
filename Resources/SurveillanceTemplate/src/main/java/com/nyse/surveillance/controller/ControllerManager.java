package com.nyse.surveillance.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lokiz.service.SurveillanceProcessor;

public class ControllerManager {

	private static final Log log = LogFactory.getLog(ControllerManager.class);

	public static void main(String[] args) {

		String date = args[0];
		log.info("<--------------------Starting ControllerManager: " + date + "-------------------->\n");
		SurveillanceProcessor surveillance;
		try {
			surveillance = new Surveillance(date);
			surveillance.process(null);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exiting program");
			System.exit(-1);
		}
	}

}
