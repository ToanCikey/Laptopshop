package vn.edu.stu.laptopshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.stu.laptopshop.controller.request.user.UserCreateRequest;
import vn.edu.stu.laptopshop.exception.InvalidDataException;
import vn.edu.stu.laptopshop.exception.ResourceNotFoundException;
import vn.edu.stu.laptopshop.model.UserEntity;
import vn.edu.stu.laptopshop.repository.UserRepository;
import vn.edu.stu.laptopshop.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity addUser(UserCreateRequest request) {
         Optional<UserEntity> userEmail = userRepository.findByEmail(request.getEmail());
         if (userEmail.isPresent()) {
             throw new InvalidDataException("Email " + request.getEmail() + " already exists");
         }

         UserEntity userEntity = new UserEntity();
         userEntity.setEmail(request.getEmail());
         userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
         userEntity.setFirstName(request.getFirstName());
         userEntity.setLastName(request.getLastName());
         userEntity.setPhone(request.getPhone());
         userEntity.setStatus(request.getStatus());

         return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User with email " + email + " not found"));
    }
}
