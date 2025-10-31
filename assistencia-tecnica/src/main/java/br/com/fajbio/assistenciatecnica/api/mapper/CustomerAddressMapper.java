package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CustomerAddressReq;
import br.com.fajbio.assistenciatecnica.api.dto.CustomerAddressRes;
import br.com.fajbio.assistenciatecnica.domain.model.Address;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerAddressMapper {
    private final AddressMapper addressMapper;

    public CustomerAddress mappear(Address address, String tipo, Customer customer) {
        return CustomerAddress.builder()
                .customerId(customer.getId())
                .customer(customer)
                .addressId(address.getId())
                .address(address)
                .tipo(tipo)
                .build();
    }

    public List<CustomerAddressRes> mappear(List<CustomerAddress> addresses) {
        return addresses.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public CustomerAddressRes mappear(CustomerAddress address) {
        return CustomerAddressRes.builder()
                .id(address.getId())
                .address(addressMapper.mappear(address.getAddress()))
                .tipo(address.getTipo())
                .build();
    }

    public CustomerAddress mappear(CustomerAddressReq address){
        return CustomerAddress.builder()
                .address(addressMapper.mappear(address.address()))
                .tipo(address.tipo())
                .build();
    }

    public List<CustomerAddress> mappear(Set<CustomerAddressReq> addresses) {
        return addresses.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }
}
