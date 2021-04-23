package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product")
public class Product {
    private Long id;
    @TableField("product_name")
    private String product_name2;
    private String product_desc;
    private Double price;
    private String brand_name;
    private String category_name;
    private boolean is_enable;
    private boolean is_delete;
    private Long create_time;
    private  Long update_time;
}

