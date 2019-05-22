package guru.springframework.spring5WebApp.bootstrap;

import guru.springframework.spring5WebApp.model.Author;
import guru.springframework.spring5WebApp.model.Book;
import guru.springframework.spring5WebApp.model.Publisher;
import guru.springframework.spring5WebApp.repositories.AuthorRepository;
import guru.springframework.spring5WebApp.repositories.BookRepository;
import guru.springframework.spring5WebApp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public AuthorRepository getAuthorRepository() {
        return authorRepository;
    }

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        // Publisher
        Publisher  harperCollins = new Publisher("Harper Collins", "123 abc");
        publisherRepository.save(harperCollins);

        Publisher worx = new Publisher("Worx", "456 def");
        publisherRepository.save(worx);

        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "12234", worx);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
