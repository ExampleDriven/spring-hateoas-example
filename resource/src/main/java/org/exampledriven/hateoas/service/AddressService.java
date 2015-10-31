package org.exampledriven.hateoas.service;

import org.exampledriven.hateoas.domain.Address;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    List<Address> addressList;

    public AddressService() {
        addressList = new LinkedList<>();
        addressList.add(new Address(1, "Street 1 ", 1));
        addressList.add(new Address(2, "Street 2", 2));
    }

    public Address getAddressByCustomerId(int customerId) {
        Optional<Address> address = addressList.stream().filter(address1 -> address1.getCustomerId() == customerId).findFirst();
        return address.get();
    }

    public Address getAddressById(int id) {
        Optional<Address> address = addressList.stream().filter(address1 -> address1.getId() == id).findFirst();
        return address.get();
    }

}
