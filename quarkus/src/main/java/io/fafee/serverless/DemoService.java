package io.fafee.serverless;

import io.fafee.serverless.dto.Calculation;
import io.fafee.serverless.dto.Foo;
import io.fafee.serverless.dto.FooBar;
import io.fafee.serverless.entity.FooEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class DemoService {

    @Inject
    EntityManager em;
    @Inject
    FooMapper fooMapper;
    @Inject
    BarMapper barMapper;

    @Transactional
    public Long create(Foo dto) {
        FooEntity entity = fooMapper.dtoToEntity(dto);
        em.persist(entity);

        return entity.getId();
    }

    @Transactional
    public void update(Long id, Foo dto) {
        FooEntity entity = em.find(FooEntity.class, id);
        fooMapper.updateFromDto(dto, entity);
        em.persist(entity);
    }

    public List<Foo> list() {
        List<FooEntity> entities = em.createQuery("from FooEntity", FooEntity.class).getResultList();
        return entities.stream().map(fooMapper::entityToDto).collect(Collectors.toList());
    }

    public FooBar get(Long id) {
//        FooEntity foo = fooRepository.loadById(id).get();
//        FooBar response = new FooBar();
//        response.setId(foo.getId());
//        response.setEnumeration(foo.getEnumeration());
//        response.setName(foo.getName());
//        response.setBars(foo.getBars().stream().map(barMapper::entityToDto).collect(Collectors.toList()));
//
//        return response;
        return null;
    }

    public Calculation calculate(Long id) {
//        FooEntity foo = fooRepository.loadById(id).get();
//        Double sum = foo.getBars().stream().map(bar -> Math.sqrt(
//                bar.getCoords().getX() * bar.getCoords().getX() +
//                bar.getCoords().getY() * bar.getCoords().getY() +
//                bar.getCoords().getZ() * bar.getCoords().getZ()
//        )).reduce(0.0d, Double::sum);
//
//        return Calculation.builder().avgDistance(sum / foo.getBars().size()).build();
        return null;
    }
}
