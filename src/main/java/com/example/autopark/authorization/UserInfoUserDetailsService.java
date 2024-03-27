package com.example.autopark.authorization;

import com.example.autopark.model.UserInfo;
import com.example.autopark.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

//класс отвечает за загрузку пользователя по имени из бд и возбуждения ошибки, если он не найден

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }

    /*
    Класс UserInfoUserDetailsService получает объект UserInfoRepository через автосвязывание (@Autowired),
    который используется для поиска пользователя по его имени.

    Метод loadUserByUsername получает имя пользователя и находит его в базе данных через UserInfoRepository.
    Если пользователь найден, создается объект UserInfoUserDetails и возвращается как реализация интерфейса UserDetails.
    Если пользователь не найден, возбуждается исключение UsernameNotFoundException.

     */
}
