package com.FtpServer.FtpServerFileDownload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FtpServerFileDownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtpServerFileDownloadApplication.class, args);
	}

}
