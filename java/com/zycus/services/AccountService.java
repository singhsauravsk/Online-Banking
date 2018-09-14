package com.zycus.services;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.zycus.entity.accounts.Account;
import com.zycus.repository.AccountRepository;

@Service
@Transactional
public class AccountService implements Services <Account, Long> {
	
	@Autowired
	AccountRepository accountRepository;
	
	@CacheEvict(value = "allAccounts.cache", allEntries = true)
	public void addNew(Account account) {
		account.setAmount(12345.4d);
		account.setAccountNumber(System.nanoTime() + System.currentTimeMillis());
		account.setOpeningDate(System.currentTimeMillis());
		account.setPrimaryKey(System.currentTimeMillis());
		
		accountRepository.save(account);
	}

	@Cacheable(value = "allAccounts.cache")
	public Iterable<Account> fetchAll() {
		return accountRepository.findAll();
	}

	public Account fetchById(Long id) {
		return accountRepository.findById(id).get();
	}

	public boolean validateUser(Account t, HttpServletRequest request) {
		return false;
	}

}
