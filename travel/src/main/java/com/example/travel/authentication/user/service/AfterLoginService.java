package com.example.travel.authentication.user.service;

import com.example.travel.authentication.component.UserComponent;
import com.example.travel.authentication.user.repo.AuthorityRepo;
import com.example.travel.authentication.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AfterLoginService {
    private final UserRepo userRepo;
    private final AuthorityRepo authorityRepo;
    private final PasswordEncoder encoder;
    private final UserComponent userComponent;
}
