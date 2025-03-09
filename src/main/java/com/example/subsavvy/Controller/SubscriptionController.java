package com.example.subsavvy.Controller;

import com.example.subsavvy.Data.Subscription;
import com.example.subsavvy.Service.SubscriptionService;
import com.example.subsavvy.dto.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);


    private final SubscriptionService subscriptionService;

    @GetMapping("/{userId}")
    public List<Subscription> getAllSubscriptions(@PathVariable UUID userId) {
        logger.info("Fetching all subscriptions for user: {}", userId);
        return subscriptionService.getSubscriptionsByUserId(userId);
    }

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(
            @PathVariable UUID userId,
            @PathVariable UUID id
    ) {
        logger.info("Fetching subscription {} for user {}", id, userId);
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);

        if (subscription.isPresent() && subscription.get().getUserid().equals(userId)) {
            return ResponseEntity.ok(subscription.get());
        }
        logger.warn("Unauthorized access attempt for subscription {} by user {}", id, userId);
        return ResponseEntity.status(403).body(null);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Subscription> addSubscription(
            @PathVariable UUID userId,
            @RequestBody SubscriptionDto subscriptionDto
    ) {
        logger.info("Adding subscription for user {}", userId);

        Subscription addedSubscription = subscriptionService.addSubscription(
                userId, subscriptionDto.getName(), subscriptionDto.getPrice(),
                subscriptionDto.getStart_date(), subscriptionDto.getEnd_date(),
                subscriptionDto.isTrial(), subscriptionDto.getStatus()
        );

        logger.info("Subscription added successfully for user {}", userId);
        return ResponseEntity.ok(addedSubscription);
    }

    @PutMapping("/{userId}/{id}")
    public ResponseEntity<Subscription> updateSubscription(
            @PathVariable UUID userId,
            @PathVariable UUID id,
            @RequestBody Subscription subscription
    ) {
        logger.info("Updating subscription {} for user {}", id, userId);

        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);
        if (existingSubscription.isPresent() && existingSubscription.get().getUserid().equals(userId)) {
            subscription.setUserid(userId);
            Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscription);
            return ResponseEntity.ok(updatedSubscription);
        }
        logger.warn("Unauthorized update attempt for subscription {} by user {}", id, userId);
        return ResponseEntity.status(403).body(null);
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<Void> deleteSubscription(
            @PathVariable UUID userId,
            @PathVariable UUID id
    ) {
        logger.info("Deleting subscription {} for user {}", id, userId);

        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionById(id);
        if (existingSubscription.isPresent() && existingSubscription.get().getUserid().equals(userId)) {
            subscriptionService.deleteSubscription(id);
            logger.info("Subscription {} deleted successfully for user {}", id, userId);
            return ResponseEntity.noContent().build();
        }
        logger.warn("Unauthorized deletion attempt for subscription {} by user {}", id, userId);
        return ResponseEntity.status(403).body(null);
    }
}
