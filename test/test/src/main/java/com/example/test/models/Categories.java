//package com.example.test.models;
//
//import com.fasterxml.jackson.annotation.*;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "categories")
////@JsonIgnoreProperties({"post"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "category_id")  // Thêm ID generator
//
//
//public class Categories {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "category_id")  // Ánh xạ tên cột chính xác
//    private Long category_id;
//    private String category_name;
//        @DateTimeFormat(pattern = "dd-MM-yyyy ")
//    private LocalDateTime created_at;
//
//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER,
//            cascade = {CascadeType.ALL})
//    @JsonIgnore
//    private List<Posts> post;
//}
