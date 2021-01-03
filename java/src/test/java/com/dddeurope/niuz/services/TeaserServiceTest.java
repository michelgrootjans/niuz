package com.dddeurope.niuz.services;

import com.dddeurope.niuz.dtos.TeaserDto;
import com.dddeurope.niuz.entities.Teaser;
import com.dddeurope.niuz.repositories.TeaserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeaserServiceTest {
    private TeaserRepository teasers;
    private TeaserService teaserService;

    @BeforeEach
    void setUp() {
        teasers = mock(TeaserRepository.class);
        teaserService = new TeaserService(teasers);
    }

    @Test
    void getHomepage() {
        when(teasers.getByPage("homepage")).thenReturn(Arrays.asList(new Teaser("headline", "Freddy Kruger")));
        assertThat(teaserService.get("homepage")).containsExactly(
                new TeaserDto("headline", "Freddy Kruger")
        );
    }
}
