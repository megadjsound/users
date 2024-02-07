package skillbox.com.users.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.repository.CityRepository;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {
    @Mock
    CityRepository cityRepository;
    @InjectMocks
    CityServiceImpl cityService;
    CityEntity testCityEntity;
    CityDto testCityDto;
    @BeforeEach
    void setUp() {
        testCityDto = new CityDto(1, "Kaliningrad");
        testCityEntity = CityServiceImpl.convertToEntity(testCityDto);
    }

    @Test
    @DisplayName("JUnit test for GetAllCities Success")
    void tesGetAllCities_Success() {
        // given
        List<CityEntity> testCities = new ArrayList<>();
        testCities.add(new CityEntity());
        when(cityRepository.findAll()).thenReturn(testCities);
        // when
        List<CityDto> citiesDto = cityService.getAllCities();
        // then
        assertEquals(testCities.size(), citiesDto.size());
        verify(cityRepository, times(1)).findAll();
    }
}