package com.example.autopark.controller;
import java.util.List;

import com.example.autopark.model.UserInfo;
import com.example.autopark.service.UserService;
import com.example.autopark.model.Cargo;
import com.example.autopark.model.Record;
import com.example.autopark.service.RecordService;
import com.example.autopark.service.CargoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired; // аннотация
import org.springframework.data.repository.query.Param; // привязываем наши параметры к передаче данных из sql запроса
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;  //определяет наш класс, как управляющий
import org.springframework.ui.Model; // интерфейс, необходимый для взаимодействия контроллера и конфигуратора Model u Controller, а также для добавления всех элементов в веб-интерфейс
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    @Autowired
    private CargoService service;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;

//    @RequestMapping("/")
//    public String viewHomePage(Model model, @Param("keyword") String keyword) {
//        List<Cargo> listCargo = service.listAll(keyword);
//        model.addAttribute("listCargo", listCargo); // переменная, которую будем использовать в веб-интерфейсе
//        model.addAttribute("keyword", keyword);
//        return "index";
//    }
    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword,
                           @ModelAttribute(name = "cargo") Cargo cargo) {
    List<Cargo> listCargo = service.listAll(keyword);
    model.addAttribute("listCargo", listCargo);
    model.addAttribute("keyword", keyword);
    return "index";
    }
    //@RequestMapping("/new")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //public String showNewConferenceForm(Model model) {
     //   Cargo cargo = new Cargo();
     //   model.addAttribute("cargo", cargo);
     //   return "new_cargo";
    //}
//
    @RequestMapping(value ="/new", method = RequestMethod.POST)
    public String addCargo(@ModelAttribute(name="cargo") Cargo newCargo) {
       service.save(newCargo);
       return "index";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCargo(@ModelAttribute("cargo") Cargo cargo) { 
        System.out.println(cargo);
        service.save(cargo);
        return "redirect:/";
    }

//    @RequestMapping("/edit/{id}") // контроллер для редактирования студентов по ключу
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ModelAndView showEditCargoForm(@PathVariable(name="id") Long id) {
//        ModelAndView mav = new ModelAndView("index");
//        Cargo cargo = service.get(id);
//        mav.addObject("Cargo", cargo);
//        return mav; // возвращаем страницу с данными о студентах по id
//    }

    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteCargo(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        userService.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }
    @GetMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        //new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "privetic_admin";
    }

    /*
    Метод addNewUser() является обработчиком POST-запроса на адрес /register и принимает параметры запроса: userInfo, name, roles и session.
    Параметр @ModelAttribute UserInfo userInfo представляет собой объект модели, который содержит информацию о новом пользователе, к
    оторую пользователь вводит на странице регистрации. Параметры @RequestParam String name и @RequestParam String roles представляют собой параметры запроса, которые содержат имя пользователя и его роли соответственно. Параметр HttpSession session используется для сохранения информации о зарегистрированном пользователе.

    Внутри метода addNewUser() вызывается сервис для добавления нового пользователя в базу данных, а затем сохраняется имя пользователя и его роли в сессии. После этого метод перенаправляет пользователя на главную страницу приложения.

    Метод register() является обработчиком GET-запроса на адрес /register и принимает параметры запроса HttpServletRequest request и HttpServletResponse response. Внутри метода создается новый SecurityContextLogoutHandler для выхода из текущей сессии пользователя, после чего пользователь перенаправляется на страницу с приветствием.

    В целом, данный код реализует функциональность регистрации нового пользователя и выхода из текущей сессии. Однако, для полной оценки его работы необходимо знать, как определены классы UserInfo и userService, а также как настроены механизмы аутентификации и авторизации в приложении.
     */

    @GetMapping("/login_page")
    public String showLogin() {// add any additional objects you want to pass to the HTML page
        return "login_page";
    }
    @PostMapping("/login_page")
    public String SuccessLogin(@RequestParam String username, HttpSession session) {
        // Аутентифицируйте пользователя и установка атрибут "username" в модели
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//        HttpSession session = request.getSession(true);
        session.setAttribute("username", currentUser);
//        session.setAttribute("password", password);
        return "redirect:/";
    }

    /*
    Метод showLogin() является обработчиком GET-запроса на адрес /login_page. Он используется для отображения страницы
    входа в систему для пользователя. В данном методе возвращается строковое значение "login_page", которое является именем
    HTML-шаблона для отображения страницы входа. В методе не происходит никакой аутентификации пользователя.

    Метод SuccessLogin() является обработчиком POST-запроса на адрес /login_page. Он используется для аутентификации
    пользователя и установки его имени в сессию. Метод принимает параметры запроса username и session.
    Параметр username представляет имя пользователя, которое он ввел при входе в систему. Параметр session представляет
    объект сессии, используемый для сохранения информации о пользователе.

    Внутри метода вызывается метод getName() объекта Authentication из SecurityContextHolder.getContext(),
    чтобы получить имя аутентифицированного пользователя. Полученное имя сохраняется в сессии пользователя,
    используя метод setAttribute(). После этого метод перенаправляет пользователя на главную страницу приложения.

    В целом, данный код реализует функциональность входа пользователя в систему и сохранения его имени в сессии.
    Однако, для полной оценки его работы необходимо знать, как настроены механизмы аутентификации и авторизации в приложении,
    а также как реализовано представление страницы входа в систему.
     */

    @RequestMapping("/autoblog")
    public String viewAutoblog(Model model, @Param("keyword") String keyword,
                               @ModelAttribute(name = "record") Record record) {
        List<Record> listRecord = recordService.listAllRecords(keyword);
        model.addAttribute("listRecord", listRecord);
        model.addAttribute("keyword", keyword);
        return "autoblog";
    }

    @RequestMapping(value ="/newRecord", method = RequestMethod.POST)
    public String addRecord(@ModelAttribute(name="record") Record newRecord) {
        recordService.save(newRecord);
        return "autoblog";
    }

    @RequestMapping(value = "/saveRecord", method = RequestMethod.POST)
    public String saveRecord(@ModelAttribute("record") Record record) {
        System.out.println(record);
        recordService.save(record);
        return "redirect:/autoblog";
    }

    /*@RequestMapping("/editRecord/{id}") // контроллер для редактирования студентов по ключу
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView showEditRecordForm(@PathVariable(name="id") Long id) {
        ModelAndView mav = new ModelAndView("edit_record");
        Record record = recordService.get(id);
        mav.addObject("Record", record);
        return mav; // возвращаем страницу с данными о студентах по id
    }*/
    @RequestMapping("/deleteRecord/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRecord(@PathVariable(name = "id") Long id) {
        recordService.delete(id);
        return "redirect:/autoblog";
    }

    @RequestMapping("/find")
    public String searchRecord(Model model, @Param("keywordRecord") String keywordRecord, @Param("keywordPubDate") String keywordPubDate, @Param("keywordDateName") String keywordRecordPubDate, @Param("keywordAuthor") String keywordAuthor, @Param("keywordText") String keywordText){
        List<Record> listRecordByCriteria = recordService.listByRecordCriteria(keywordRecord, keywordPubDate, keywordRecordPubDate, keywordAuthor, keywordText);
        model.addAttribute("listRecord", listRecordByCriteria);
        model.addAttribute(recordService.getKeywordName(), recordService.getKeyword());
        return "autoblog";
    }

    @GetMapping("/403")
    public String _error() {
        return "403";
    }

}
