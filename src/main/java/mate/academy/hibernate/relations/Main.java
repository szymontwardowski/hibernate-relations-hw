package mate.academy.hibernate.relations;

import java.util.List;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Zamiast jednej długiej linii, rozbij to na dwa etapy:
        final CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        final CountryService countryService = new CountryServiceImpl(countryDao);

        final ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        final ActorService actorService = new ActorServiceImpl(actorDao);

        final MovieDao movieDao = new MovieDaoImpl(sessionFactory);
        final MovieService movieService = new MovieServiceImpl(movieDao);
        // Teraz te wywołania mogą zostać tam, gdzie były:
        Country usa = new Country("USA");
        countryService.add(usa);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);

        System.out.println("Pobrany film: " + movieService.get(fastAndFurious.getId()));
    }
}
