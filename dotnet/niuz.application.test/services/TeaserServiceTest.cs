using System.Collections.Generic;
using FluentAssertions;
using niuz.application.dtos;
using niuz.application.entities;
using niuz.application.repositories;
using NSubstitute;
using Xunit;

namespace niuz.application.services
{
  public class TeaserServiceTest : BaseTest
  {
    [Fact]
    public void GetHomepage()
    {
      var teaserService = Get<TeaserService>();

      teasers.GetByPage("homepage").Returns(new List<Teaser> {new Teaser("headline", "Freddy Kruger")});

      teaserService.Get("homepage").Should().ContainInOrder(
        new TeaserDto("headline", "Freddy Kruger")
      );

    }
  }
}
