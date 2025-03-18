package ru.example.service.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.model.dto.user.NewUserDto;
import ru.example.model.dto.user.UserDto;
import ru.example.model.mapper.UserMapper;
import ru.example.repo.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDto add(NewUserDto newUserDto) {
        return UserMapper.toDto(
                userRepository.save(UserMapper.toEntity(newUserDto)));
    }

}
