package com.example.subsavvy.dto;

import com.example.subsavvy.Data.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionDto {
    private String name;
    private int price;
    private boolean trial;
    private Status.StatusType status;
    private Timestamp start_date;
    private Timestamp end_date;

    public SubscriptionDto( String name, int price, Timestamp start_date, Timestamp end_date, boolean trial, Status.StatusType status){
        this.name=name;
        this.price=price;
        this.start_date=start_date;
        this.end_date=end_date;
        this.trial=trial;
        this.status=status;
    }
}
