package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bidding implements Serializable {

    private final String description;
    private final List<Bid> bids;
    private double highestBid = 0.0;
    private double lowestBid = 0.0;

    public Bidding(String description) {
        this.description = description;
        this.bids = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void propoe(Bid bid) {
        double valorAtual = bid.getValue();

        if(handleBid(bid)){
            bids.add(bid);
            Collections.sort(bids); //organiza lances
            calculaMaiorLance(valorAtual);
            calculaMenorLance(valorAtual);
        };
    }

    private boolean handleBid(Bid bid) {
        //Retorna aviso para o usuário
        if (bids.isEmpty()) {
            initBidsToFirstBid(bid);
            return true;
        } else return isHighestBidder(bid)
                && isDifferentUser(bid)
                && !isLastBid(bid.getUser());
    }

    private void initBidsToFirstBid(Bid bid) {
        highestBid = bid.getValue();
        lowestBid = bid.getValue();
    }

    private boolean isHighestBidder(Bid bid) {
        return bid.getValue() > bids.get(0).getValue();
    }

    private void calculaMenorLance(double valorAtual) {
        if (valorAtual < lowestBid) {
            lowestBid = valorAtual;
        }
    }

    private void calculaMaiorLance(double valorAtual) {
        if (valorAtual > highestBid) {
            highestBid = valorAtual;
        }
    }

    public List<Bid> getBids() {
        return bids;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public double getLowestBid() {
        return lowestBid;
    }

    public void setLowestBid(double lowestBid) {
        this.lowestBid = lowestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public List<Bid> tresMaioresLances() {
        if (bids.size() < 3) {
            return bids.subList(0, bids.size());
        }
        return bids.subList(0, 3);
        //Método subList -> Pegas os 3 primeiros valores
    }

    private boolean isDifferentUser(Bid bid) {
        if (bids.get(0).getUser().equals(bid.getUser())) {
            return false;
        }
        return true;
    }

    private boolean isLastBid(User user) {
        int cont = 0;

        for (Bid l : getBids()) {
            if (user.equals(l.getUser())) {
                cont++;
                if (cont == 5) {
                    return true;
                }
            }
        }
        return false;
    }
}
