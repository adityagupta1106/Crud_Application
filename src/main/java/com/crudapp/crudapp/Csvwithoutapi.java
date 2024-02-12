//package com.crudapp.crudapp;
//
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.logging.Logger;
//
//@Component
////@Slf4j
//public class Csvwithoutapi {
//
//    private static final Logger log= Logger.getLogger(Csvwithoutapi.class.getName());
//    private final String csvData ="Name,Age,Location\nAditya,21,Bhagalpur\nAshutosh,22,Bhagalpur";
//    private final String csvFilePath="/Users/aditya2.gupta/Downloads/project/csv/file.csv";
//
//
//    @PostConstruct
//    public void createCsvFile(){
//        try(FileWriter writer=new FileWriter(csvFilePath)) {
//            writer.write(csvData);
//            Thread.sleep(20000);
//            log.info("Csv file created at {}");
//        } catch (IOException e) {
//            log.warning("Error creating Csv file");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        ;
//    }
//}
