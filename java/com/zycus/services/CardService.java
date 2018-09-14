package com.zycus.services;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zycus.entity.cards.Card;
import com.zycus.repository.CardRepository;

@Service
@Transactional
public class CardService implements Services <Card, Long> {

	@Autowired
	CardRepository cardRepository;
	
	@CacheEvict(value = "allCards.cache", allEntries = true)
	public void addNew(Card t) {
		cardRepository.save(t);
	}

	@Cacheable(value = "allCards.cache")
	public Iterable<Card> fetchAll() {
		return cardRepository.findAll();
	}

	public Card fetchById(Long id) {
		return cardRepository.findById(id).get();
	}

	public boolean validateUser(Card t, HttpServletRequest request) {
		return false;
	}
}
