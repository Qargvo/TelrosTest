package com.telros.test.controller;

import com.telros.test.db.DBRepository;
import com.telros.test.model.RequestAnswer;
import com.telros.test.model.UpdateUser;
import com.telros.test.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final DBRepository dbRepository;

    @PostMapping("api/v1/save")
    public ResponseEntity<?> save(@RequestBody User user) {

        dbRepository.save(user);
        return new ResponseEntity<>(new RequestAnswer("Successful save user", HttpStatus.OK.value()), HttpStatus.OK);

    }

    @DeleteMapping("api/v1/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        dbRepository.delete(id);
        return new ResponseEntity<>(new RequestAnswer("Successful delete user", HttpStatus.OK.value()), HttpStatus.OK);

    }

    @GetMapping("api/v1/all")
    public Object getAll() {
        return dbRepository.selectAll();
    }

    @GetMapping("api/v1/filter")
    public Object filter(@RequestBody User user) {
        return dbRepository.select(user);
    }

    @PostMapping("api/v1/update")
    public ResponseEntity<?> updateRaw(@RequestBody UpdateUser user) {
        dbRepository.update(user);
        return new ResponseEntity<>(new RequestAnswer("Successful update user", HttpStatus.OK.value()), HttpStatus.OK);

    }
}
