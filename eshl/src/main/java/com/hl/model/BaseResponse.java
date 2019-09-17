package com.hl.model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class BaseResponse {

    public static  <E> ResponseEntity<GenericResponse<E>> ltResponse(E obj, HttpStatus httpStatus) {
        GenericResponse<E> restResponseDTO = new GenericResponse<E>(obj, httpStatus.value(), null);
        return new ResponseEntity<GenericResponse<E>>(restResponseDTO, httpStatus);
    }

    public static <Any> ResponseEntity<GenericResponse<Any>> ltResponse(Any obj) {
        return ltResponse(obj, HttpStatus.OK);
    }
}
