package io.fafee.serverless;

import io.fafee.serverless.entity.BarEntity;
import io.fafee.serverless.entity.CoordsEmbeddable;
import io.fafee.serverless.entity.Enumeration;
import io.fafee.serverless.entity.FooEntity;
import io.quarkus.runtime.StartupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationScoped
public class GeneratorService {
    private final SecureRandom random = new SecureRandom();

    @Inject
    EntityManager em;

    @Transactional
    public void onStart(@Observes StartupEvent ev) {
        if (em.createQuery("from FooEntity", FooEntity.class).getResultList().size() < 200) {
            for (int i = 0; i < 500; ++i) {
                FooEntity foo = new FooEntity();
                foo.setName(generateLetters(24));
                foo.setEnumeration(Enumeration.values()[nextInt(3)]);
                em.persist(foo);
                List<BarEntity> bars = new ArrayList<>();
                for (int j = 0; j < nextInt(100); ++j) {
                    BarEntity bar = new BarEntity();
                    bar.setIdentifier(generateLetters(10));
                    CoordsEmbeddable coords = new CoordsEmbeddable(nextDouble(100.0, 5000.0), nextDouble(100.0, 5000.0), nextDouble(100.0, 5000.0));
                    bar.setCoords(coords);
                    bar.setFoo(foo);
                    bars.add(bar);
                    em.persist(bar);
                }
            }
        }
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public double nextDouble(double min, double max) {
        return random.nextDouble(min, max);
    }

    public String generateLetters(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        String generatedString =
                random
                        .ints(leftLimit, rightLimit + 1)
                        .limit(length)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

        return generatedString;
    }
}
