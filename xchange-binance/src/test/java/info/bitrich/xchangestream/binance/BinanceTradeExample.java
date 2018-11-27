package info.bitrich.xchangestream.binance;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.service.BinanceTradeService;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import info.bitrich.xchangestream.binance.BinanceDemoUtils;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;
import org.knowm.xchange.utils.StreamUtils;

public class BinanceTradeExample {

    public static void main(String[] args) throws IOException {

        Exchange exchange = BinanceDemoUtils.createExchange();
        exchange.getExchangeSpecification().setApiKey("pStaEkXOjIBcGE0M9N4PT0emLbpENccx82YIoWkQV39u116F3eO95h2TwKMqf36O");
        exchange.getExchangeSpecification().setSecretKey("8SIjmbtTt390DqjNv6oqegyE9JnSP7Tg1BrEmbTuLNZ3hmHY5tcnZmUavJwt87OB");
        generic(exchange);
        raw((BinanceExchange) exchange);
    }

    public static void generic(Exchange exchange) throws IOException {

        CurrencyPair pair = CurrencyPair.BTC_USDT;
        //TradeService tradeService = exchange.getTradeService();
        TradeService tradeService = new BinanceTradeService(exchange);

        // Get open orders
        OpenOrders orders = tradeService.getOpenOrders(new DefaultOpenOrdersParamCurrencyPair(pair));
        LimitOrder order = orders.getOpenOrders().stream().collect(StreamUtils.singletonCollector());
        if (order != null) {
            System.out.println(order);
        }
    }

    public static void raw(BinanceExchange exchange) throws IOException {

        CurrencyPair pair = CurrencyPair.EOS_ETH;
        BinanceTradeService tradeService = new BinanceTradeService(exchange);
        // Get open orders
        OpenOrders orders = tradeService.getOpenOrders(pair);
        LimitOrder order = orders.getOpenOrders().stream().collect(StreamUtils.singletonCollector());
        if (order != null) {
            System.out.println(order);
        }
    }
}
