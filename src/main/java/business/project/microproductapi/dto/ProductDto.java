package business.project.microproductapi.dto;


import business.project.microproductapi.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotBlank
    private String productIdentifier;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    private Float preco;
    @NotNull
    private CategoryDto categoryDto;


    public static ProductDto convert(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductIdentifier(product.getProductIdentifier());
        productDto.setNome(product.getNome());
        productDto.setDescricao(product.getDescricao());
        productDto.setPreco(product.getPreco());
        if (product.getCategory() != null) {
            productDto.setCategoryDto(
                    CategoryDto.convert(product.getCategory()));
        }
        return productDto;
    }
}
