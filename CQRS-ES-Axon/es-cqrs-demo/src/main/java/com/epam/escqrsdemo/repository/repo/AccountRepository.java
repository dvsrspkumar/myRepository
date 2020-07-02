package com.epam.escqrsdemo.repository.repo;

import com.epam.escqrsdemo.repository.dto.AccountQueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
