package amvisible.hookahmarket.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private List<Article> articles;
}
