package com.FtpServer.FtpServerFileDownload.Dao;

import com.FtpServer.FtpServerFileDownload.Entity.BlacklistNumber;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacklistNumberRepo extends JpaRepository<BlacklistNumber ,Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT IGNORE INTO blacklist_numbers (msisdn, created_on, created_by) VALUES (?1, NOW(), 'Admin')", nativeQuery = true)
    void insertIntoTable(String msisdn);
}
