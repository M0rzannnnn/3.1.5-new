package ru.vinogradov.kataBoot.conrtoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vinogradov.kataBoot.model.User;
import ru.vinogradov.kataBoot.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws Exception {
        User user = userService.findById(id);
        if(user == null) {
            throw new Exception("There is no user with id = " + id + " in database.");
        }

        userService.deleteUser(id);
        return ResponseEntity.ok("User with id = " + id + " was deleted.");
    }

}
