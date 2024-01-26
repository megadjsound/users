package skillbox.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skillbox.com.users.entity.SubscriptionEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
}
