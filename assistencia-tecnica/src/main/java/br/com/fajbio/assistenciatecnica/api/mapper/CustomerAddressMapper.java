package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CustomerAddressReq;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerAddressRes;
import br.com.fajbio.assistenciatecnica.domain.model.Address;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerAddressMapper {
    private final AddressMapper addressMapper;

    public CustomerAddressRes mappear(CustomerAddress address) {
        return CustomerAddressRes.builder()
                .id(address.getId())
                .tipo(address.getTipo())
                .build();
    }

    public CustomerAddress mappear(CustomerAddressReq address){
        return CustomerAddress.builder()
                .address(addressMapper.mappear(address.address()))
                .tipo(address.tipo())
                .build();
    }
    
    public List<CustomerAddress> mappear(List<CustomerAddressReq> addresses, Customer customer) {
        return addresses.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public List<CustomerAddressRes> mappear(List<CustomerAddress> addresses) {
        return addresses.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public CustomerAddress mappear(String tipo, Customer customer, Address address) {
        return CustomerAddress.builder()
                .customerId(customer.getId())
                .customer(customer)
                .addressId(address.getId())
                .address(address)
                .tipo(tipo)
                .build();
    }
}
