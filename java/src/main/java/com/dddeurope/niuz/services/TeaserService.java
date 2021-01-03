package com.dddeurope.niuz.services;

import com.dddeurope.niuz.dtos.TeaserDto;
import com.dddeurope.niuz.entities.Teaser;
import com.dddeurope.niuz.repositories.TeaserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TeaserService {
    private final TeaserRepository teasers;

    public TeaserService(TeaserRepository teasers) {
        this.teasers = teasers;
    }

    public List<TeaserDto> get(String page) {
        return teasers.getByPage(page).stream()
                      .map(this::map)
                      .collect(Collectors.toList());
    }

    private TeaserDto map(Teaser teaser) {
        return new TeaserDto(teaser.getHeadline(), teaser.getAuthor());
    }
}
