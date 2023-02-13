package net.czaplinski.mailSender.repository;

import net.czaplinski.mailSender.domain.model.MailAddresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailAddressesRepository extends JpaRepository<MailAddresses, Long> {
}
