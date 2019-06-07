package com.locale.demo.controller;

import com.locale.demo.domain.dto.FailedBookingDto;
import com.locale.demo.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Parisana
 */
@RestController
@RequestMapping("query")
public class QueryController {

    private final StorageService storageService;

    public QueryController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("search")
    public List<FailedBookingDto> getFailedBookingsDtos(@RequestParam(value = "id", required = false, defaultValue = "1") Long customerId){
        return storageService.findAllFailedBookingDTO(customerId).stream().map(FailedBookingDto::new).collect(Collectors.toList());
    }

}
