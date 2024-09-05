package org.Sem3.HM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ServicePerson {
    @Autowired
    private PersonRepository personRepository;

    /**
     * save a new person
     */
    public Person save(Person person) {
        return personRepository.save(person);
    }

    /**
     * get all persons
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * find a person by id
     */
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    /**
     * find a person by name
     */
    public Person getPersonByName(String name) {
        return personRepository.findByName(name).orElse(null);
    }

    /**
     * delete a person by id
     */
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    /**
     * update any fields in a person info
     */
    public Person updatePerson(Long id) throws IllegalAccessException {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            System.out.println("Person not found");
            return null;
        }
        Person personToUpdate = person.get();
        Field[] fields = personToUpdate.getClass().getDeclaredFields();
        int i = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(i + ". " + field.getName() + ": " + field.get(person));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the field to update: ");
        int fieldToUpdate = scanner.nextInt();
        scanner.nextLine();

        if (fieldToUpdate < 1 || fieldToUpdate > fields.length) {
            System.out.println("Invalid choice.");
            return personToUpdate;
        }

        Field fieldTo = fields[fieldToUpdate - 1];
        System.out.println("Enter new value for " + fieldTo.getName() + ": ");
        String newValue = scanner.nextLine();

        if (fieldTo.getType() == int.class) {
            fieldTo.setInt(person, Integer.parseInt(newValue));
        } else if (fieldTo.getType() == double.class) {
            fieldTo.setDouble(person, Double.parseDouble(newValue));
        } else {
            fieldTo.set(person, newValue);
        }
        return personRepository.save(personToUpdate);
    }
}
