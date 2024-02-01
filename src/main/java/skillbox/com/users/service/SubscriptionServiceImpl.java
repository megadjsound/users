package skillbox.com.users.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skillbox.com.users.dto.SubscriptionDto;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SubscriptionDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
        if (subscriptionRepository.existsBySubscriberIdAndSubscribedId
                (subscriptionDto.getSubscriberId(), subscriptionDto.getSubscribedId())) {
            throw new ResponseStatusException(HttpStatus.FOUND);
        }
        if (!userRepository.existsByIdAndDeletedFalse(subscriptionDto.getSubscriberId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!userRepository.existsByIdAndDeletedFalse(subscriptionDto.getSubscribedId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        SubscriptionEntity subscriptionEntity = convertToEntity(subscriptionDto);
        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscriptionEntity);
        return convertToDto(savedSubscription);
    }

    @Override
    public boolean deleteSubscription(Integer subscriptionId) {
        if (!subscriptionRepository.existsById(subscriptionId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        subscriptionRepository.deleteById(subscriptionId);
        return true;
    }

    private SubscriptionDto convertToDto(SubscriptionEntity  subscriptionEntity) {

        if (subscriptionEntity == null) {
            return  null;
        }
        return new SubscriptionDto(
                subscriptionEntity.getId(),
                subscriptionEntity.getSubscribeDate(),
                subscriptionEntity.getSubscriberId(),
                subscriptionEntity.getSubscribedId()
        );
    }

    private SubscriptionEntity convertToEntity(SubscriptionDto subscriptionDto) {

        if (subscriptionDto == null) {
            return null;
        }
        return new SubscriptionEntity(
                subscriptionDto.getId(),
                subscriptionDto.getSubscribeDate(),
                subscriptionDto.getSubscriberId(),
                subscriptionDto.getSubscribedId()
        );
    }
}
