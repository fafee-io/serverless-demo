package io.fafee.serverless;

import io.fafee.serverless.dto.Calculation;
import io.fafee.serverless.dto.Foo;
import io.fafee.serverless.dto.FooBar;
import io.fafee.serverless.entity.FooEntity;
import io.fafee.serverless.entity.FooRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DemoService {

    private final FooRepository fooRepository;
    private final FooMapper fooMapper;
    private final BarMapper barMapper;

    public Long create(Foo dto) {
        FooEntity entity = fooMapper.dtoToEntity(dto);
        entity = fooRepository.save(entity);

        return entity.getId();
    }

    public void update(Long id, Foo dto) {
        FooEntity entity = fooRepository.findById(id).get();
        fooMapper.updateFromDto(dto, entity);
        fooRepository.save(entity);
    }

    public List<Foo> list() {
        List<FooEntity> entities = fooRepository.findAll();
        return entities.stream().map(fooMapper::entityToDto).collect(Collectors.toList());
    }

    public FooBar get(Long id) {
        FooEntity foo = fooRepository.loadById(id).get();
        FooBar response = new FooBar();
        response.setId(foo.getId());
        response.setEnumeration(foo.getEnumeration());
        response.setName(foo.getName());
        response.setBars(foo.getBars().stream().map(barMapper::entityToDto).collect(Collectors.toList()));

        return response;
    }

    public Calculation calculate(Long id) {
        FooEntity foo = fooRepository.loadById(id).get();
        Double sum = foo.getBars().stream().map(bar -> Math.sqrt(
                bar.getCoords().getX() * bar.getCoords().getX() +
                bar.getCoords().getY() * bar.getCoords().getY() +
                bar.getCoords().getZ() * bar.getCoords().getZ()
        )).reduce(0.0d, Double::sum);

        return Calculation.builder().avgDistance(sum / foo.getBars().size()).build();
    }
}
