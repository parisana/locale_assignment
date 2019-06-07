package com.locale.demo.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locale.demo.domain.dto.Result;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Parisana
 */
@Component
public final class CSVConvertor {
    private final ObjectMapper objectMapper;

    @Autowired
    public CSVConvertor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T, E> Result<List<T>, List<E>> toOptionalObjects(Reader reader, Class<? extends T> clazzType, Class<? extends E> failClazzType){
//        List<T> resultList = new CopyOnWriteArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT.withHeader();

        try (BufferedReader inputReader = new BufferedReader(reader);
             CSVParser dataCSVParser = new CSVParser(inputReader, format) ) {

            final List<CSVRecord> csvRecords = dataCSVParser.getRecords();

            final List<E> exceptionRows = new LinkedList<>();

            Map<String, Integer> headerMap = dataCSVParser.getHeaderMap();
            final List<T> resultList = csvRecords.stream().map(csvRecord -> {
                Map<String, String> inputMap = new LinkedHashMap<>();

                for (Map.Entry<String, Integer> header : headerMap.entrySet()) {
                    final String currentVal = csvRecord.get(header.getValue());
//                    inputMap.put(header.getKey(), currentVal.equalsIgnoreCase("null")?null:currentVal);
                    if (!currentVal.equalsIgnoreCase("null"))
                        inputMap.put(header.getKey(), currentVal);
                }

                try {
                    return objectMapper.convertValue(inputMap, clazzType);
                } catch (IllegalArgumentException e) {
                    exceptionRows.add(objectMapper.convertValue(inputMap, failClazzType));
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            return new Result<>(resultList, exceptionRows);
//            for(CSVRecord record : csvRecords){
//                Map<String, String> inputMap = new LinkedHashMap<>();
//
//                for(Map.Entry<String, Integer> header : headerMap.entrySet()){
//                    final String currentVal = record.get(header.getValue());
////                    inputMap.put(header.getKey(), currentVal.equalsIgnoreCase("null")?null:currentVal);
//                    if (!currentVal.equalsIgnoreCase("null"))
//                        inputMap.put(header.getKey(), currentVal);
//                }
//
//                final T o = objectMapper.convertValue(inputMap, clazzType);
//                resultList.add(o);
//            }
//            return Optional.of(resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
