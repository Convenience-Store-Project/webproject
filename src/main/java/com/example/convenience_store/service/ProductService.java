package com.example.convenience_store.service;

import com.example.convenience_store.model.entity.Customer;
import com.example.convenience_store.model.entity.Product;
import com.example.convenience_store.model.entity.Reservation;
import com.example.convenience_store.repository.ProductRepository;
import com.example.convenience_store.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    public List<Product> findProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }


    public Product read(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return toProductResponse(product);
        }
        return null;
    }

    public Product update(Product updatedProduct) {
        Product existingProduct = productRepository.findById(updatedProduct.getProductId()).orElse(null);
        if (existingProduct != null) {
            if (existingProduct.getQuantity() - updatedProduct.getQuantity() < 0) {
                System.out.println("수량이 없습니다");
            } else {
                existingProduct.setQuantity(existingProduct.getQuantity() - updatedProduct.getQuantity());
            }
            Product updated = productRepository.save(existingProduct);
            return toProductResponse(updated);
        }
        return null;
    }

    public void confirmData() {
        // 데이터 읽어오기
        List<Product> productData = productRepository.findAll();

        // 데이터 변환 및 저장
        for (Product product : productData) {
            Reservation reservation = new Reservation();
            reservation.getCustomer();
            reservation.getProduct(); // 제품 정보를 설정
            reservation.getQuantity();
            reservation.getPrice();
            reservation.getTime();
            reservationRepository.save(reservation);
        }
    }

    private Product toProductResponse(Product product) {
        return Product.builder()
                .store(product.getStore())
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

}
