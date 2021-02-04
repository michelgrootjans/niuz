using niuz.application.hr;

namespace niuz.application.commands
{
    public class CommandDispatcher
    {
        private readonly AuthorService authorService;

        public CommandDispatcher(AuthorService authorService)
        {
            this.authorService = authorService;
        }

        public void Dispatch(HireAuthor hireAuthor)
        {
            authorService.Hire(hireAuthor);
        }
    }
}