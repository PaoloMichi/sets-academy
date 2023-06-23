package it.sets.common.web;

import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

import it.sets.common.model.EEntityWebType;

public class BackofficeTemplateResolver {
	
	public static String getListTemplate(EEntityWebType entityWebType) {
    	return new StringBuilder(entityWebType.getDesc()).append("/list").toString();
    }
    
	public static String getFormTemplate(EEntityWebType entityWebType) {
    	return new StringBuilder(entityWebType.getDesc()).append("/form").toString();
    }
    
	public static String redirectFormTemplate(EEntityWebType entityWebType, String id) {
    	return redirectFormTemplate(entityWebType, id, null);
    }
    
	public static String redirectFormTemplate(EEntityWebType entityWebType, String id, Map<String, String> queryParams) {
    	UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    	builder.path("admin").path("/")
    		.path("v1").path("/")
    		.path(entityWebType.getDesc()).path("/edit/")
    		.path(id);
    		
    	if (null != queryParams && !queryParams.isEmpty()) {
    		queryParams.forEach((k, v) -> builder.queryParam(k, v));
    	}
    	return new StringBuilder("redirect:/").append(builder.toUriString()).toString();
    }
    
	public static String redirectListTemplate(EEntityWebType entityWebType) {
    	return redirectListTemplate(entityWebType, null);
    }
    
	public static String redirectListTemplate(EEntityWebType entityWebType, Map<String, String> queryParams) {
    	UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    	builder.path("admin").path("/")
    		.path("v1").path("/")
    		.path(entityWebType.getDesc()).path("/")
    		.path("1");

    	if (null != queryParams && !queryParams.isEmpty()) {
    		queryParams.forEach((k, v) -> builder.queryParam(k, v));
    	}
    	return new StringBuilder("redirect:/").append(builder.toUriString()).toString();
    }

}
