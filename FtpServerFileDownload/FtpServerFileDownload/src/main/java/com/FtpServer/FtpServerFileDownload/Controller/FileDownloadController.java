package com.FtpServer.FtpServerFileDownload.Controller;

import com.FtpServer.FtpServerFileDownload.Service.FtpDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FileDownloadController {

    @Autowired
    private FtpDownloadService ftpDownloadService;

    @GetMapping("/{filename:.+}")
    public String downloadFile(@PathVariable String filename) {

       ftpDownloadService.downloadFile(filename);
return "file down.........";
    }

//    @GetMapping("/download")
//    public String downloadFile() {
//        try {
////            ftpDownloadService.downloadFile("172.16.15.242", 21, "dwh_ftp_evamp", "evamp@123",
////                   "NON_PROMO_MSISDN_EXPORT_", "/opt/modules/download/NON_PROMO_MSISDN_EXPORT_");
//
//            ftpDownloadService.downloadFile("172.16.15.242", 21, "dwh_ftp_evamp", "evamp@123",
//                   "NON_PROMO_MSISDN_EXPORT_", "/opt/modules/download/NON_PROMO_MSISDN_EXPORT_");
//            return "File downloaded successfully!";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error: " + e.getMessage();
//        }
//    }
}





