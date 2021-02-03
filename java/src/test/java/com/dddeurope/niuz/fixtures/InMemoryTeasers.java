package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.entities.Teaser;
import com.dddeurope.niuz.repositories.TeaserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTeasers implements TeaserRepository {
    final Map<String, List<Teaser>> teasers = new HashMap<>();

    @Override
    public void save(String page, Teaser teaser) {
        teasers.putIfAbsent(page, new ArrayList<>());
        teasers.get(page).add(teaser);
    }

    @Override
    public List<Teaser> getByPage(String page) {
        return teasers.getOrDefault(page, new ArrayList<>());
    }}
