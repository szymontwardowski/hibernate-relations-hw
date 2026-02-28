package mate.academy.hibernate.relations.dao;

import java.util.Optional;
import mate.academy.hibernate.relations.model.Actor;

public interface ActorDao {
    Actor create(Actor actor);

    Optional<Actor> get(Long id);
}
