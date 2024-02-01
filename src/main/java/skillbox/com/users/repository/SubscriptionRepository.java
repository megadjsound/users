package skillbox.com.users.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import skillbox.com.users.entity.SubscriptionEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    boolean existsBySubscriberIdAndSubscribedId(Integer subscriberId, Integer subscribedId);
    @Modifying
    @Transactional
    @Query(value = "Delete SubscriptionEntity se WHERE se.subscriberId = :p_user_id OR se.subscribedId = :p_user_id")
    void deleteSubscriptionByUserId(@Param("p_user_id") Integer userId);
}
