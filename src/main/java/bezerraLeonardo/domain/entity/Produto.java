package bezerraLeonardo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor // annotation do lombok
@AllArgsConstructor // annotation do lombok
@Data // annotation do lombok onde vc nao precisa criar os geters e seters
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private  String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco;

}
