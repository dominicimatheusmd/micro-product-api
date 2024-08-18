package business.project.microproductapi.controller;

import business.project.microproductapi.dto.ProductDto;
import business.project.microproductapi.serviceImpl.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts(){
        return productService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getProductsByCategory(@PathVariable Long categoryId){
        return productService.getProductByCategoriaId(categoryId);
    }

    @GetMapping("/{productIdentifier}")
    public ProductDto findById(@PathVariable String productIdentifier){
        return productService.findByProductIdentifier(productIdentifier);
    }
    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.save(productDto);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

    @PostMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        return productService.editProduct(id, productDto);
    }

    @GetMapping("/pageable")
    public Page<ProductDto> getProductsPage(Pageable pageable){
        return productService.getAllPage(pageable);
    }
}
