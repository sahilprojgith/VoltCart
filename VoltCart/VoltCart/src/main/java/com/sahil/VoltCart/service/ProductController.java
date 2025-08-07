package com.sahil.VoltCart.service;

import com.sahil.VoltCart.controller.ProductService;
import com.sahil.VoltCart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")// Here I defined a common base path for all the request mappings inside the controller.
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED);
    }
    //ResponseEntity<Product> → For a single Product object.
    //ResponseEntity<List<Product>> → For a list of Product objects.

    @GetMapping("/product/{id}") // {id} is a path variable placeholder (dynamic)
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product !=null) { // if product is not null teh n return the responseEntity
            return new ResponseEntity<>(product, HttpStatus.OK); // product being the variable here.
        }else { // or else return http-Status Not Found.  // and all that stored in variable product.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // then product references productservice
            // which has a method getproductbyid defined
        }
    }

    @GetMapping("product/{productId}/image") // get the image by product id
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product =  productService.getProductById(productId);
        if(product!=null){
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/api/product", consumes = "multipart/form-data")  //add
    public ResponseEntity<?> addProduct(@RequestPart("product") Product product,
                                        @RequestPart("imageFile") MultipartFile imageFile){
        Product savedProduct = null;
        try {
            savedProduct = productService.addProduct(product,imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }


    @PutMapping("product/{id}")  //update
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart("product") Product product,
                                                @RequestPart("imageFile") MultipartFile imageFile ){
        Product updateProduct = null;
        try{
            updateProduct =productService.updateProduct(product,imageFile);
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = productService.getProductById(id);
        if (product != null){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("Searching with" + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }




}
