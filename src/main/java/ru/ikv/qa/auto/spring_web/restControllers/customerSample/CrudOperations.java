package ru.ikv.qa.auto.spring_web.restControllers.customerSample;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ikv.qa.auto.spring_web.restControllers.dto.ClientTypeCode;
import ru.ikv.qa.auto.spring_web.restControllers.dto.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("/customer")
public class CrudOperations {
    Map<Integer, Customer> data = new HashMap<>();

    @PostConstruct
    void init() {
        data.put(1, new Customer(1, "Иванов Иван Иванович", "ИП Иванов и К", true, "1234567", ClientTypeCode.CLIENT));
        data.put(2, new Customer(2, "Петров Сергей Иванович", "ООО Серго", true, "1234098", ClientTypeCode.PROSPECT));
        data.put(3, new Customer(3, "Сидоров Арсений Петрович", "ИП АПС", false, "1004567", ClientTypeCode.FORMER_CLIENT));
        data.put(4, new Customer(4, "Быстров Евгений Олегович", "ИП Технический", false, "10869966", ClientTypeCode.TECHNICAL_CLIENT));
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable int id) {
        if(!data.containsKey(id)) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return data.get(id);
    }
    @GetMapping("/all")
    public List<Customer> getAll() {
        return new ArrayList<>(data.values());
    }

    @PostMapping
    public ResponseEntity saveCustomer(@RequestBody Customer customer){
        int id = data.size() + 1;
        customer.setId(id);
        data.put(id, customer);
        return new ResponseEntity<>(CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity changeCustomer(@PathVariable int id, @RequestBody Customer customerChanging) {
        Customer customer = data.get(id);
        customer.setName(customerChanging.getName());
        customer.setFullName(customerChanging.getFullName());
        customer.setInn(customerChanging.getInn());
        customer.setResident(customerChanging.isResident());
        customer.setClientTypeCode(customerChanging.getClientTypeCode());
        data.put(id, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        data.remove(id);
    }
}
