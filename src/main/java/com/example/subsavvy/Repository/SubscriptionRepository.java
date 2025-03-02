package com.example.subsavvy.Repository;

import com.example.subsavvy.Data.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;


public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    // Méthode pour récupérer toutes les souscriptions par utilisateur
    List<Subscription> findByUserid(UUID userid);


}
