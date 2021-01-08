package com.udemy.projeto.services;

import com.udemy.projeto.model.Item;
import com.udemy.projeto.model.PaymentSlip;
import com.udemy.projeto.model.Pedido;
import com.udemy.projeto.model.enums.PaymentState;
import com.udemy.projeto.repositoies.CostumerRepository;
import com.udemy.projeto.repositoies.ItemRepository;
import com.udemy.projeto.repositoies.OrderRepository;
import com.udemy.projeto.repositoies.PaymentRepository;
import com.udemy.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SlipService slipService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ItemRepository itemRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
    @Autowired
    private CostumerService costumerService;

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstant(new Date());
        pedido.setCostumer(costumerService.find(pedido.getCostumer().getId()));
        pedido.getPayment().setPaymentState(PaymentState.PENDENTE);
        pedido.getPayment().setPedido(pedido);
        if (pedido.getPayment() instanceof PaymentSlip) {
            PaymentSlip pagto = (PaymentSlip) pedido.getPayment();
            slipService.fillPaymentSlip(pagto, pedido.getInstant());
        }
        pedido = orderRepository.save(pedido);
        paymentRepository.save(pedido.getPayment());
        for (Item item : pedido.getItems()) {
            item.setDiscount(0.0);
            item.setProduct(productService.find(item.getProduct().getId()));
            item.setPrice(item.getProduct().getPrice());
            item.setPedido(pedido);
        }
        itemRepository.saveAll(pedido.getItems());
        System.out.println(pedido);
        return pedido;
    }
}
