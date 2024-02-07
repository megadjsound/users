package skillbox.com.users.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import skillbox.com.users.dto.SubscriptionDto;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {
    @Mock
    SubscriptionRepository subscriptionRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    SubscriptionServiceImpl subscriptionService;
    SubscriptionEntity testSubscriptionEntity;
    SubscriptionDto testSubscriptionDto;
    @BeforeEach
    void setUp() {
        testSubscriptionDto = new SubscriptionDto(1, LocalDate.now(), 1, 2);
        testSubscriptionEntity = SubscriptionServiceImpl.convertToEntity(testSubscriptionDto);
    }

    @Test
    @DisplayName("JUnit test for GetAllSubscriptions Success")
    void testGetAllSubscriptions_Success() {
        // given
        List<SubscriptionEntity> subscriptionEntities = List.of(testSubscriptionEntity);
        when(subscriptionRepository.findAll()).thenReturn(subscriptionEntities);
        // when
        List<SubscriptionDto> actualSubscriptionsDto = subscriptionService.getAllSubscriptions();
        // then
        assertEquals(1, actualSubscriptionsDto.size());
        verify(subscriptionRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("JUnit test for CreateSubscription Success")
    void testCreateSubscription_Success() {
        // given
        when(subscriptionRepository.save(any(SubscriptionEntity.class)))
                .thenReturn(testSubscriptionEntity);
        Integer subscriberId = 1;
        Integer subscribedId = 2;
        when(userRepository.existsByIdAndDeletedFalse(subscriberId)).thenReturn(Boolean.TRUE);
        when(userRepository.existsByIdAndDeletedFalse(subscribedId)).thenReturn(Boolean.TRUE);
        // when
        SubscriptionDto actualSubscriptionsDto = subscriptionService.createSubscription(testSubscriptionDto);
        // then
        assertTrue(new ReflectionEquals(testSubscriptionDto).matches(actualSubscriptionsDto));
        verify(subscriptionRepository, times(1)).save(any(SubscriptionEntity.class));
    }

    @Test
    @DisplayName("JUnit test for DeleteSubscription Success")
    void testDeleteSubscription_Success() {
        // given
        Integer idSubscription = 1;
        when(subscriptionRepository.existsById(idSubscription)).thenReturn(Boolean.TRUE);
        // when
        boolean isDeleted = subscriptionService.deleteSubscription(idSubscription);
        // then
        verify(subscriptionRepository, times(1)).deleteById(idSubscription);
    }
}