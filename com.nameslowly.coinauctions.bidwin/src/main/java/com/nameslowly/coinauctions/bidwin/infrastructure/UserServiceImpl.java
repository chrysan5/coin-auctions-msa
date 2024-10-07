package com.nameslowly.coinauctions.bidwin.infrastructure;

import com.nameslowly.coinauctions.bidwin.application.dto.UserDto;
import com.nameslowly.coinauctions.bidwin.application.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public UserDto getUser() {
        // not -> exception
        return null;
    }
}
