package com.grysz.pos;

class TotallingShoppingCart implements ShoppingCart {
    private Price total = Price.cents(0);

    @Override
    public void put(Price price) {
        total = total.add(price);
    }

    @Override
    public Price getTotal() {
        return total;
    }
}
