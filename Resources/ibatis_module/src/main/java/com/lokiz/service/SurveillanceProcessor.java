package com.lokiz.service;

public interface SurveillanceProcessor {
	public <T extends SurveillanceParameter> void process(Class<T> t) throws Exception;
}
