package skillbox.com.users.service;

import skillbox.com.users.dto.UserDto;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto) throws ParseException;
    List<UserDto> getAllUsers();
    boolean updateUser(UserDto userDto, Integer id);
    boolean deleteUser (Integer id);
    UserDto getUser(Integer id);
}
