package com.dddeurope.niuz.commands;

import com.dddeurope.niuz.hr.AuthorService;
import com.dddeurope.niuz.hr.HireAuthor;

public class CommandDispatcher {
    private final AuthorService authorService;

    public CommandDispatcher(AuthorService authorService) {
        this.authorService = authorService;
    }

    public void dispatch(HireAuthor hireAuthor) {
        authorService.hire(hireAuthor);
    }
}
