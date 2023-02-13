package net.czaplinski.mailSender.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MailAddressesDto {
    private Long id;
    @NotEmpty(message = "Pleas provide email address")
    @Email(message = "String must be email format")
    private String emailAddress;
}
