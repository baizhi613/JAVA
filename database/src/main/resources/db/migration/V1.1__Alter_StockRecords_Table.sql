-- 先删除旧表（如果存在）
DROP TABLE IF EXISTS stockrecords;

-- 创建新表
CREATE TABLE stockrecords (
    stockRecordId INT PRIMARY KEY AUTO_INCREMENT,
    productId INT NOT NULL,
    type VARCHAR(10) NOT NULL,  -- 'In' 或 'Out'
    quantity INT NOT NULL,
    note VARCHAR(255),
    date DATETIME NOT NULL,
    FOREIGN KEY (productId) REFERENCES products(productId)
); 