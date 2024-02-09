package com.crudapp.crudapp;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
@Slf4j
public class Csvwithoutapi {
    private final String csvData ="Name,Age,Location\nAditya,21,Bhagalpur\nAshutosh,22,Bhagalpur";
    private final String csvFilePath="/Users/aditya2.gupta/Downloads/project/csv/file.csv";


    @PostConstruct
    public void createCsvFile(){
        try(FileWriter writer=new FileWriter(csvFilePath)) {
            writer.write(csvData);
            Thread.sleep(20000);
            log.info("Csv file created at {}",csvFilePath);
        } catch (IOException e) {
            log.error("Error creating Csv file",e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ;
    }
}
