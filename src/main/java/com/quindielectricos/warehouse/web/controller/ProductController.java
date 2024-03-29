package com.quindielectricos.warehouse.web.controller;

import com.quindielectricos.warehouse.domain.Product;
import com.quindielectricos.warehouse.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/all")
    @ApiOperation("Get all products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @ApiOperation("select a product")
    @ApiResponses({@ApiResponse(code = 400, message = "ok"),
    @ApiResponse(code = 404, message = "NOTFOUND"),})
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@ApiParam(value = "the product id", required = true,example = "3") @PathVariable("id") int productId){
        return productService.getProduct(productId).map(prod ->new ResponseEntity<>(prod,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/category/{idCategory}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("idCategory") int categoryId){
        return productService.getByCategory(categoryId).map(prods-> new ResponseEntity<>(prods,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
@PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity delete(@PathVariable("id") int productId){
        if (productService.delete(productId)){
            return new ResponseEntity<>(HttpStatus.OK) ;
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }


}
