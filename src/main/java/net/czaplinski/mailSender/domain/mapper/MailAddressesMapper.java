package net.czaplinski.mailSender.domain.mapper;

import net.czaplinski.mailSender.domain.dto.MailAddressesDto;
import net.czaplinski.mailSender.domain.model.MailAddresses;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailAddressesMapper {
    public MailAddresses mapToMailAddress(MailAddressesDto mailAddressesDto) {
        return MailAddresses.builder()
                .emailAddress(mailAddressesDto.getEmailAddress())
                .createDate(LocalDateTime.now())
                .build();
    }

    public MailAddressesDto mapToMailAddressDto(MailAddresses mailAddresses) {
        return MailAddressesDto.builder()
                .id(mailAddresses.getId())
                .emailAddress(mailAddresses.getEmailAddress())
                .build();
    }

    public List<MailAddressesDto> mapToMailAddressesDtoList(List<MailAddresses> mailAddresses) {
        return mailAddresses.stream()
                .map(this::mapToMailAddressDto)
                .collect(Collectors.toList());
    }
}
