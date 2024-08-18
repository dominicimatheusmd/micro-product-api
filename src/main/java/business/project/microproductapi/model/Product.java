package business.project.microproductapi.model;


import business.project.microproductapi.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private Float preco;
    private String descricao;
    private String productIdentifier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public static Product convert(ProductDto productDto){
        Product product = new Product();
        product.setNome(productDto.getNome());
        product.setPreco(productDto.getPreco());
        product.setDescricao(productDto.getDescricao());
        product.setProductIdentifier(productDto.getProductIdentifier());
        if (productDto.getCategoryDto() != null){
            product.setCategory(
                    Category.convert(productDto.getCategoryDto()));
        }
        return product;
    }
}
