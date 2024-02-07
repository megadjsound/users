package skillbox.com.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public UserServiceImpl(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto)
    {
        if (userRepository.existsByLoginAndDeletedFalse(userDto.getLogin())) {
            throw new ResponseStatusException(HttpStatus.FOUND);
        }
        UserEntity userEntity = convertToEntity(userDto);
        UserEntity userCreated = userRepository.save(userEntity);
        return convertToDto(userCreated);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findByDeletedFalse().stream()
                .map(UserServiceImpl::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateUser(UserDto userDto, Integer id) {

        if (!userRepository.existsByIdAndDeletedFalse(id)){
            return false;
        }

        userDto.setId(id);

        UserEntity userEntity = convertToEntity(userDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return true;
    }
    @Override
    public boolean deleteUser (Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (userEntity.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userEntity.setDeleted(true);
        userRepository.save(userEntity);
        subscriptionRepository.deleteSubscriptionByUserId(id);
        return true;
    }

    @Override
    public UserDto getUser(Integer id) {
        return userRepository.findById(id)
                .map(UserServiceImpl::convertToDto)
                .orElse(null);
    }

    public static UserDto convertToDto(UserEntity userEntity) {

        if (userEntity == null) {
            return  null;
        }
        return new UserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLogin(),
                userEntity.getGender(),
                userEntity.isDeleted(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.getAddress(),
                userEntity.getCityId()
        );
    }

    public static UserEntity convertToEntity(UserDto userDto) {

        if (userDto == null) {
            return null;
        }
        return new UserEntity(
                userDto.getId(),
                userDto.getName(),
                userDto.getLogin(),
                userDto.getGender(),
                userDto.isDeleted(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getAddress(),
                userDto.getCityId()
        );
    }
}
