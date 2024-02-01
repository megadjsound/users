package skillbox.com.users.service;

import org.springframework.stereotype.Service;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.repository.CityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    //@Autowired
    //private ModelMapper modelMapper;
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CityDto convertToDto(CityEntity cityEntity) {

        //return modelMapper.map(cityEntity, CityDto.class);
        if (cityEntity == null) {
            return  null;
        }
        return new CityDto(
                cityEntity.getName()
        );
    }

    private CityEntity convertToEntity(CityDto cityDto) {
        //return modelMapper.map(cityDto, CityEntity.class);
        if (cityDto == null) {
            return null;
        }
        return new CityEntity(
                cityDto.getName()
        );
    }
}
