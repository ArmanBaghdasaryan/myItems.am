package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    private int id;
    private String title;
    private double price;
    private Category category;
    private String picUrl;
    private User user;


}
