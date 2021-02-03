using System.Collections.Generic;
using niuz.application.entities;
using niuz.application.repositories;

namespace niuz.application.fixtures
{
    public class InMemoryAuthors : IAuthorRepository
    {
        private readonly Dictionary<string, Author> authors = new Dictionary<string, Author>();
        
        public void Save(Author author)
        {
            authors[author.Id] = author;
        }

        public Author GetByAuthorId(string authorId)
        {
            return authors[authorId];
        }
    }
}