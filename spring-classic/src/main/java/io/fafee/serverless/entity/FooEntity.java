package io.fafee.serverless.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "demo_foo")
public class FooEntity extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Enumeration enumeration;

    @OneToMany(mappedBy = "foo", fetch = FetchType.LAZY)
    private Set<BarEntity> bars;

}
