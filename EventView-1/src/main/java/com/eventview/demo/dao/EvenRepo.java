package com.eventview.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.eventview.demo.lol.Evens;

public interface EvenRepo extends CrudRepository< Evens, Integer> {

}
