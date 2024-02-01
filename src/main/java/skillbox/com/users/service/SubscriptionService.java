package skillbox.com.users.service;

import skillbox.com.users.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionDto> getAllSubscriptions();
    SubscriptionDto createSubscription(SubscriptionDto subscriptionDto);
    boolean deleteSubscription(Integer subscriptionId);
}
