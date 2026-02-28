package mate.academy.hibernate.relations;

import java.util.List;
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
        // 1. Pobieramy fabrykę sesji
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // 2. Inicjalizujemy serwisy (wstrzykujemy im odpowiednie DAO)
        CountryService countryService = new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        ActorService actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));

        // 3. Tworzymy i zapisujemy przykładowe dane
        Country usa = new Country("USA");
        countryService.add(usa);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);

        // 4. Testujemy pobieranie z bazy
        System.out.println("Pobrany film: " + movieService.get(fastAndFurious.getId()));
    }
}
