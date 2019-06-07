package com.locale.demo.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Parisana
 */

@Getter
@RequiredArgsConstructor
public final class Result<T, E> {

    private final T obj;
    private final E exceptionObj;

    private Result(){
        this.obj = null;
        this.exceptionObj =null;
    }

}
