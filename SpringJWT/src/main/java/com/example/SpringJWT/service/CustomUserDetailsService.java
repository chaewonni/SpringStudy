package com.example.SpringJWT.service;

import com.example.SpringJWT.dto.CustomUserDetails;
import com.example.SpringJWT.entity.UserEntity;
import com.example.SpringJWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//DB 기반 로그인 검증 로직
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username);

        if(userData != null) {

            return new CustomUserDetails(userData);
        }

        return null;
    }
}
