package net.czaplinski.mailSender.domain.mapper;

import net.czaplinski.mailSender.PrepareData;
import net.czaplinski.mailSender.domain.dto.MailAddressesDto;
import net.czaplinski.mailSender.domain.model.MailAddresses;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MailAddressesMapperTest {

    MailAddressesMapper mapper = new MailAddressesMapper();

    @Test
    public void mapToMailAddressTest() {
        //Given
        MailAddressesDto mailAddressesDto = PrepareData.prepareMailAddressDto("test@test.com");

        //When
        MailAddresses mailAddresses = mapper.mapToMailAddress(mailAddressesDto);

        //Then
        assertEquals(mailAddressesDto.getEmailAddress(), mailAddresses.getEmailAddress());
    }

    @Test
    public void mapToMailAddressDtoTest() {

        //Given
        MailAddresses mailAddresses = PrepareData.prepareMailAddress("test@test.com");

        //When
        MailAddressesDto mailAddressesDto = mapper.mapToMailAddressDto(mailAddresses);

        //Then
        assertEquals(mailAddresses.getEmailAddress(), mailAddressesDto.getEmailAddress());
    }

    @Test
    public void mapToMailAddressDtoListTest() {

        //Given
        List<MailAddresses> mailAddresses = PrepareData.prepareMailAddressList(5);

        //When
        List<MailAddressesDto> mailAddressesDtos = mapper.mapToMailAddressesDtoList(mailAddresses);

        //Then
        assertEquals(mailAddresses.size(), mailAddressesDtos.size());
        assertEquals(mailAddresses.get(1).getEmailAddress(), mailAddressesDtos.get(1).getEmailAddress());
    }


}