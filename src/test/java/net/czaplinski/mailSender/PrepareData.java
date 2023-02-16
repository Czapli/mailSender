package net.czaplinski.mailSender;

import net.czaplinski.mailSender.domain.dto.MailAddressesDto;
import net.czaplinski.mailSender.domain.model.MailAddresses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrepareData {
    public static MailAddresses prepareMailAddress(String address) {
        return MailAddresses.builder()
                .emailAddress(address)
                .createDate(LocalDateTime.now())
                .build();
    }

    public static List<MailAddresses> prepareMailAddressList(int numberOfElements) {
        List<MailAddresses> mailAddresses = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            mailAddresses.add(MailAddresses.builder()
                    .emailAddress("test" + i + "@test.pl")
                    .createDate(LocalDateTime.now())
                    .build());
        }
        return mailAddresses;
    }

    public static MailAddressesDto prepareMailAddressDto(String address) {
        return MailAddressesDto.builder()
                .id(1L)
                .emailAddress(address)
                .build();
    }
}
