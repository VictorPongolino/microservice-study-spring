package br.com.pongolino.study.ApiUsers.api.services;

import br.com.pongolino.study.ApiUsers.api.data.User;
import br.com.pongolino.study.ApiUsers.api.data.UserAuthentication;
import br.com.pongolino.study.ApiUsers.api.data.UserRepository;
import br.com.pongolino.study.ApiUsers.api.presentation.model.UserCreationResponse;
import br.com.pongolino.study.ApiUsers.api.services.model.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = false)
    public UserCreationResponse createUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userDto.setUUID(userDto.getUUID() == null ? UUID.randomUUID().toString() : userDto.getUUID());
        User user = modelMapper.map(userDto, User.class);
        user.setEncryptedPassword(passwordEncoder.encode(userDto.getRawPassword()));
        User persistenceUser = userRepository.save(user);
        return modelMapper.map(persistenceUser, UserCreationResponse.class);
    }

    public Optional<UserDto> findByEmail(String email) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return userRepository.findByEmail(email).map(entity -> modelMapper.map(entity, UserDto.class));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(UserAuthentication::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
