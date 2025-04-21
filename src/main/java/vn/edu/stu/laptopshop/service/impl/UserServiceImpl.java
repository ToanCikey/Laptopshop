package vn.edu.stu.laptopshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.stu.laptopshop.common.UserStatus;
import vn.edu.stu.laptopshop.controller.request.user.UserCreateRequest;
import vn.edu.stu.laptopshop.controller.request.user.UserUpdateRequest;
import vn.edu.stu.laptopshop.exception.InvalidDataException;
import vn.edu.stu.laptopshop.exception.ResourceNotFoundException;
import vn.edu.stu.laptopshop.model.UserEntity;
import vn.edu.stu.laptopshop.repository.UserRepository;
import vn.edu.stu.laptopshop.service.UserService;

import java.util.List;
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

         UserEntity userEntity = UserEntity.builder()
                 .email(request.getEmail())
                 .password(passwordEncoder.encode(request.getPassword()))
                 .fullName(request.getFullName())
                 .phone(request.getPhone())
                 .status(request.getStatus())
                 .address(request.getAddress())
                 .type(request.getType())
                 .build();

         return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public void updateUser(UserUpdateRequest request) {
        UserEntity user = getUserById(request.getId());
        if(user != null) {
            user.setFullName(request.getFullName());
            user.setPhone(request.getPhone());
            user.setAddress(request.getAddress());
            
            if(request.getStatus() != null){
                user.setStatus(request.getStatus());
            }

            if(request.getType() != null){
                user.setType(request.getType());
            }
            
            userRepository.save(user);
        }
    }

    @Override
    public void lockUser(Long id) {
        UserEntity user = getUserById(id);
        if(user != null) {
            user.setStatus(UserStatus.INACTIVE);
            userRepository.save(user);
        }
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
