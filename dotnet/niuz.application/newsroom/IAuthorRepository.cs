namespace niuz.application.newsroom
{
    public interface IAuthorRepository
    {
        void Save(Author author);
        Author GetByAuthorId(string authorId);
    }
}