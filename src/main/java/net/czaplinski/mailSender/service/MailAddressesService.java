package net.czaplinski.mailSender.service;

import lombok.AllArgsConstructor;
import net.czaplinski.mailSender.controller.exceptions.AddressNotFoundException;
import net.czaplinski.mailSender.domain.model.MailAddresses;
import net.czaplinski.mailSender.repository.MailAddressesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MailAddressesService {
    private final MailAddressesRepository repository;


    public List<MailAddresses> getAllAddresses() {
        return repository.findAll();
    }

    public MailAddresses getById(Long id) throws AddressNotFoundException {
        return repository.findById(id).orElseThrow(AddressNotFoundException::new);
    }

    public void addMailAddresses(MailAddresses mailAddress) {
        repository.save(mailAddress);
    }

    public void updateMailAddress(Long id, MailAddresses newMailAddress) {
        MailAddresses updatedMailAddresses = repository.findById(id).get();
        updatedMailAddresses.setEmailAddress(newMailAddress.getEmailAddress());
        updatedMailAddresses.setModificationDate(LocalDateTime.now());
        repository.save(updatedMailAddresses);
    }

    public void deleteMailAddress(Long id) {
        repository.deleteById(id);
    }
}
