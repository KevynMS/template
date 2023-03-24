package com.flakkeeverhuizers.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RelocationLabelsKey implements Serializable {

    /*@Column(name = "relocation_id", updatable = false, nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")*/
    @ManyToOne
    @JoinColumn(name = "relocation_id")
    private Relocations relocation;

    /*@Column(name = "label_id", updatable = false, nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")*/
    @ManyToOne
    @JoinColumn(name = "label_id")
    private Labels labelId;
}
