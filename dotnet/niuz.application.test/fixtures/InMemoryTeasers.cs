using System.Collections.Generic;
using niuz.application.entities;
using niuz.application.repositories;
using niuz.application.website;

namespace niuz.application.fixtures
{
    public class InMemoryTeasers : ITeaserRepository
    {
        private readonly Dictionary<string, List<Teaser>> pages = new Dictionary<string, List<Teaser>>();

        public void Save(string page, Teaser teaser)
        {
            FindPage(page).Add(teaser);
        }

        public IEnumerable<Teaser> GetByPage(string page)
        {
            return FindPage(page);
        }

        private List<Teaser> FindPage(string page)
        {
            if (!pages.ContainsKey(page)) pages[page] = new List<Teaser>();
            return pages[page];
        }
    }
}