package com.dddeurope.niuz.repositories;

import com.dddeurope.niuz.entities.Teaser;

import java.util.List;

public interface TeaserRepository {
    void save(String page, Teaser teaser);
    List<Teaser> getByPage(String page);
}
