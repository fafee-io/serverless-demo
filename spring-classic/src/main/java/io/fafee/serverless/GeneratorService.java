package io.fafee.serverless;

import io.fafee.serverless.entity.BarEntity;
import io.fafee.serverless.entity.BarRepository;
import io.fafee.serverless.entity.CoordsEmbeddable;
import io.fafee.serverless.entity.Enumeration;
import io.fafee.serverless.entity.FooEntity;
import io.fafee.serverless.entity.FooRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeneratorService {

    private final SecureRandom random = new SecureRandom();
    private final FooRepository fooRepository;
    private final BarRepository barRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        if (fooRepository.count() < 200) {
            for (int i = 0; i < 500; ++i) {
                FooEntity foo = new FooEntity();
                foo.setName(generateLetters(24));
                foo.setEnumeration(Enumeration.values()[nextInt(3)]);
                foo = fooRepository.save(foo);
                List<BarEntity> bars = new ArrayList<>();
                for (int j = 0; j < nextInt(100); ++j) {
                    BarEntity bar = new BarEntity();
                    bar.setIdentifier(generateLetters(10));
                    CoordsEmbeddable coords = new CoordsEmbeddable(nextDouble(100.0, 5000.0), nextDouble(100.0, 5000.0), nextDouble(100.0, 5000.0));
                    bar.setCoords(coords);
                    bar.setFoo(foo);
                    bars.add(bar);
                }
                barRepository.saveAll(bars);
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
