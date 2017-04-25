package ecompim.ERPAccess;

import ecompim.Product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class ERPDummyDB {

    public static Map<Integer, Product> getProducts() {
        Map<Integer, Product> products = new HashMap<>();


        products.put(1, new Product(1, "MSI Z270I Gaming Pro Carbon AC, S-1151\n" +
                "Bundkort, mini-ITX, Z270, DDR4, 1xPCIe-x16, M.2, WiFi, Mystic Light Sync", 1649, "MSI Z270I Gaming Pro Carbon AC", 50));

        products.put(2, new Product(2, "AMD Ryzen 7 1700 Processor\n" +
                "Socket-AM4, 8-Core, 16-Thread, 3.0/3.7GHz, 65W, inkl. køler", 2690, "AMD Ryzen 7 1700", 34));

        products.put(3, new Product(3, "Corsair Obsidian 900D Big Tower Sort\n" +
                "Blæsere: 3x 120mm Front, 1x 140mm Bag, M/E-ATX, m-ITX, HPTX, USB2.0/3, Vindue", 2999, "Corsair Obsidian 900D", 16));

        products.put(4, new Product(4, "MSI GeForce GTX 1080 Ti Sea Hawk X\n" +
                "Grafikkort, PCI-Express 3.0, 11GB GDDR5X, 1544/1657MHz, Water cooled, Pascal", 7790, "MSI GeForce GTX 1080 Ti Sea Hawk X", 6));

        //TODO Products may be added here
        return products;
    }
}
