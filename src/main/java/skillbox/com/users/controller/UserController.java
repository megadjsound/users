package skillbox.com.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.service.UserService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws ParseException {

        UserDto userResponse = userService.createUser(userDto);
        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {

        boolean updated = userService.updateUser(userDto, id);

        if (!updated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer userId) {
        boolean deleted = userService.deleteUser(userId);

        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        UserDto userDto = userService.getUser(userId);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
