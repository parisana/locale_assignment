package com.locale.demo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locale.demo.Utils.CSVConvertor;
import com.locale.demo.domain.BookingDTO;
import com.locale.demo.domain.FailedBooking;
import com.locale.demo.domain.dto.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * @author Parisana
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        final CSVConvertor csvConvertor = new CSVConvertor(new ObjectMapper());

        final long start = System.currentTimeMillis();
        final Result<List<BookingDTO>, List<FailedBooking>> listListResult = csvConvertor.toOptionalObjects(new FileReader(new File("/home/pari/Project/localeAi/demo/src/main/resources/data.csv")), BookingDTO.class, FailedBooking.class);

        System.out.println("time taken: "+ ((System.currentTimeMillis()-start)));
        assert listListResult != null;
//        listListResult.getObj().forEach(System.out::println);
        listListResult.getExceptionObj().forEach(System.out::println);
    }

}
