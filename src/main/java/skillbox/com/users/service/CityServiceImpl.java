package skillbox.com.users.service;

import org.springframework.stereotype.Service;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.repository.CityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityRepository.findAll().stream()
                .map(CityServiceImpl::convertToDto)
                .collect(Collectors.toList());
    }

    public static CityDto convertToDto(CityEntity cityEntity) {

        if (cityEntity == null) {
            return  null;
        }
        return new CityDto(
                cityEntity.getId(),
                cityEntity.getName()
        );
    }

    public static CityEntity convertToEntity(CityDto cityDto) {

        if (cityDto == null) {
            return null;
        }
        return new CityEntity(
                cityDto.getName()
        );
    }
}
