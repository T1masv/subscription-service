package com.example.subsavvy.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userid;

    private String name;
    private int price;
    private Timestamp start_date;
    private Timestamp end_date;
    private boolean trial;
    private Status.StatusType status;

    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp update_at;


    public Subscription(UUID user, String name, int price, Timestamp start_date, Timestamp end_date, boolean trial, Status.StatusType status){
        this.userid=user;
        this.name=name;
        this.price=price;
        this.start_date=start_date;
        this.end_date=end_date;
        this.trial=trial;
        this.status=status;
    }


}
