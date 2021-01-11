using niuz.application.entities;
using niuz.application.repositories;
using NSubstitute;
using Xunit;

namespace niuz.application.services
{
    public class AuthorServiceTest
    {
        private readonly IAuthorRepository authors;
        private readonly AuthorService authorService;

        public AuthorServiceTest()
        {
            authors = Substitute.For<IAuthorRepository>();
            authorService = new AuthorService(authors);
        }

        [Fact]
        public void HireAuthor()
        {
            authorService.Hire("author-1", "Freddy Kruger", "123-4567-89", "pay-by-publication");
            authors.Received().Save(new Author("author-1", "Freddy Kruger", "123-4567-89", "pay-by-publication"));
        }
    }
}