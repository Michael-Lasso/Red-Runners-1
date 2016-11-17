package com.nyse.surveillance.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lokiz.dao.ObejctDAO;
import com.lokiz.service.SurveillanceParameter;
import com.lokiz.service.SurveillanceProcessor;
import com.nyse.surveillance.domain.Criteria;
import com.nyse.surveillance.util.ProgramConstants;

public class Surveillance implements SurveillanceProcessor {

	private static final Log log = LogFactory.getLog(Surveillance.class);
	private Criteria criteria;
	private final ObejctDAO objDAO;

	/**
	 * Constructor instantiates object with date in yyyyMMdd format
	 * 
	 * @param tdateStr
	 * @throws Exception
	 */
	public Surveillance(final String tdateStr) throws Exception {
		@SuppressWarnings("resource")
		final ApplicationContext ctx = new ClassPathXmlApplicationContext(ProgramConstants.APPLICATION_CONTEXT);
		criteria = Criteria.setDateCriteria(tdateStr);
		objDAO = (ObejctDAO) ctx.getBean(ProgramConstants.TMP_MCIS_DAO);
	}

	/**
	 * Execute queries
	 * 
	 * @throws Exception
	 * 
	 */
	private void executeQueries() throws Exception {
		log.info("Executing Queries");
		objDAO.getList(ProgramConstants.SELECT_1, criteria);
		objDAO.updateQuery(ProgramConstants.SELECT_2, null);
		List<Object> list = objDAO.getList(ProgramConstants.GET_LIST, criteria);
		log.info(list.size());
	}

	/**
	 * Drop all temp tables created by the program
	 * 
	 * @throws DropTableException
	 */
	private void cleanDB() {
		log.info("<------------- Cleaning up DB tables ------------->");
		for (int i = 1; i <= ProgramConstants.TEMP_TABLES; i++) {
			try {
				objDAO.dropTempTable(ProgramConstants.DROP_TEMP_TABLE + i, null);
			} catch (Exception e) {
				log.info("<--------------------- TEMP TABLE " + i + " was not removed --------------------->");
			}
		}
	}

	@Override
	public <T extends SurveillanceParameter> void process(Class<T> t) throws Exception {
		cleanDB();
		executeQueries();// program execute functions here
		cleanDB();
	}
}
