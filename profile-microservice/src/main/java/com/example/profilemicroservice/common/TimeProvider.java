package com.example.profilemicroservice.common;


import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;

    public Date now() {
        return new Date();
    }

    public Timestamp nowTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    public long timeDifferenceInMinutes(Date time1, Date time2) {
        long difference = Math.abs(time1.getTime() - time2.getTime());
        return difference / (60 * 1000);
    }
}