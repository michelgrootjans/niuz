using System.Collections.Generic;
using niuz.application.entities;

namespace niuz.application.repositories
{
    public interface ITeaserRepository
    {
        void Save(string page, Teaser teaser);
        List<Teaser> GetByPage(string page);
    }
}