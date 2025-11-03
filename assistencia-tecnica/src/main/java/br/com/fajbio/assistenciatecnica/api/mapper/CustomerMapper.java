package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CustomerRes;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerRes mappear(Customer customer) {
        return CustomerRes.builder()
                .id(customer.getId())
                .nomeLegal(customer.getNomeLegal())
                .documento(customer.getDocumento())
                .build();
    }

    public List<CustomerRes> mappear(List<Customer> customers) {
        return customers.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }
}
