package com.example.autopark.service;

import com.example.autopark.model.UserInfo;
import com.example.autopark.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//класс сохранения пользователей и сохранения+кодирования их паролей
//тут бизнес логика
@Service
public class UserService {
    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public void addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
    }

    /*

    UserInfoRepository - интерфейс, отвечающий за взаимодействие с хранилищем данных пользователей.
    PasswordEncoder - интерфейс, отвечающий за кодирование паролей пользователей.

    Метод addUser добавляет нового пользователя в базу данных, присваивая ему зашифрованный пароль.
    Для этого используется метод passwordEncoder.encode() класса PasswordEncoder, который кодирует пароль
    в соответствии с определенным алгоритмом. Затем данные пользователя сохраняются в репозитории с помощью метода
    repository.save(userInfo).
     */
}
