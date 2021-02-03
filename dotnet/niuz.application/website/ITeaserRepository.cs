using System.Collections.Generic;

namespace niuz.application.website
{
    public interface ITeaserRepository
    {
        void Save(string page, Teaser teaser);
        IEnumerable<Teaser> GetByPage(string page);
    }
}