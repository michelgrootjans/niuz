package com.dddeurope.niuz.website;

import com.dddeurope.niuz.events.ArticlePublished;
import com.dddeurope.niuz.events.Topic;

import java.util.List;
import java.util.stream.Collectors;

public class TeaserService {
    private final TeaserRepository teasers;

    public TeaserService(TeaserRepository teasers, Topic topic) {
        this.teasers = teasers;
        topic.subscribe(ArticlePublished.class, this::publish);
    }

    private void publish(ArticlePublished event) {
        teasers.save("homepage", new Teaser(event.getHeadline(), event.getAuthor()));
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