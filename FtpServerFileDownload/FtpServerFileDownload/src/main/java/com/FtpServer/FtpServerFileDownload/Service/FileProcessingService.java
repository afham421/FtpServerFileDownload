package com.FtpServer.FtpServerFileDownload.Service;

import com.FtpServer.FtpServerFileDownload.Dao.BlacklistNumberRepo;
import com.FtpServer.FtpServerFileDownload.Entity.BlacklistNumber;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class FileProcessingService {

    @Autowired
    public BlacklistNumberRepo blacklistNumberRepo; // Autowire your repository here


    @Transactional
//    @Scheduled(cron = "0 0 * * * *") // Schedule to run every hour
    @Scheduled(cron = "0/10 * * * * *") // Schedule to run every 10 seconds
    public void scheduledFileProcessing() {
        processFile(); // Call the existing file processing logic
    }

    @Transactional
    public void processFile() {
        System.out.println("\nFILE READING STARTED AT " + new SimpleDateFormat("yyyy-MM-dd HH:mm.ss").format(new Date()));

        String downloadFilePath = "D:\\NON_PROMO_MSISDN_EXPORT_2023-08-23.txt"; // Replace with your actual file path
        File file = new File(downloadFilePath);

        List<BlacklistNumber>  blacklistNumbers = null;
        if (file.exists()) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String line;
                String invalidLine = "";
                HashMap<String, String> msisdnList = new HashMap<>();
                Pattern pattern = Pattern.compile("[0-9]+");

                while ((line = fileReader.readLine()) != null) {
                    if (line.startsWith("923") && line.length() == 12 && pattern.matcher(line).matches()) {
                        msisdnList.put(line, line);
                    } else {
                        invalidLine = invalidLine + line + ",";
                    }
                }

                for (String key : msisdnList.keySet()) {

                   blacklistNumberRepo.insertIntoTable(key);
                }

                msisdnList.clear();

                if (!invalidLine.isEmpty()) {
                    System.out.println("INVALID RECORD: " + invalidLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("FILE NOT EXIST: " + downloadFilePath);
        }

        System.out.println("FILE READING ENDED AT " + new SimpleDateFormat("yyyy-MM-dd HH:mm.ss").format(new Date()));

    }
}
