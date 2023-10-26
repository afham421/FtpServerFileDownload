package com.FtpServer.FtpServerFileDownload.Service;



import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FtpDownloadService {

    // Replace with your actual file directory
    private static final String FILE_DIRECTORY = "D:\\NON_PROMO_MSISDN_EXPORT_2023-08-22.txt";

    public ResponseEntity<Resource> downloadFile(String filename) {
        Path filePath = Paths.get(FILE_DIRECTORY).resolve(filename);

        try {
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", filename);

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}




//import org.springframework.stereotype.Service;
//import org.xbib.io.ftp.client.FTP;
//import org.xbib.io.ftp.client.FTPClient;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//@Service
//public class FtpDownloadService {
//
//    static int days = 1;
//
//    private String getFilePostFix() {
//        int x = days;
//        Calendar cal = GregorianCalendar.getInstance();
//        cal.add(6, -x);
//        Date date = cal.getTime();
//        return new SimpleDateFormat("yyyy-MM-dd").format(date) + ".txt";
//    }
//    public void downloadFile(String server, int port, String username, String password,
//                             String remoteFilePath, String localFilePath) throws IOException {
//        FTPClient ftpClient = new FTPClient();
//        try {
//            ftpClient.connect(server, port);
//            ftpClient.login(username, password);
//            ftpClient.enterLocalPassiveMode();
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//
//            try (OutputStream outputStream = new FileOutputStream(localFilePath+getFilePostFix())) {
//                boolean success = ftpClient.retrieveFile(remoteFilePath+getFilePostFix(), outputStream);
//                if (!success) {
//                    throw new IOException("Failed to download the file from the FTP server.");
//                }
//            }
//        } finally {
//            ftpClient.logout();
//            ftpClient.disconnect();
//        }
//    }
//}
