package io.fafee.serverless;

import io.fafee.serverless.dto.Bar;
import io.fafee.serverless.dto.FooBar;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Slf4j
public class DemoService {

    private final SecureRandom random = new SecureRandom();

    public FooBar get(Long id) {
        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < nextInt(20); ++i) {
            bars.add(
                    Bar.builder()
                            .identifier(generateLetters(10))
                            .x(nextDouble(100.0, 100000.0))
                            .y(nextDouble(100.0, 100000.0))
                            .z(nextDouble(100.0, 100000.0))
                            .build()
            );
        }
        return FooBar.builder()
                .id(id)
                .name(generateLetters(24))
                .bars(bars)
                .build();
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public double nextDouble(double min, double max) {
        return random.nextDouble();
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
