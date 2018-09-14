package com.zycus.repository;

import org.springframework.data.repository.CrudRepository;

import com.zycus.entity.accounts.Account;

public interface AccountRepository extends CrudRepository <Account, Long>{

}
