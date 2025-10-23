package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerAddress;
import br.com.fajbio.assistenciatecnica.domain.model.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByDocumento(String documento);

    @Query("SELECT cc FROM Customer c JOIN c.contacts cc " +
            "WHERE c.id = :customerId AND cc.id = :contactId")
    CustomerContact findContactByCustomerIdAndContactId(
            @Param("customerId") Long customerId,
            @Param("contactId") Long contactId
    );

    @Query("SELECT cc FROM Customer c JOIN c.address cc " +
            "WHERE c.id = :customerId AND cc.id = :addressId")
    CustomerAddress findAddressByCustomerIdAndAddressId(
            @Param("customerId") Long customerId,
            @Param("addressId") Long addressId
    );

}
