package skillbox.com.users.service;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @Mock
    SubscriptionRepository subscriptionRepository;
    @InjectMocks
    UserServiceImpl userService;
    UserEntity testUserEntity;
    UserDto testUserDto;

    @BeforeEach
    void setUp() {
        testUserDto = new UserDto(1, "Test user1", "user1", "M", false, "www1@gmail.com",
                "8(123)123-45-678", "address", 1);
        testUserEntity = UserServiceImpl.convertToEntity(testUserDto);
        userService = new UserServiceImpl(userRepository, subscriptionRepository);
    }

    @Test
    @DisplayName("JUnit test for getUser Success")
    void testGetUser_Success() {
        // given
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUserEntity));
        // when
        UserDto actualUserDto = userService.getUser(userId);
        // then
        assertEquals(userId, actualUserDto.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("JUnit test for getUser Fail")
    void testGetUser_Fail() {
        // given
        Integer userId = 1;
        when(userRepository.findById(userId)).thenThrow(PersistenceException.class);
        // when
        Executable executable = () -> userService.getUser(userId);
        // then
        assertThrows(PersistenceException.class, executable);
    }

    @Test
    @DisplayName("JUnit test for GetAllUsers Success")
    void testGetAllUsers_Success() {
        // given
        List<UserEntity> userEntityList = List.of(testUserEntity);
        when(userRepository.findByDeletedFalse()).thenReturn(userEntityList);
        // when
        List<UserDto> usersDto = userService.getAllUsers();
        // then
        assertEquals(userEntityList.size(), usersDto.size());
        verify(userRepository, times(1)).findByDeletedFalse();
    }

    @Test
    @DisplayName("JUnit test for CreateUser Success")
    void testCreateUser_Success() {
        // given
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(testUserEntity);
        // when
        UserDto actualUserDto = userService.createUser(testUserDto);
        // then
        assertTrue(new ReflectionEquals(testUserDto).matches(actualUserDto));
        verify(userRepository, times(1)).save(Mockito.any(UserEntity.class));
    }

    @Test
    @DisplayName("JUnit test for UpdateUser Success")
    void testUpdateUser_Success() {
        // given
        Integer userId = 1;
        when(userRepository.existsByIdAndDeletedFalse(userId)).thenReturn(Boolean.TRUE);
        when(userRepository.save(any(UserEntity.class))).thenReturn(testUserEntity);
        // when
        boolean isUpdated = userService.updateUser(testUserDto, userId);
        // then
        assertTrue(isUpdated);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("JUnit test for DeleteUser Success")
    void testDeleteUser_Success() {
        // given
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUserEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(testUserEntity);
        // when
        boolean isDeleted = userService.deleteUser(userId);
        // then
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(subscriptionRepository, times(1)).deleteSubscriptionByUserId(userId);
    }
}