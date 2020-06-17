package com.epam.da.auction.resources;

import com.epam.da.auction.model.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, String> {
}
