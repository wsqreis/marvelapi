package com.talents.orange.marvelapi.service;

import com.talents.orange.marvelapi.dto.response.ComicDTO;
import com.talents.orange.marvelapi.dto.request.UserDTO;
import com.talents.orange.marvelapi.dto.response.UserComicsDTO;
import com.talents.orange.marvelapi.entity.User;
import com.talents.orange.marvelapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public String createUser(UserDTO userDTO) {
        verifyEmailAndCpf(userDTO);

        User userToSave = modelMapper.map(userDTO, User.class);
        userRepository.save(userToSave);

        return "Created user with ID " + userToSave.getId();
    }

    public UserComicsDTO getById(Long id){
        User user = findById(id);

        UserComicsDTO userComicsDTO = modelMapper.map(user, UserComicsDTO.class);

        List<ComicDTO> comicDTOList = user.getComics()
                .stream()
                .map(comic -> modelMapper.map(comic, ComicDTO.class))
                .collect(Collectors.toList());

        userComicsDTO.setComics(comicDTOList);

        userComicsDTO.setDiscount(userComicsDTO.getComics());

        return userComicsDTO;
    }

    private void verifyEmailAndCpf(UserDTO userDTO) {
        Optional<User> email = userRepository.findUserByEmail(userDTO.getEmail());
        Optional<User> cpf = userRepository.findUserByCpf(userDTO.getCpf());

        if (cpf.isPresent() && email.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email and CPF already exists!");
        } else if (email.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
        } else if (cpf.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF already exists!");
        }
    }

    protected User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

}
