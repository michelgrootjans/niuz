package com.dddeurope.niuz.website;

import java.util.List;

public interface TeaserRepository {
    void save(String page, Teaser teaser);
    List<Teaser> getByPage(String page);
}
