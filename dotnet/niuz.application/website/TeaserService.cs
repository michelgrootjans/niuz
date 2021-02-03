using System.Collections.Generic;
using System.Linq;
using niuz.application.events;

namespace niuz.application.website
{
    public class TeaserService
    {
        private readonly ITeaserRepository teasers;

        public TeaserService(ITeaserRepository teasers, ITopic topic)
        {
            this.teasers = teasers;
            topic.Subscribe<ArticlePublished>(Publish);
        }

        private void Publish(ArticlePublished @event)
        {
            teasers.Save("homepage", new Teaser(@event.Headline, @event.Author));
        }
        
        public IEnumerable<TeaserDto> Get(string page)
        {
            return teasers.GetByPage(page)
                .Select(p => new TeaserDto(p.Headline, p.Author));
        }
    }
}