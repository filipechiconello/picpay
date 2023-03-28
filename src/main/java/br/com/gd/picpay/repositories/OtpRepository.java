package br.com.gd.picpay.repositories;

import br.com.gd.picpay.entities.OtpEntity;
import org.springframework.data.repository.CrudRepository;

public interface OtpRepository extends CrudRepository<OtpEntity, Long> {

    OtpEntity findByEmail(String email);
}
