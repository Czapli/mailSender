package net.czaplinski.mailSender.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.czaplinski.mailSender.controller.exceptions.AddressNotFoundException;
import net.czaplinski.mailSender.domain.model.MailAddresses;
import net.czaplinski.mailSender.repository.MailAddressesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MailAddressesService {
    private final MailAddressesRepository repository;


    public List<MailAddresses> getAllAddresses() {
        log.trace("get all email addresses from DB");
        return repository.findAll();
    }

    public MailAddresses getById(Long id) throws AddressNotFoundException {
        Optional<MailAddresses> mailAddresses = repository.findById(id);
        if (mailAddresses.isEmpty()) {
            log.trace("can not find Email Address with ID: " + id);
            throw new AddressNotFoundException();
        } else {
            log.trace("find email address: " + mailAddresses.get().getEmailAddress());
            return mailAddresses.get();
        }
    }

    public void addMailAddresses(MailAddresses mailAddress) {
        MailAddresses saved = repository.save(mailAddress);
        log.trace("new email address has been added to DB: " + saved.getEmailAddress() + " with ID: " + saved.getId());
    }

    public void updateMailAddress(Long id, MailAddresses newMailAddress) throws AddressNotFoundException {
        Optional<MailAddresses> updatedMailAddresses = repository.findById(id);
        if (updatedMailAddresses.isEmpty()) {
            log.trace("can not updated email address with ID: " + id);
            throw new AddressNotFoundException();
        } else {
            updatedMailAddresses.get().setEmailAddress(newMailAddress.getEmailAddress());
            updatedMailAddresses.get().setModificationDate(LocalDateTime.now());
            log.trace("update email address with ID: " + id + " for : " + newMailAddress.getEmailAddress());
            repository.save(updatedMailAddresses.get());
        }
    }

    public void deleteMailAddress(Long id) throws AddressNotFoundException {
        Optional<MailAddresses> updatedMailAddresses = repository.findById(id);
        if (updatedMailAddresses.isEmpty()) {
            log.trace("can not updated email address with ID: " + id);
            throw new AddressNotFoundException();
        } else {
            log.trace("delete email address with ID: " + id);
            repository.deleteById(id);
        }
    }
}
