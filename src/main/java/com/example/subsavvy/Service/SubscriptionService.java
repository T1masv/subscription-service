package com.example.subsavvy.Service;

import com.example.subsavvy.Data.Status;
import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Repository.SubscriptionRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;




@Service
@RequiredArgsConstructor
public class SubscriptionService {


    private final SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(UUID id) {
        return subscriptionRepository.findById(id);
    }

     public Subscription addSubscription(UUID user, String name, int price, Timestamp start_date, Timestamp end_date, boolean trial, Status.StatusType status) {
        Subscription subscription = new Subscription(user,name,price,start_date,end_date,trial,status);
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(UUID id, Subscription subscription) {

        if (subscriptionRepository.existsById(id)) {
            subscription.setId(id);
            return subscriptionRepository.save(subscription);
        } else {
            throw new RuntimeException("Sub not find");
        }
    }

    public void deleteSubscription(UUID id) {
        subscriptionRepository.deleteById(id);
    }

    public List<Subscription> getSubscriptionsByUserId(UUID userId) {
        return subscriptionRepository.findByUserid(userId);
    }
}
