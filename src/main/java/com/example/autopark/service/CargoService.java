package com.example.autopark.service;//бизнес логика

import java.util.List;

import com.example.autopark.model.Cargo;
import com.example.autopark.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired; // для связи зависимостей из всех классов(иначе проект не соберется)
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; // аннотация для обнаружения всех зависимостей, указывает, что класс StudentService принадлежит серверу SpringBoot

@Service
public class CargoService {
    List<Cargo> cargoList = null;
    @Autowired
    private CargoRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Cargo> listAll(String keyword) {/*коллекция, отвечающая за поиск и фильтр*/
        if(keyword != null){
            return repo.search(keyword);
        }
        return repo.findAll();
        //.stream().sorted(Comparator.comparing(Conference::getDate_event)).collect(Collectors.toList());
        //Comparator.comparing принимает функцию ключа сортировки и возвращает компаратор для типа, который содержит ключ сортировки
        /*
        Используем Stream API и Comparator для сортировки списка книг по дате их выпуска.
        Метод bi.findAll() возвращает поток книг, который затем сортируется с помощью объекта Comparator, который сравнивает метод getDate_of_issue()
        каждой книги. Отсортированный поток собирается в список с помощью сборщиков.Метод ToList().
         */
    }

    public void save(Cargo cargo){
        repo.save(cargo);
    }

    public Cargo get(Long id){/*редактирование*/
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

}
