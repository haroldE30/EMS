package com.adeptsource.hrms.common.http;

import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import com.adeptsource.hrms.common.bind.DTO;

public class ResponseDTO<T> extends HttpEntity<T> {

	private static final ModelMapper modelMapper = new ModelMapper();

	ResponseDTO(T body, HttpStatus status) {
		super(body);
	}
	
	public static Builder accepted() {
		return status(HttpStatus.ACCEPTED);
	}
	
	public static Builder ok() {
		return status(HttpStatus.OK);
	}
	
	public static Builder badRequest() {
		return status(HttpStatus.BAD_REQUEST);
	}
	
	public static Builder status(HttpStatus status) {
		return new BodyBuilder(status);
	}
	
	public static Builder create() {
		return new BodyBuilder();
	}

	public interface Builder {
		<T> ResponseDTO<T> convertTo(Object entity, Class<T> aClass);
	}
	
	private static class BodyBuilder implements Builder {
		
		private HttpStatus status;
		
		public BodyBuilder() {
		}
		
		public BodyBuilder(HttpStatus status) {
			this.status = status;
		}

		@Override
		public <T> ResponseDTO<T> convertTo(Object entity, Class<T> aClass) {
			Assert.notNull(AnnotationUtils.getAnnotation(aClass, DTO.class),
                    "Type should contain DTO annotation");
			
			return new ResponseDTO<>(modelMapper.map(entity, aClass), status);
		}
		
	}
}
