package com.example.autopark.config;

import com.example.autopark.authorization.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("Basant")
//                .password(encoder.encode("Pwd1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("John")
//                .password(encoder.encode("Pwd2"))
//                .roles("USER","ADMIN","HR")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
        return new UserInfoUserDetailsService();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                .and()
                .authorizeHttpRequests()//начало цепочки настройки прав доступа на основе запросов
                //.requestMatchers("/register").permitAll()
                .requestMatchers("/register").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeHttpRequests().requestMatchers("/**").authenticated()
                .and()
                .formLogin()// начинает конфигурацию формы входа в систему
                    .loginPage("/login_page")
                    .defaultSuccessUrl("/") //устанавливает URL, на который будет перенаправлен пользователь после успешного входа в систему
                    .permitAll()
                .and()
                .exceptionHandling()
                //начало настройки обработки исключений при выполнении запросов
                .accessDeniedPage("/403")
                //указывает на то, что если у пользователя нет прав доступа к определенному ресурсу, то он будет перенаправлен на страницу с кодом ответа HTTP 403 ("Forbidden").

        .and()
                .logout()
                    .logoutSuccessUrl("/login_page")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and().build(); //завершает настройку цепочки и возвращает объект
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*
    PasswordEncoder используется для кодирования паролей пользователей при аутентификации

    BCrypt - это один из самых популярных алгоритмов кодирования паролей. Этот алгоритм обеспечивает высокую стойкость к
    атакам перебора, что означает, что злоумышленнику потребуется очень много времени и ресурсов, чтобы расшифровать хэш пароля.
    Ключевым элементом алгоритма BCrypt является использование соли. Соль - это случайная строка символов, которая добавляется
    к паролю перед его хэшированием. Каждый раз, когда пользователь создает новый аккаунт, генерируется новая случайная соль,
    которая добавляется к его паролю. Это означает, что два пользователя с одинаковыми паролями будут иметь разные хеши в базе данных,
     потому что у них будут разные соли.
    Соль улучшает стойкость к атакам перебора, потому что она усложняет процесс поиска паролей, основанных на словарях и
    предварительно вычисленных хешах. Даже если злоумышленник узнает соль, он должен будет перебирать хеши для каждого пароля,
    чтобы найти совпадение, что является очень трудоемкой задачей.
    Другим важным элементом алгоритма BCrypt является множественные итерации хеширования. Каждый раз, когда пароль хэшируется,
    он проходит через функцию хэширования несколько раз. Это усложняет процесс перебора, потому что злоумышленник должен будет
    перебрать каждую итерацию, что занимает больше времени и ресурсов.
     */

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(); // создание объекта DaoAuthenticationProvider и присвоение его ссылки на переменную authenticationProvider
        authenticationProvider.setUserDetailsService(userDetailsService()); //вызов метода setUserDetailsService() на объекте authenticationProvider для установки объекта UserDetailsService, который будет использоваться для получения информации о пользователе при аутентификации
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // вызов метода setPasswordEncoder() на объекте authenticationProvider для установки объекта PasswordEncoder, который будет использоваться для кодирования пароля пользователя при аутентификации
        return authenticationProvider;
    }

    /*
    Аннотация @Bean используется в Spring Framework для указания, что метод, к которому она применяется,
     возвращает объект, который следует зарегистрировать в контексте приложения как компонент (бин).
    Когда Spring создает контекст приложения, он анализирует классы, находит методы с аннотацией @Bean и вызывает
    их для получения объектов. Затем эти объекты могут быть внедрены (injected) в другие объекты, используя механизмы
    внедрения зависимостей (dependency injection), что позволяет управлять жизненным циклом и конфигурацией объектов.
    Таким образом, аннотация @Bean используется для создания и регистрации компонентов в Spring-контексте,
    чтобы их можно было использовать в других компонентах приложения.
     */

}
