using niuz.application.entities;

namespace niuz.application.repositories
{
    public interface IAuthorRepository
    {
        void Save(Author author);
        Author GetByAuthorId(string authorId);
    }
}