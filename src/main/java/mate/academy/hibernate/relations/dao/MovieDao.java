package mate.academy.hibernate.relations.dao;

import java.util.Optional;
import mate.academy.hibernate.relations.model.Movie;

public interface MovieDao {
    Movie create(Movie movie);

    Optional<Movie> get(Long id);
}
