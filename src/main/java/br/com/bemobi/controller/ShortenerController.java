package br.com.bemobi.controller;

import br.com.bemobi.model.Statistics;
import br.com.bemobi.model.URL;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by diego.magalhaes on 5/19/2016.
 * Controller that invoke the actions needed for shortening, retrieving and display the top 10 URL most visited
 */
@RestController
@RequestMapping(ShortenerController.APP_ENTRY_POINT)
public class ShortenerController {
    static final String APP_ENTRY_POINT = "/u";

    @RequestMapping(value = "/{alias}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Callable<URL> getByAlias(String alias){
        return () -> URL
                .builder()
                .id(1L)
                .alias(alias)
                .description("sample description")
                .statistics(Statistics.builder().timeTaken(200).unit(TimeUnit.MILLISECONDS).build())
                .url("www.uol.com.br")
                .build();
    }

}
