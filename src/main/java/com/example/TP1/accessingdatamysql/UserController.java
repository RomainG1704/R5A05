package com.example.TP1.accessingdatamysql;

import com.example.TP1.Entite.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/user/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // Code HTTP 204 No Content si la liste est vide
        }
        return ResponseEntity.ok(users); // Code HTTP 200 OK avec la liste des utilisateurs
    }

    @GetMapping(path="/user/get")
    public ResponseEntity<User> getUser(@RequestParam(name = "id") int id) {

        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            return ResponseEntity.ok(user); // Code HTTP 200 OK avec la liste des utilisateurs
        }
        return ResponseEntity.noContent().build();
    }


    @PostMapping(path="/user/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody User user) {

        userRepository.save(user);
        return "Saved";
    }

    @PutMapping(path = "/user/update")
    public @ResponseBody ResponseEntity<?> updateUser (
            @RequestParam(name = "id") int id,
            @RequestBody User updatedUser) {

        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());

            userRepository.save(user);

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(path="/user/delete") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<?> deleteUser (@RequestBody Map<String, Long> requestBody ) {

        long id = requestBody.get("id");
        if (userRepository.findById((int) id).isPresent()) {
            User user = userRepository.findById((int) id).get();
            userRepository.delete(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.noContent().build();
    }
}
