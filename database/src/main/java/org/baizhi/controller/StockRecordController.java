package org.baizhi.controller;

import org.baizhi.model.StockRecord;
import org.baizhi.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockRecords")
public class StockRecordController {

    @Autowired
    private StockRecordService stockRecordService;

    @GetMapping
    public List<StockRecord> getAllStockRecords() {
        return stockRecordService.getAllStockRecords();
    }

    @GetMapping("/{id}")
    public StockRecord getStockRecordById(@PathVariable("id") Integer id) {
        StockRecord stockRecord = stockRecordService.getStockRecordById(id);
        if (stockRecord != null) {
            return stockRecord;
        } else {
            throw new RuntimeException("库存记录不存在！");
        }
    }

    @GetMapping("/product/{productId}")
    public List<StockRecord> getStockRecordsByProductId(@PathVariable("productId") Integer productId) {
        return stockRecordService.getStockRecordsByProductId(productId);
    }

    @PostMapping
    public String createStockRecord(@RequestBody StockRecord stockRecord) {
        int result = stockRecordService.createStockRecord(stockRecord);
        return result > 0 ? "库存记录创建成功！" : "库存记录创建失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteStockRecord(@PathVariable("id") Integer id) {
        int result = stockRecordService.deleteStockRecord(id);
        return result > 0 ? "库存记录删除成功！" : "库存记录删除失败！";
    }

    @DeleteMapping("/product/{productId}")
    public String deleteStockRecordsByProductId(@PathVariable("productId") Integer productId) {
        int result = stockRecordService.deleteStockRecordsByProductId(productId);
        return result > 0 ? "商品相关库存记录删除成功！" : "商品相关库存记录删除失败！";
    }
} 