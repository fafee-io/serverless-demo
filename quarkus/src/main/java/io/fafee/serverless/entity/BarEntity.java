package io.fafee.serverless.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "demo_bar")
public class BarEntity extends BaseEntity {

    private String identifier;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "coords_x")),
            @AttributeOverride(name = "y", column = @Column(name = "coords_y")),
            @AttributeOverride(name = "z", column = @Column(name = "coords_z"))
    })
    private CoordsEmbeddable coords;

    @ManyToOne
    @JoinColumn(name = "foo_id")
    private FooEntity foo;
}
