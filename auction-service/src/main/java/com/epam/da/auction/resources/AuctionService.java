package com.epam.da.auction.resources;

import com.epam.da.auction.model.Auction;
import com.epam.da.auction.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/auction")
public class AuctionService {

    private static Logger log = LoggerFactory.getLogger(AuctionService.class);

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getProduct")
    public Product getProductDetails() {
        log.info("Handling home");
        Product response = restTemplate.getForObject("http://PRODUCT-SERVICE/product/view", Product.class);
        System.out.println(response);
        return response;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @RequestMapping(value = "/create", method = {RequestMethod.GET, RequestMethod.POST})
    public Auction create(Auction auction) {
        if(auction != null) {
            auctionRepository.save(auction);
        }
        return auction;
    }

    /*@GetMapping
    public Auction search(Auction auction) {
        return null;
    }

    @GetMapping
    public Auction view(Auction auction) {
        return null;
    }

    @PostMapping
    public Auction start(Auction auction) {
        return null;
    }

    @PostMapping
    public Auction stop(Auction auction) {
        return null;
    }*/
}
