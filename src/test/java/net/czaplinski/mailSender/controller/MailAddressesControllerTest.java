package net.czaplinski.mailSender.controller;

import net.czaplinski.mailSender.PrepareData;
import net.czaplinski.mailSender.controller.exceptions.AddressNotFoundException;
import net.czaplinski.mailSender.domain.dto.MailAddressesDto;
import net.czaplinski.mailSender.domain.model.MailAddresses;
import net.czaplinski.mailSender.service.MailAddressesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MailAddressesControllerTest {
    @Autowired
    MailAddressesController controller;
    @Autowired
    MailAddressesService service;

    @Test
    public void getAllMailAddressesTest() {
        //Given
        service.addMailAddress(PrepareData.prepareMailAddress("test@test.pl"));
        service.addMailAddress(PrepareData.prepareMailAddress("test1@test.pl"));
        //When
        List<MailAddressesDto> all = controller.getAllMailAddresses().getBody();

        //Then
        assertEquals(2, all.size());
    }

    @Test
    public void getMailAddressByIdTest() throws AddressNotFoundException {
        //Given
        MailAddresses mailAddresses = PrepareData.prepareMailAddress("test@test.pl");
        service.addMailAddress(mailAddresses);
        //When
        MailAddressesDto mailAddressesDto = controller.getMailAddressById(mailAddresses.getId()).getBody();

        //Then
        assertEquals(mailAddresses.getId(), mailAddressesDto.getId());
        assertThrows(AddressNotFoundException.class, () -> controller.getMailAddressById(mailAddressesDto.getId() + 1));
    }

    @Test
    public void cantMailAddressByIdTest() {
        //Given
        MailAddresses mailAddresses = PrepareData.prepareMailAddress("test@test.pl");
        service.addMailAddress(mailAddresses);
        //When&Then
        assertThrows(AddressNotFoundException.class, () -> controller.getMailAddressById(mailAddresses.getId() + 1));
    }

    @Test
    public void addMailAddressTest() {
        //Given
        MailAddressesDto mailAddressesDto = PrepareData.prepareMailAddressDto("test@test.pl");

        //When
        controller.addMailAddress(mailAddressesDto);
        List<MailAddresses> allAddresses = service.getAllAddresses();

        //Then
        assertEquals(1, allAddresses.size());
    }

    @Test
    public void updateMailAddressTest() throws AddressNotFoundException {
        //Given
        MailAddresses mailAddress = PrepareData.prepareMailAddress("test@test.pl");
        service.addMailAddress(mailAddress);
        MailAddressesDto newMailAddressesDto = PrepareData.prepareMailAddressDto("newTest@test.pl");

        //When
        controller.updateMailAddress(mailAddress.getId(), newMailAddressesDto);
        MailAddresses newMailAddress = service.getById(mailAddress.getId());

        //Then
        assertEquals(newMailAddressesDto.getEmailAddress(), newMailAddress.getEmailAddress());
    }

    @Test
    public void deleteMailAddressTest() throws AddressNotFoundException {
        //Given
        MailAddresses mailAddress = PrepareData.prepareMailAddress("test@test.pl");
        service.addMailAddress(mailAddress);

        //When
        controller.removeMailAddressById(mailAddress.getId());

        List<MailAddresses> allAddresses = service.getAllAddresses();

        //Then
        assertEquals(0, allAddresses.size());
    }

}