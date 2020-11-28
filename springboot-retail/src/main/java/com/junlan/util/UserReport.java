package com.junlan.util;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class UserReport {

    public byte[] userPDFReport(DataSource dataSource) throws JRException, SQLException {

        ClassLoader classLoader = getClass().getClassLoader();
        System.out.println("JRXML classLoader ---" + classLoader);
        String reportSrcFile = (classLoader.getResource("UserReport.jrxml").getFile());
        System.out.println("JRXML File Path ---" + reportSrcFile.length());
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        Connection conn = dataSource.getConnection();
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null, conn);
        byte[] pdfData = JasperExportManager.exportReportToPdf(print);
        return pdfData;
    }
}
