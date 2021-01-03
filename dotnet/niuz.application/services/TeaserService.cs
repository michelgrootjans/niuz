using System.Collections.Generic;
using System.Linq;
using niuz.application.dtos;
using niuz.application.repositories;

namespace niuz.application.services
{
    public class TeaserService
    {
        private readonly ITeaserRepository teasers;

        public TeaserService(ITeaserRepository teasers)
        {
            this.teasers = teasers;
        }

        public IEnumerable<TeaserDto> Get(string page)
        {
            return teasers.GetByPage(page)
                .Select(p => new TeaserDto(p.Headline, p.Author));
        }
    }
}