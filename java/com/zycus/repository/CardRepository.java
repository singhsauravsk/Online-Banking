package com.zycus.repository;

import org.springframework.data.repository.CrudRepository;

import com.zycus.entity.cards.Card;

public interface CardRepository extends CrudRepository <Card, Long>{

}
