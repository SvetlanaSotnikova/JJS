package org.Sem3.HM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication(scanBasePackages = "org.Sem3.HM")
public class Main {
    private static final String FILE_JSON = "src/main/resources/person_data.json";

    public static void main(String[] args) throws IllegalAccessException {
        List<Person> persons = List.of(new Person(1L, "Kolea", 20, "GPA")
                , new Person(2L, "Misha", 15, "GPA"));
        Serialization.savePersonToFile(FILE_JSON, persons);
        List<Person> loadedPersons = (List<Person>) Serialization.loadPersonFromFile(FILE_JSON, Person.class);
        System.out.println(loadedPersons);

//        ServicePerson servicePerson1 = new ServicePerson();
//        Person person1 = servicePerson.save(persons.getFirst());
//        Person person2 = servicePerson.save(persons.getLast());

        ApplicationContext context = SpringApplication.run(org.Sem3.HM.Main.class, args);

        ServicePerson servicePerson = context.getBean(ServicePerson.class);

        servicePerson.save(persons.getFirst());
        servicePerson.save(persons.getLast());
        servicePerson.getAllPersons().forEach(System.out::println);
        System.out.println("get person by id 2: " + servicePerson.getPersonById(2L));
        System.out.println("get person by name: " + servicePerson.getPersonByName("Kolea"));
        servicePerson.updatePerson(1L);
        servicePerson.deletePerson(1L);
        System.out.println("Персона удалена");
    }
}
