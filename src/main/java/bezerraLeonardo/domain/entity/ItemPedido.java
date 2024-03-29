package bezerraLeonardo.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // esse annotation fará a coluna ID se auto incremento
    @Column(name = "id")
    private  Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private  Pedido pedido;

    @ManyToOne // annotation referencia a chave primaria
    @JoinColumn(name = "produto_id") // annotation cria o relacinamento
    private  Produto produto;

    @Column
    private Integer quantidade;

}
