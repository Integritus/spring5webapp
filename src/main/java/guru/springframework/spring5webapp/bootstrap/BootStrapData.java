package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Publisher name");
        publisher.setAddressLine1("12 St");
        publisher.setCity("Tacoma");
        publisher.setState("WA");
        publisher.setZip("12345");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        System.out.println("Printing Publishers");
        publisherRepository.findAll().forEach(System.out::println);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Author guy = new Author("Some", "Guy");
        Book thisGuyBook = new Book("Book of this guy", "123654789");
        guy.getBooks().add(thisGuyBook);
        thisGuyBook.getAuthors().add(guy);
        thisGuyBook.setPublisher(publisher);

        authorRepository.save(guy);
        bookRepository.save(thisGuyBook);


        System.out.println("Number of Books: " + bookRepository.count());

        System.out.println("Printing Authors");
        authorRepository.findAll().forEach(System.out::println);
        System.out.println("Printing Books");
        bookRepository.findAll().forEach(System.out::println);
    }
}
