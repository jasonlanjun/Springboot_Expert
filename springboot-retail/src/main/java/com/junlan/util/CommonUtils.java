package com.junlan.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommonUtils {

    public boolean isContactValid(String contactNo) {
        Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher matcher = pattern.matcher(contactNo);
        return (matcher.find() && matcher.group().equals(contactNo));
    }

    public boolean isLicenseValid(String license) {
        Pattern pattern = Pattern.compile("[0-9]{5}");
        Matcher matcher = pattern.matcher(license);
        return (matcher.find() && matcher.group().equals(license));
    }

    public boolean isDateValid(String dateToValidate) {
		return dateToValidate.matches("^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$");
    }

    public String getTime() {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return (dt.format(new Date()));
    }
}
