package ru.vinogradov.kataBoot.conrtoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vinogradov.kataBoot.model.Role;
import ru.vinogradov.kataBoot.repositories.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RoleRestController {

    private final RoleRepository roleRepository;

    public RoleRestController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        return  ResponseEntity.ok(roleRepository.findAll().stream().limit(2).collect(Collectors.toList()));
    }

}
