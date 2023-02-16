package net.czaplinski.mailSender.service;

import net.czaplinski.mailSender.PrepareData;
import net.czaplinski.mailSender.controller.exceptions.AddressNotFoundException;
import net.czaplinski.mailSender.domain.model.MailAddresses;
import net.czaplinski.mailSender.repository.MailAddressesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailAddressesServiceTests {
    @Autowired
    private MailAddressesService service;
    @Autowired
    private MailAddressesRepository repository;

    @Test
    public void addNewMailAddressToRepository() {

        //Given
        MailAddresses mailAddresses = PrepareData.prepareMailAddress("test@test.com");
        repository.save(mailAddresses);

        //When
        Optional<MailAddresses> shouldFindEmailAddress = repository.findById(mailAddresses.getId());

        //Then
        assertEquals(shouldFindEmailAddress.get().getId(), mailAddresses.getId());
        assertEquals(shouldFindEmailAddress.get().getEmailAddress(), mailAddresses.getEmailAddress());
    }

    @Test
    public void addNewMailAddressToRepositoryByService() {

        //Given
        MailAddresses mailAddresses = PrepareData.prepareMailAddress("test@test.com");
        service.addMailAddress(mailAddresses);

        //When
        Optional<MailAddresses> shouldFindEmailAddress = repository.findById(mailAddresses.getId());
        Optional<MailAddresses> shouldFindEmailAddressViaService = repository.findById(mailAddresses.getId());

        //Then
        assertEquals(shouldFindEmailAddress.get().getId(), mailAddresses.getId());
        assertEquals(shouldFindEmailAddress.get().getEmailAddress(), mailAddresses.getEmailAddress());
        assertEquals(shouldFindEmailAddressViaService.get().getId(), mailAddresses.getId());
        assertEquals(shouldFindEmailAddressViaService.get().getEmailAddress(), mailAddresses.getEmailAddress());
    }

    @Test
    public void updateMailAddressInRepository() throws AddressNotFoundException {

        //Given
        MailAddresses mailAddresses = PrepareData.prepareMailAddress("test@test.com");
        service.addMailAddress(mailAddresses);

        //When
        MailAddresses newMailAddress = PrepareData.prepareMailAddress("newTest@test.com");
        service.updateMailAddress(mailAddresses.getId(), newMailAddress);
        MailAddresses shouldContainNewEmailAddress = service.getById(mailAddresses.getId());

        //Then
        assertTrue(shouldContainNewEmailAddress.getEmailAddress().contains("newTest@test.com"));
    }

    @Test
    public void deleteMailAddressFromRepository() throws AddressNotFoundException {

        //Given
        MailAddresses mailAddresses = PrepareData.prepareMailAddress("test@test.com");
        service.addMailAddress(mailAddresses);

        //When
        assertNotNull(service.getById(mailAddresses.getId()));
        service.deleteMailAddress(mailAddresses.getId());

        //Then
        assertThrows(AddressNotFoundException.class, () -> service.getById(mailAddresses.getId()));
    }



}