package org.baizhi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.baizhi.model.SeckillProduct;
import org.baizhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/seckill/product")
public class ProductController {

    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductController(ProductService productService, RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.productService = productService;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    // 查询所有秒杀商品
    @GetMapping("/products")
    public List<SeckillProduct> getAllProducts() {
        return productService.getAllProducts();
    }

    // 根据id查询秒杀商品
    @GetMapping("/productbyid")
    public SeckillProduct getProductById(@RequestParam("productId") Long productId) {
        return productService.getProductById(productId);
    }

    // 添加秒杀商品
    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody SeckillProduct product){
        try{
            productService.addProduct(product);
            // 将新增的商品存入 Redis 缓存
            Long id = product.getId();
            String redisKey = "seckill:product:" + id;
            String productJson = objectMapper.writeValueAsString(product);
            redisTemplate.opsForValue().set(redisKey, productJson, 1000, TimeUnit.MINUTES); // 设置缓存过期时间
            return ResponseEntity.ok("商品添加成功");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("商品添加失败：" + e.getMessage());
        }
    }

    // 根据id删除秒杀商品
    @DeleteMapping("/deleteproduct")
    public ResponseEntity<String> deleteProduct(@RequestParam("productId") Long productId) {
        try {
            // 检查商品是否存在
            SeckillProduct product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.status(404).body("商品不存在");
            }
            // 删除商品
            productService.deleteProduct(productId);
            // 删除 Redis 中的商品缓存
            String redisKey = "seckill:product:" + productId;
            redisTemplate.delete(redisKey);
            return ResponseEntity.ok("商品删除成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("商品删除失败：" + e.getMessage());
        }
    }

}
