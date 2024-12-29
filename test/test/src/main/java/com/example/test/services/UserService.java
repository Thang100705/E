package com.example.test.services;
import com.example.test.Dto.LoginDto;
import com.example.test.models.Posts;
import com.example.test.models.Users;
import com.example.test.respositories.UserRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRespo userRespo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRespo userRespo, PasswordEncoder passwordEncoder) {
        this.userRespo = userRespo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<Users> getAll() {
        return userRespo.findAll();
    }
    @Transactional
    public Optional<Users>getUserId(Long id){
        return userRespo.findById(id);
    }
    @Transactional
    public void deleteUserById(Long id){
        userRespo.deleteById(id);
    }

    @Transactional
    public Users save(Users user) {
        //mã hóa password
        if (user.getRole() == null) {
            user.setRole(Users.Role.USER); // Mặc định vai trò USER
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated_at(LocalDate.now());
        return userRespo.save(user);
    }

    @Transactional
    public ResponseEntity<Users> loginUser(LoginDto loginDto) {
        Optional<Users> userOptional = userRespo.findByUsername(loginDto.getUsername());
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            boolean isPasswordMatch = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
            if (isPasswordMatch) {
                    return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    public Optional<Users> getUserByUsername(String username) {
        return userRespo.findByUsername(username);
    }

    public Users update(Long id,Users users){
        return userRespo.findById(id).map(userUpdate -> {
            userUpdate.setPassword(users.getPassword());
            return userRespo.save(userUpdate); // Lưu và trả về bản ghi đã cập nhật
        }).orElseThrow(() ->new RuntimeException("Not Found with id:" + id));
    }


}
