package business.project.microproductapi.serviceImpl;


import business.project.microproductapi.dto.ProductDto;
import business.project.microproductapi.model.Product;
import business.project.microproductapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAll(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductByCategoriaId(Long categoriaId){
        List<Product> products = productRepository.getProductByCategory(categoriaId);
        return products.stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }

    public ProductDto findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null){
            return ProductDto.convert(product);
        }
        return null;
    }

    public ProductDto save(ProductDto productDto){
        Product product = productRepository.save(Product.convert(productDto));
        return ProductDto.convert(product);
    }

    public void delete(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(value -> productRepository.delete(value));
    }

    public ProductDto editProduct(Long id, ProductDto productDto){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if(productDto.getNome() != null || !productDto.getNome().isEmpty()){
            product.setNome(productDto.getNome());
        }if(productDto.getPreco() != null){
            product.setPreco(productDto.getPreco());
        }
        return ProductDto.convert(productRepository.save(product));
    }

    public Page<ProductDto> getAllPage(Pageable page){
        Page<Product> users = productRepository.findAll(page);
        return users.map(ProductDto::convert);
    }

}
